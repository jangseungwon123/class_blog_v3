package com.tenco.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor // 생성자 자동 생성 + 멤버 변수 확인 -->DI처리
@Repository // IoC 대상이 된다 + 싱글톤 패턴 관리 = 스프링 컨테이너에 들어가 있다.
public class BoardRepository {

    //DI
    private  final EntityManager em;

    /**
     * 전체 게시글 조회
     */
    public List<Board> findByAll() {// JPQL
        String jpql = "SELECT b FROM Board b ORDER BY b.id DESC";
        TypedQuery query = em.createQuery(jpql, Board.class);
        List<Board> boardList = query.getResultList();
        return boardList;
    }

    /**
     *  게시글 단건 조회 (PK 기준)
     * @param id : Board 엔티티에 ID 값
     * @return : Board 엔티티
     */
    public Board findById(Long id) {
        // 조회 - PK 조회는 무조건 엔티티 매니저 활용이 이득임
        Board board = em.find(Board.class, id);

        return board;
    }


}
