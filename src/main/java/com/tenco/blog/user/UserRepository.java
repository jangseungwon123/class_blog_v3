package com.tenco.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor // 생성자 의존 주입
@Repository // IoC 대상 + 싱글톤 패턴으로 관리
public class UserRepository {

     // DI
    // 생성자 의존 주입 -DI
    private final EntityManager em ;

    // 회원정보 저장

    /**
     * 회원 정보 저장 처리
     * @param user (비영속 상태)
     * @return User 엔티티 반환
     */
    @Transactional
    public User save(User user) {
        // 매개변수에 들어오는 user object는 비영속호 된 상태이다.

        em.persist(user); // 영속성 컨텍스트에 user 객체를 관리하기 시작 함

        // 트랜잭션이 끝나면 커밋 시점에 실제 INSERT 쿼리를 실행한다

        return user;
    }

    // 사용자명 중복 체크용 조회 기능
    public User findByUsername(String username) {
//        String jpql = " SELECT *u FROM User u WHERE u.username = :username ";
//        TypedQuery<User> typedQuery = em.createQuery(jpql,User.class);
//        typedQuery.setParameter("username",username);
//        return typedQuery.getSingleResult();
        try{
            String jpql = " SELECT *u FROM User u WHERE u.username = :username ";
            return em.createQuery(jpql,User.class)
                    .setParameter("username",username)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }


}
