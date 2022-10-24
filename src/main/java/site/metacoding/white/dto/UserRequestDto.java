package site.metacoding.white.dto;

import lombok.Getter;
import lombok.Setter;

public class UserRequestDto {

    @Setter
    @Getter
    public static class JoinDto { // 게시글쓰기dto
        private String username;
        private String password;

        // 클라이언트한테 받는게 아님!
        @Setter
        @Getter
        public class ServiceDto {
        }

        private ServiceDto serviceDto;

        public void newInstance() {
            serviceDto = new ServiceDto();
        }

    }
}
