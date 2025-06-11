package org.scoula.controller;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@Log4j2
public class HomeController {
    @GetMapping("/") // "/"주소로 get요청 들어오면,

    //SampleDTO : 가방역할은 항상 필요할까? --> 필요할때만 잠깐 만들었다가, 필요없으면 없애버리는 특징 (따라서 당연히 싱글톤객체로는 만들지 않는다.)
    //HttpSession : 로그아웃전까지, 브라우저를 완전히 다 닫기전까지.
    public String home(Model model) {
        //Model : 서버에서 forward할때, request, response객체가 계속 전달된다.
        //request객체에 속성으로 저장해줘! 속성으로 지정한 것이 request객체와 함께
        //forward에 전달됨. jsp에서 꺼내어 출력할 예정!

        //스프링이 핸들러매퍼에 주소와방식에 따른 어떤 컨트롤러의 메소드를
        //불러야할지를 자동으로 등록시켜줌
        model.addAttribute("name","홍길동");


        return "index";              // View의 이름
    }
}
