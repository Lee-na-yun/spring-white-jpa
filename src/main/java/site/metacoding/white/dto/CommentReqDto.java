package site.metacoding.white.dto;

import javax.swing.text.AbstractDocument.Content;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.white.domain.Board;
import site.metacoding.white.domain.Comment;

public class CommentReqDto {

    @Setter
    @Getter
    public static class CommentSaveReqDto {
        private String content; // 내용
        private SessionUser sessionUser; // 작성자 //서비스로직
        private Long boardId;

        public Comment toEntity(Board board) {
            return Comment.builder()
                    .content(content)
                    .board(board)
                    .user(sessionUser.toEntity())
                    .build();
        }
    }

}
