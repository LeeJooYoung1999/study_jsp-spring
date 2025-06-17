package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class HomeController {

    @GetMapping("/")
    public String home() {
//        log.info("================> HomController /");
//        return "index";		// View의 이름

        return "redirect:/board/list";  //개발편의를 위해 홈페이지를 임시로 게시판 목록페이지로 리다이렉트
    }

}
