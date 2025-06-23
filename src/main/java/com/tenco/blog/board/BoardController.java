package com.tenco.blog.board;



import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.List;

@RequiredArgsConstructor
@Controller // IoC 대상 - 싱글톤 패턴으로 관리 됨
public class BoardController {

    //DI 처리
    private final BoardRepository boardRepository;

    @GetMapping("/")
    public String index(HttpServletRequest request) {

        // 1. 게시글 목록 조회
        List<Board> boardList = boardRepository.findByAll();
        // 2. 생각해 볼 사항 - Board 엔티티에는 User 엔티티와 연관관계 중
        // 연관 관계 호출 확인
        //boardList.get(0).getUser().getUsername();
        // 3 .뷰에 데이터 전달
        request.setAttribute("boardList",boardList);
        return "index";
    }

    /**
     *
     * @param id - 게시글 PK
     * @param request (뷰에 데이터 전달)
     * @return detail.mustache
     */
    @GetMapping("/board/{id}")
    public String detail(@PathVariable(name = "id") Long id,HttpServletRequest request) {

        Board board = boardRepository.findById(id);
        request.setAttribute("board" , board);

        return "board/detail";
    }

}
