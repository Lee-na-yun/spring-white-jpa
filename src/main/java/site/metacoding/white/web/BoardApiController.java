package site.metacoding.white.web;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.white.domain.Board;
import site.metacoding.white.domain.User;
import site.metacoding.white.dto.ResponseDto;
import site.metacoding.white.dto.SessionUser;
import site.metacoding.white.dto.BoardRequestDto.BoardSaveReqDto;
import site.metacoding.white.dto.BoardRespDto.BoardSaveRespDto;
import site.metacoding.white.service.BoardService;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RestController // json을 return할거라서
public class BoardApiController {

    private final BoardService boardService;
    private final HttpSession session;

    @GetMapping("/board")
    public List<Board> findAll() {
        return boardService.findAll();
    }

    @DeleteMapping("/board/{id}")
    public String delete(@PathVariable Long id) {
        boardService.delete(id);
        return "ok";
    }

    @PutMapping("/board/{id}")
    public String update(@PathVariable Long id, @RequestBody Board board) { // dto가 아닌 entity로 받으면 나중에 힘들어짐!
        boardService.update(id, board);
        return "ok";
    }

    // 게시글 상세보기
    @GetMapping("/board/{id}")
    public ResponseDto<?> findById(@PathVariable Long id) {
        return new ResponseDto<>(1, "ok", boardService.findById(id));
    }

    /*
     * @GetMapping("/v2/board/{id}")
     * public String findByIdV2(@PathVariable Long id) {
     * System.out.println("현재 open-in-view는 true 인가 false 인가 생각해보기!!");
     * Board boardPS = boardService.findById(id);
     * System.out.println("board.id : " + boardPS.getId());
     * System.out.println("board.title : " + boardPS.getTitle());
     * System.out.println("board.content : " + boardPS.getContent());
     * System.out.println("open-in-view가 false이면 Lazy 로딩 못함");
     * return "ok";
     * }
     */

    @PostMapping("/board")
    public ResponseDto<?> save(@RequestBody BoardSaveReqDto boardSaveReqDto) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        boardSaveReqDto.setSessionUser(sessionUser);
        BoardSaveRespDto boardSaveRespDto = boardService.save(boardSaveReqDto); // 컨트롤러는 entity를 알 필요가 없으므로 dto 그대로 넘기기
        return new ResponseDto<>(1, "ok", boardSaveRespDto); // 서비스에는 단 하나의 객체만 전달한다
    }

}
