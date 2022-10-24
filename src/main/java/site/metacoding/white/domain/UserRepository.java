package site.metacoding.white.domain;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository // IOC 등록
public class UserRepository {

    // EntityManager = prepareStatement랑 비슷
    private final EntityManager em;

    public void save(User user) {
        // persistence Context에 영속화 시키기 ==> 자동 flush (트랙잭션 종료시)
        em.persist(user); // insert 쿼리가 자동으로 돌게 됨
    }

    public User findByUsername(String username) {
        return em.createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    } // 쿼리에는 테이블을 대문자로 쓰고, 별칭을 꼭 넣어줘야 함!

}