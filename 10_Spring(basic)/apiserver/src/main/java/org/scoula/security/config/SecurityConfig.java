package org.scoula.security.config;
//보안을 위해 어떤 필터적용할지, 어떤권한 부여할지 설정을 작성하는
//config파일.

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CorsFilter;


@Configuration
@Log4j2
@EnableWebSecurity
@MapperScan(basePackages = {"org.scoula.security.account.mapper"})  //해당 경로내의 매퍼파일을 찾아내어(scan) 인식, -> Mybatis로 만들어줌.
@ComponentScan(basePackages = {"org.scoula.security"})
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {//
    private final UserDetailsService userDetailsService;

    //PasswordEncorder 인터페이스
    //  ==> 비밀번호는 반드시 암호화해서 처리해야 한다!!
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 문자셋필터적용
    // post방식의 전달자 body에 들어있는 값 한글인코딩 필터
    public CharacterEncodingFilter encodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return encodingFilter;
    }

    // AuthenticationManager 빈 등록
    //              JWT 방식은 폼로그인과달리Spring Security의 기본 인증필터를 사용하지않고,
    //              클라이언트→ JWT 토큰→ 커스텀필터
    //              (OncePerRequestFilter 등) →
    //              SecurityContext 직접 설정
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //cross origin 접근허용
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    // 접근제한무시경로설정–resource
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**", "/*", "/api/member/**");
    }


    // 경로별 접근권한을 설정하기.
    @Override
    public void configure(HttpSecurity http) throws Exception {
        //한글인코딩필터
        http.addFilterBefore(encodingFilter(), CsrfFilter.class);

        http.httpBasic().disable()  // 기본 HTTP 인증비활성화
                .csrf().disable()  // CSRF 비활성화
                .formLogin().disable()  // formLogin 비활성화- 관련 필터 해제
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 세션 생성 모드 설정


        // 경로별 접근권한설정
        // form-login 기본설정은 비활성화되어서 사라짐.
        //권한이 없으면 403에러 화면에 뜸.
        // --> 이 에러화면보다는 로그인하는 페이지를 보여주는것이 더 나을거 같음.
        http.authorizeRequests()
                //모두허용
                .antMatchers("/security/all").permitAll()
                //특정역할에게 헝용
                .antMatchers("/security/admin").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/security/member").access("hasAnyRole('ROLE_MEMBER', 'ROLE_ADMIN')");

        http.formLogin() // form 기반로그인활성화, 나머지는모두디폴트
                .loginPage("/security/login")
                .loginProcessingUrl("/security/login")
                .defaultSuccessUrl("/");
        http.logout()                           // 로그아웃설정시작
                .logoutUrl("/security/logout")  // POST: 로그아웃 호출 url
                .invalidateHttpSession(true)    // 세션 invalidate
                .deleteCookies("remember-me", "JSESSION-ID") // 삭제할 쿠키 목록
                .logoutSuccessUrl("/security/logout");    // GET: 로그아웃 이후이동할페이지
    }

    //인증정보의 출처를 설정.
    //          (여기서는, 로그인 할수 있는 사용자의 정보를 등록 by 메모리방식.)
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        log.info("configure .........................................");
        // in memory user → UserDetailsService와 같이 사용불가
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());        //여기까지가 레벨2까지 적용한거임.

//----------//테스트 위해 임시적으로 램에 넣어서 사용했던 아이디/비밀번호 설정들이므로, 주석처리함.//------------------
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                //.password("{noop}1234")  //암호화 X
//                .password("$2a$10$EsIMfxbJ6NuvwX7MDj4WqOYFzLU9U/lddCyn0nic5dFo3VfJYrXYC")  //암호화 O - 같은 문자열이어도 매번 다르게 암호화됨.
//                .roles("ADMIN", "MEMBER"); // ROLE_ADMIN
//        auth.inMemoryAuthentication()
//                .withUser("member")
//                //.password("{noop}1234")
//                .password("$2a$10$EsIMfxbJ6NuvwX7MDj4WqOYFzLU9U/lddCyn0nic5dFo3VfJYrXYC")
//                .roles("MEMBER");// ROLE_MEMBER
    }
}
