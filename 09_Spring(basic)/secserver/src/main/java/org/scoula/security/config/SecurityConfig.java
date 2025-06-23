package org.scoula.security.config;
//보안을 위해 어떤 필터적용할지, 어떤권한 부여할지 설정을 작성하는
//config파일.

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;


@Configuration
@Log4j2
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {//

    //PasswordEncorder 인터페이스
    //  ==> 비밀번호는 반드시 암호화해서 처리해야 한다!!
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 문자셋필터적용
    public CharacterEncodingFilter encodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return encodingFilter;
    }

    // 경로별 접근권한을 설정하기.
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(encodingFilter(), CsrfFilter.class);
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
        auth.inMemoryAuthentication()
                .withUser("admin")
                //.password("{noop}1234")  //암호화 X
                .password("$2a$10$EsIMfxbJ6NuvwX7MDj4WqOYFzLU9U/lddCyn0nic5dFo3VfJYrXYC")  //암호화 O - 같은 문자열이어도 매번 다르게 암호화됨.
                .roles("ADMIN", "MEMBER"); // ROLE_ADMIN
        auth.inMemoryAuthentication()
                .withUser("member")
                //.password("{noop}1234")
                .password("$2a$10$EsIMfxbJ6NuvwX7MDj4WqOYFzLU9U/lddCyn0nic5dFo3VfJYrXYC")
                .roles("MEMBER");// ROLE_MEMBER
    }
}
