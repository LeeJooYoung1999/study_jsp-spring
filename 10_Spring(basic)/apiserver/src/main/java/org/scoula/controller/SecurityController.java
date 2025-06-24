package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Log4j2
@RequestMapping("/security")
@Configuration
public class SecurityController {

    //보안 권한 3단계 추가.
    @GetMapping("/all") //1단계: 전부접근가능.
    public void doAll() { //void인경우, /security/all
        log.info("do all can access everybody");
    }

    //    @GetMapping("/member") //member권한 가진 사람, admin권한 가진 사람 접근 가능
//    public void doMember() {
//        log.info("logined member");
//    }
    @GetMapping("/member") //2단계: MEMBER 또는ADMIN 권한가진사람 접근가능.
    public void doMember(Principal principal) {
        log.info("logined member details info >>" + principal);
        log.info("logined member details info >>" + principal.getName());
    }

    @GetMapping("/admin") //3단계: ADMIN 권한있어야만 접근가능.
    public void doAdmin() {
        log.info("admin only");
    }


    @GetMapping("/login")
    public void login() {
        log.info("login page");
    }

    @GetMapping("/logout")
    public void logout() {
        log.info("logout page");
    }
}
