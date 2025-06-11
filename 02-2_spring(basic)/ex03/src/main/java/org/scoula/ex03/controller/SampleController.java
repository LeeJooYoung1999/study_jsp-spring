package org.scoula.ex03.controller;
//SampleController에 다음 작업을 진행하세요.
//  공통url을"/sample"로 설정
//  /sample 경로에 대한 모든 요청메서드에 대해 호출되는 basic() 메서드작성

import lombok.extern.log4j.Log4j2;
import org.scoula.ex03.dto.SampleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller //싱글톤 + 클래스 안에서 설정한 주소 -> 함수 매핑을 핸들러매퍼에게 등록해줌.
//@Component + 컨트롤러 기능도 추가
@RequestMapping("/sample")  // 해당 도메인을 요청할때는앞에다 sample이라고 붙이자 는 뜻.
//   http://locahothost:8080/sample/insert,  localhost == 127.0.0.1
//  /sample/delete , /sample/update
@Log4j2
public class SampleController {

    //sample 도메인을 위한 여러 요청에 대한 함수를 정의함.
    @RequestMapping("")    // url : /sample
    public void basic() {
        log.info("basic............");
    }

    // /sample/basic 경로의 GET, POST 요청에 호출되는 basicGet() 메서드 작성
    @RequestMapping(value="/basic", method= {RequestMethod.GET, RequestMethod.POST})  // url: /sample/basic
    public void basicGet(){
        log.info("basic get............");
        //스프링은 컨트롤러로 오면 그 다음은 무조건 jsp파일을 호출하는 흐름임
        //이를 forward라고 함 ==> 결과를 넣는 jsp페이지가 필요하다!!
        //redirect하여 view로 안갈수도 있음. ==> 다른페이지로 전환.
    }

    // /sample/basicOnlyGet 경로의 GET 요청에만 호출되는 basicGet2() 메서드 작성
    @GetMapping("/basicOnlyGet")  // url: /sample/basicOnlyGet
    public void basicGet2(){
        //void인 경우에 호출한 주소를 사용해서 /WEB-INF/views/요청주소.jsp
        //요청주소와 views밑에 파일이름이 같아면, String으로 return 안해도 됨.
        // /sample/basicOnlyGet.jsp를 찾는다는 뜻.
        log.info("basic get only get............");
    }

    //다음과 같이 요청했을때 SampleController에서 이를 처리하는 ex04() 메서드를 작성하세요.
    @GetMapping("/ex04")
    //public String ex04(SampleDTO dto, int page){
    //page가 뷰로 넘어가도록 수정
    public String ex04(SampleDTO dto,  @ModelAttribute("page") int page){
        log.info("/ex04 ===================");
        log.info("dto: " + dto);
        log.info("page: " + page);
        return "sample/ex04";
    }

    //SampleController에 다음과 같이 정의되어 있을 때, 이를 위한 뷰 파일을 작성하세요.
    @GetMapping("/ex05")
    public void ex05() {
        log.info("/ex05........");
    }

    //SampleController에 다음을 처리하는 ex06 메서드를작성하세요.
    //  ○ GET으로/sample/ex06 요청시 /sample/ex06-2로 리다이렉트
    //  ○ 리다이렉트될때name=AAA, age=10을 쿼리문자열에 추가
    @GetMapping("/ex06")
    public String ex06(RedirectAttributes ra) {
        log.info("/ex06................");
        ra.addAttribute("name", "AAA");
        ra.addAttribute("age", 10);
        return "redirect:/sample/ex06-2";
    }

    //업로드된 파일 화면으로 불러오기 위함.
    @GetMapping("/exUpload")
    public void exUpload() {
        log.info("/exUpload..........");
    }

    //base64 인코딩
    @PostMapping("/exUploadPost")
    public void exUploadPost(ArrayList<MultipartFile> files) {
        for(MultipartFile file : files) {
            log.info("----------------------------------");
            log.info("name:" + file.getOriginalFilename()); // 윈도우OS: 한글파일명인경우깨짐
            log.info("size:" + file.getSize());
        }
    }
}
