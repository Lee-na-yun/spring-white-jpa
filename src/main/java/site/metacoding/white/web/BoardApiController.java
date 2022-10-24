package site.metacoding.white.web;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.white.domain.Board;
import site.metacoding.white.domain.User;
import site.metacoding.white.dto.BoardRequestDto.BoardSaveDto;
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

    @GetMapping("/board/{id}")
    public Board findById(@PathVariable Long id) {
        return boardService.findById(id);
    }

    /*
     * @PostMapping("/board")
     * public String save(@RequestBody Board board) {
     * boardService.save(board);
     * return "ok";
     * }
     */

    @PostMapping("/v2/board")
    public String saveV2(@RequestBody BoardSaveDto boardSaveDto) {
        User principal = (User) session.getAttribute("principal");
        boardSaveDto.newInstance();
        boardSaveDto.getServiceDto().setUser(principal);
        boardService.save(boardSaveDto); // 컨트롤러는 entity를 알 필요가 없으므로 dto 그대로 넘기기
        return "ok";
    }

}
