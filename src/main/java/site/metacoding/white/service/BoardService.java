package site.metacoding.white.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.white.domain.Board;
import site.metacoding.white.domain.BoardRepository;
import site.metacoding.white.domain.User;
import site.metacoding.white.domain.UserRepository;
import site.metacoding.white.dto.BoardRequestDto.BoardSaveReqDto;
import site.metacoding.white.dto.BoardRespDto.BoardDetailRespDto;
import site.metacoding.white.dto.BoardRespDto.BoardSaveRespDto;
import site.metacoding.white.dto.BoardRespDto.BoardSaveRespDto.UserDto;

// 서비스의 역할
// 1. 트랜잭션 관리
// 2. DTO 변환해서 컨트롤러에게 돌려줘야 함

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional // 꼭 붙이기! jpa는 자동으로 트랜젝션이 안걸리기 때문
    public BoardSaveRespDto save(BoardSaveReqDto boardSaveReqDto) {

        // 핵심 로직
        Board boardPS = boardRepository.save(boardSaveReqDto.toEntity());

        // DTO 전환
        BoardSaveRespDto boardSaveRespDto = new BoardSaveRespDto(boardPS);

        return boardSaveRespDto;
    }

    @Transactional(readOnly = true) // 세션 종료 안됨
    public BoardDetailRespDto findById(Long id) {
        Optional<Board> boardOP = boardRepository.findById(id); // 오픈인뷰가 false니까 조회후 세션 종료
        if (boardOP.isPresent()) { // null이 없으면 (null값 체크)
            BoardDetailRespDto boardDetailRespDto = new BoardDetailRespDto(boardOP.get());
            return boardDetailRespDto;
        } else {
            throw new RuntimeException("해당" + id + "로 상세보기를 할 수 없습니다.");
        }
    }

    @Transactional // select아니라서 붙여야함!
    public void update(Long id, Board board) {
        Board boardPS = boardRepository.findById(id); // 영속화 됨 => PC에 board가 들어가있음
        boardPS.update(board.getTitle(), board.getContent());
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
