package site.metacoding.white.dto;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.white.domain.User;

public class UserRequestDto {

    @Setter
    @Getter
    public static class JoinReqDto { // 인증관련(로그인 전) 로직들은 전부 앞에 엔티티 안붙임!
        private String username;
        private String password;

        public User toEntity() {
            return User.builder().username(username).password(password).build();
        }
    }

    @Setter
    @Getter
    public static class LoginReqDto { // 내부클래스는 꼭 static을 붙여야 외부에 노출됨!
        private String username;
        private String password;
    }
}
