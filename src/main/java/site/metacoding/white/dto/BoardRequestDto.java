package site.metacoding.white.dto;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.white.domain.Board;

public class BoardRequestDto {

    @Setter
    @Getter
    public static class BoardSaveReqDto { // 게시글쓰기dto
        private String title;
        private String content;
        private SessionUser sessionUser; // 서비스 로직

        public Board toEntity() {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .user(sessionUser.toEntity())
                    .build();
        }
    }

    // 필요한 dto는 이 파일에 추가로 작성하기!

    @Setter
    @Getter
    public static class BoardUpdateReqDto { // 게시글쓰기dto
        private String title;
        private String content;
        private Long id; // 서비스 로직

        public Board toEntity() {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .id(id)
                    .build();
        }
    }

}
