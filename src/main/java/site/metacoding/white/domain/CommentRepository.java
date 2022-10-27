package site.metacoding.white.domain;

import java.util.Optional;

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

    // 댓글 삭제
    public void deleteById(Long id) {
        em.createQuery("delete from Comment c where c.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public Comment save(Comment comment) { // 비영속화
        // 인터페이스가 아니기 때문에 형태가 나와야함
        em.persist(comment); // insert 쿼리가 자동으로 돌게 됨 //영속화
        return comment;
    }

    public Optional<Comment> findById(Long id) {
        try {
            Optional<Comment> commentOP = Optional.of(em
                    .createQuery("select c from Comment c where c.id = :id",
                            Comment.class)
                    .setParameter("id", id)
                    .getSingleResult());
            return commentOP;
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
