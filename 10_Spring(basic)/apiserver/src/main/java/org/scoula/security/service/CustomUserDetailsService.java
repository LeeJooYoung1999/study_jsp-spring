package org.scoula.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.security.account.domain.CustomUser;
import org.scoula.security.account.domain.MemberVO;
import org.scoula.security.account.mapper.UserDetailsMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Log4j2
@Component   // CustomUserDetailsService을 Bean으로 등록
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private UserDetailsMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberVO member = mapper.get(username);
        if (member == null) {
            throw new UsernameNotFoundException(username + "은 없는 id입니다.");
        }
        return new CustomUser(member);
    }
}
