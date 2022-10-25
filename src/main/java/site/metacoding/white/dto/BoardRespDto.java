package site.metacoding.white.dto;

import lombok.Getter;
import lombok.Setter;

public class BoardRespDto {

    @Setter
    @Getter
    public static class BoardSaveRespDto {
        private Long id;
        private String title;
        private String content;
        private UserDto user;

        @Setter
        @Getter
        public static class UserDto {
            private Long id;
            private String username;
        }

        // private User user; // SessionUser를 써도 됨
    }
}
