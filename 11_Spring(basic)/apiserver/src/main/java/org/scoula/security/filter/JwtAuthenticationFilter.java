package org.scoula.security.filter;

//JwtAuthenticationFilter를 정의하라 -> OncePerRequestFilter 상속
//  컨트롤러가forward 했을때 필터를 다시 통과하게 됨 -> 이미 보안필터를 통과했는데 재통과하지 않도록,
//요청당 한번만 필터가 동작하도록 해준다.

// => 이 필터의 핵심목적 : "JWT 토큰에서 사용자 정보를 추출해 SecurityContext에 인증정보로 등록한다."

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.scoula.security.util.JwtProcessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Log4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter { //한 HTTP 요청당 단 한 번만 doFilterInternal()을 실행하도록 설계된 OncePerRequestFilter 확장.
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer "; //공백포함, "Bearer"뒤에 오는것이 실제 JWT 토큰이 된다.

    private final JwtProcessor jwtProcessor;
    private final UserDetailsService userDetailsService;

    private Authentication getAuthentication(String token) {
        String username = jwtProcessor.getUsername(token);  //토큰에서 사용자명username 추출
        UserDetails principal = userDetailsService.loadUserByUsername(username); //위 사용자 정보를 기반으로 UserDetails객체 로드
        return new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities()); //인증토큰객체 Authentication객체를 생성,반환
    }


    //--------필터가 실제로 작동하는 부분(메서드)---------------------------------------------------------//
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);  //Authorization객체의 헤더추출

        if (bearerToken != null && bearerToken.startsWith(BEARER_PREFIX)) { //Bearer 토큰 포맷 확인 후 파싱
            String token = bearerToken.substring(BEARER_PREFIX.length());

            //토큰에서 사용자 정보추출(파싱) 및 Authentication 객체 구성 후 SecurityContext에 객체저장.
            Authentication authentication = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        super.doFilter(request, response, filterChain);
    }
}
