package kopo.poly.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kopo.poly.dto.NoticeDTO;
import kopo.poly.service.INoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
/*
스프링 프레임워큰 기본적으로 lagback을 채태갷서 로그 처리함
 */
@RequiredArgsConstructor
//@RequestMapping(value = "/notice")
@Controller
public class NoticeController {

    // @RequiredArgsConstructor 를 통해 메모리에 올라간 서비스 객체를 Controller에서 사용할 수 있게 주입함


    private final INoticeService noticeService;


    /*
    게시판 리스트 보여주기
    GetMapping(value = "notice/noticeList") => GET방식을 통해 접속되는 URL이 notice/noticeList 경우 아래 함수
     */
    @GetMapping(value ="/notice/noticeList")
    public String noticeList(ModelMap model)

        throws Exception {

        //로그 찍기 (추후 로그를 통해 이 함수애ㅔ 접근 했는지 파악하기 용이하다
        log.info(this.getClass().getName() + "/.noticeList Strat!");

        //공지사항 리스트 조회하기
        List<NoticeDTO> rList = noticeService.getNoticeList();
        if (rList == null) rList = new ArrayList<>();

        // Java8부터 제공되는 Optional 활용하여 NPE(Null Pointer Exception) 처리
        // List<NoticeDTP> rList = Optional.ofNuillable(noticeService.getNoticeList())
        //          .orElseGet(ArrayList::new);

        //조회된 리스트 결과값 넣어주기
        model.addAttribute("rList", rList);

        //로그찍기
        log.info(this.getClass().getName() + ".noticeList End!");

        // 함수 처리가 끝나고 보여줄 html 파일명
        return "/notice/noticeList";

    }


        /* <p>
           이 함수는 게시판 작성 페이지로 접근하기 위해 만듦
           <p>
           GetMapping(value = "notice/noticeReg") => GET방식을 통해 접속되는 URL이 notice/notice Reg 경우 아래 함수
         */

        @GetMapping(value ="/notice/noticeReg")
        public String NoticeReg() {

            log.info(this.getClass().getName() + ".noticeReg Start!");

            log.info(this.getClass().getName() + ".noticeReg End!");

            //함수 처리가 끝나고 보여줄 html 파일명
            return "/notice/noticeReg";


        }


        /*
        게시판 글 등록
        <p>
        게시글 등록은 Ajax로 호출되기 때문에 결과는 JSON 구주로 전달해야만 함
        JSON 구주로 결과 메시지를 전송하기 위해 @ResonseponseBody 어노테이션 추가함
         */

    @PostMapping(value = "/notice/noticeInsert")
    public String noticeInsert(HttpServletRequest request, ModelMap model, HttpSession session) {


        log.info(this.getClass().getName() + ".noticeInsert Strart!");

        String msg = ""; //메시지 내용
        String url = "/notice/noticeReg"; //이동경로

    }



}






