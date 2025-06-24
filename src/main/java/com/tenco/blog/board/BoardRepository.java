package com.tenco.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor // 생성자 자동 생성 + 멤버 변수 확인 -->DI처리
@Repository // IoC 대상이 된다 + 싱글톤 패턴 관리 = 스프링 컨테이너에 들어가 있다.
public class BoardRepository {

    //DI
    private  final EntityManager em;

    /**
     * 게시글 저장 : User와 연관관계를 가진 Board 엔티티 영속화
     * @param board
     * @return
     */
    @Transactional
    public Board save(Board board){
        // 비영속 상태의 Board Object를 영속성 컨텍스트에 저장하면
        em.persist(board);
        // 이후 시점에는 같은 메모리 주소를 가르킨다.
        return board;
    }


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
