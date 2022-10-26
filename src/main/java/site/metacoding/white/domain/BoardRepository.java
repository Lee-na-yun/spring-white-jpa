package site.metacoding.white.domain;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BoardRepository {

    // EntityManager = prepareStatement랑 비슷
    private final EntityManager em;

    public Board save(Board board) { // 비영속화
        // 인터페이스가 아니기 때문에 형태가 나와야함
        em.persist(board); // insert 쿼리가 자동으로 돌게 됨 //영속화
        return board;
    }

    /*
     * public Optional<Board> findById(Long id) {
     * // Board boardPS = em
     * // .createQuery("select b from Board b join fetch b.user u where b.id = :id",
     * // Board.class)
     * // .setParameter("id", id)
     * // .getSingleResult();
     * 
     * Optional<Board> boardCP = Optional.of(em
     * .createQuery("select b from Board b where b.id = :id", Board.class) // 간단한쿼리
     * 작성할때 유용함(jpql)
     * .setParameter("id", id)
     * .getSingleResult());
     * return boardCP;
     * }
     */
    public Optional<Board> findById(Long id) {
        try {
            Optional<Board> boardOP = Optional.of(em
                    .createQuery("select b from Board b where b.id = :id",
                            Board.class)
                    .setParameter("id", id)
                    .getSingleResult());
            return boardOP;
        } catch (Exception e) {
            return Optional.empty();
        }

    }

    public void deleteById(Long id) {
        em.createQuery("delete b from Board b where b.id = :id", Board.class)
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
