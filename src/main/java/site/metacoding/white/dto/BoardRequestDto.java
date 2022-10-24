package site.metacoding.white.dto;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.white.domain.User;

public class BoardRequestDto {

    @Setter
    @Getter
    public static class BoardSaveDto { // 게시글쓰기dto
        private String title;
        private String content;
        private User user;
    }

    // 필요한 dto는 이 파일에 추가로 작성하기!

}
