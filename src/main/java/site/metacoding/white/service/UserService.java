package site.metacoding.white.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.white.domain.User;
import site.metacoding.white.domain.UserRepository;
import site.metacoding.white.dto.UserRequestDto.JoinReqDto;
import site.metacoding.white.dto.UserRequestDto.LoginReqDto;

// 서비스의 역할
// 1. 트랜잭션 관리
// 2. DTO 변환해서 컨트롤러에게 돌려줘야 함

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional // transactional을 붙이지 않으면 영속화 되어 있는 객체가 flush가 안됨
    public User save(JoinReqDto joinReqDto) {
        User userPS = userRepository.save(joinReqDto.toEntity());
        System.out.println("ccc:" + userPS.getId());
        return userPS;
        // 트랙잭션 종료 = flush
    }

    @Transactional(readOnly = true) // select는 무조건 readOnly 걸기!!
    public User login(LoginReqDto loginReqDto) {
        User userPS = userRepository.findByUsername(loginReqDto.getUsername());
        if (userPS.getPassword().equals(loginReqDto.getPassword())) {
            return userPS;
        } else {
            throw new RuntimeException("아이디 혹은 패스워드가 잘못 입력되었습니다.");
        }
    } // 트랜잭션 종료

}
