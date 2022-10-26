package site.metacoding.white.domain;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Repository // IOC 등록
public class CommentRepository {
    // 댓글 등록, 댓글 수정, 댓글 삭제

    private final EntityManager em;

    public void deleteById(Long id) {
        em.createQuery("delete b from Board b where b.id = :id", Board.class)
                .setParameter("id", id)
                .executeUpdate();
    }

    public Comment save(Comment comment) { // 비영속화
        // 인터페이스가 아니기 때문에 형태가 나와야함
        em.persist(comment); // insert 쿼리가 자동으로 돌게 됨 //영속화
        return comment;
    }
}
