package site.metacoding.white.domain;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BoardRepository {

    // EntityManager = prepareStatement랑 비슷
    private final EntityManager em;

    public void save(Board board) {
        // 인터페이스가 아니기 때문에 형태가 나와야함
        em.persist(board); // insert 쿼리가 자동으로 돌게 됨
    }

    public Board findById(Long id) {
        Board boardPS = em.createQuery("select b from Board b where b.id = :id", Board.class) // 간단한쿼리 작성할때 유용함(jpql)
                .setParameter("id", id)
                .getSingleResult();
        return boardPS;
    }

    public void deleteById(Long id) {
        em.createQuery("delete b from Board b where b.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public List<Board> findAll() {
        // JPQL문법
        List<Board> boardList = em.createQuery("select b from Board b", Board.class)
                .getResultList();
        return boardList;
    }
}
