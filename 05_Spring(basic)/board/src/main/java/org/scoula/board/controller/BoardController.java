package org.scoula.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Log4j2
public class BoardController {
    private final BoardService service;

    @GetMapping("/list") //board/list  - 목록보기(board의 기본화면)
    public void list(Model model){
        log.info("==============================>  BoardController /list");
        model.addAttribute("list", service.getList());
    }

    @GetMapping("/get")  //상세보기 = 조회 기능
    public void get(@RequestParam("no") Long no , Model model){
        log.info("get......" + no);
        model.addAttribute("board", service.get(no));
    }
    @GetMapping("/create")   //이 create은 "글쓰기" 버튼을 눌렀을때, create.jsp를 단지 보여주는 녀석 (당연히 get방식.)
    public void create(Model model){
        log.info("create......GET");
    }

    @PostMapping("/create")  //이 create가 create.jsp에서 내용을 작성완료한 이용자가 확인버튼을 눌렀을때, 해당내용을 서버에 추가하는 녀석.
    public String create(BoardDTO board){
        log.info("create......POST");
        service.create(board);         //서버에 해당내용 넣고 나서,
        return "redirect:/board/list"; //리스트로 리다이렉트 (화면흐름도 참고.)
    }

    @GetMapping("/update") //수정화면으로 넘어가기... GET (상세보기화면에서 가는것 유의.)
    public void update(@RequestParam("no") Long no, Model model){
        log.info("update......GET");
        model.addAttribute("board", service.get(no));  //사실상 상세보기랑 효과 동일. = get이랑 합칠수 있음. 다만 /get으로 get.jsp를 부르느냐, /update로 update.jsp를 부르느냐의 차이만 있을 뿐.
    }
    @PostMapping("/update") //이 update가 update.jsp에서 내용을 수정완료한 이용자가 확인버튼을 눌렀을때, 해당내용을 서버에 반영하는 녀석.
    public String update(BoardDTO board){
        log.info("update......POST");
        service.update(board);
        return "redirect:/board/list"; //리스트로 리다이렉트
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("no") Long no){
        log.info("delete......POST");
        service.delete(no);
        return "redirect:/board/list";
    }
}
