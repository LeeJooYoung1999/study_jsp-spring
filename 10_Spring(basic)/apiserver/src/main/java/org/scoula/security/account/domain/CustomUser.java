package org.scoula.security.account.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class CustomUser extends User {  //User는 이미 시큐리티 내에 만들어져 있는녀석임.
    //Security내에서 회원정보를 담을 객체는 User객체임
    //우리의 회원정보는 MemverVO에 있음.
    //MemberVO --> User객체에 매핑시켜주어야함
    private MemberVO memberVO;  //실질적인 사용자데이터임.

    //생성자 2개를 만들어줌
    public CustomUser(MemberVO memberVO) {
        super(memberVO.getUsername(), memberVO.getPassword(), memberVO.getAuthList());
        this.memberVO = memberVO;
    }

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
