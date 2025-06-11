package org.scoula.controller;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// HomeController에서 다음을 처리하세요.
// ○ Model을 이용해 name속성의 값으로 홍길동을 설정
// ○ index 뷰에서 name 속성을 출력

@Controller
@Slf4j
@Log4j2
public class HomeController {

    @GetMapping("/") // "/"주소로 get요청 들어오면,
    public String home(Model model) { //Model을 이용해
        model.addAttribute("name","홍길동"); //name속성의 값으로 홍길동을 설정

        return "index";              // index뷰에서 name속성 출력.
    }
}
