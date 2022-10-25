package site.metacoding.white.web;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.white.domain.User;
import site.metacoding.white.dto.UserRequestDto.JoinReqDto;
import site.metacoding.white.dto.UserRequestDto.LoginReqDto;
import site.metacoding.white.service.UserService;

@RequiredArgsConstructor
@RestController // json을 return할거라서
public class UserApiController {

    private final UserService userService;
    private final HttpSession session;

    @PostMapping("/join")
    public ResponseEntity<?> save(@RequestBody JoinReqDto JoinReqDto) {
        User userPS = userService.save(JoinReqDto);
        return new ResponseEntity<>(userPS, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    // 인증에 관련된 주소는 도메인명 안붙임! 인증이 안 된 상태라서
    public String login(@RequestBody LoginReqDto loginReqDto) {
        User principal = userService.login(loginReqDto);
        session.setAttribute("principal", principal);
        return "ok";
    }

}
