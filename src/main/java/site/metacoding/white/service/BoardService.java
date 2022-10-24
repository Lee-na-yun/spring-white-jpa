package site.metacoding.white.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.white.domain.Board;
import site.metacoding.white.domain.BoardRepository;
import site.metacoding.white.dto.BoardRequestDto.BoardSaveDto;

// 서비스의 역할
// 1. 트랜잭션 관리
// 2. DTO 변환해서 컨트롤러에게 돌려줘야 함

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional // 꼭 붙이기! jpa는 자동으로 트랜젝션이 안걸리기 때문
    public void save(BoardSaveDto boardSaveDto) {
        Board board = new Board();
        board.setTitle(boardSaveDto.getTitle());
        board.setContent(boardSaveDto.getContent());
        board.setUser(boardSaveDto.getServiceDto().getUser());
        boardRepository.save(board); // 여기서는 entity로 받아야함
    }

    public Board findById(Long id) {
        return boardRepository.findById(id);
    }

    @Transactional // select아니라서 붙여야함!
    public void update(Long id, Board board) {
        Board boardPS = boardRepository.findById(id); // 영속화 됨 => PC에 board가 들어가있음
        boardPS.setTitle(board.getTitle());
        boardPS.setContent(board.getContent());
        // boardPS.setAuthor(board.getAuthor()); // 영속화된 데이터를 수정함
    } // 트랜잭션 종료시 ==> 더티체킹을 함

    @Transactional
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }
}
