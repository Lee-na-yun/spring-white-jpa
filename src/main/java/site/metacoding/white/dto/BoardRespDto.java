package site.metacoding.white.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.white.domain.Board;
import site.metacoding.white.domain.Comment;
import site.metacoding.white.domain.User;

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

            public UserDto(User user) {
                this.id = user.getId();
                this.username = user.getUsername();
            }
        }

        public BoardSaveRespDto(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.user = new UserDto(board.getUser());
        }
        // private User user; // SessionUser를 써도 됨
    }

    @Setter
    @Getter
    public static class BoardDetailRespDto {
        private Long id;
        private String title;
        private String content;
        private BoardUserDto user; // board의 주인
        private List<CommentDto> comment = new ArrayList<>();

        @Setter
        @Getter
        public static class CommentDto {
            private Long id;
            private String content;
            private CommentUserDto user; // comment의 주인

            public CommentDto(Comment comment) {
                this.id = comment.getId();
                this.content = comment.getContent();
                this.user = new CommentUserDto(comment.getUser());
            }

        }

        @Setter
        @Getter
        public static class CommentUserDto { // 댓글을 쓴 유저
            private Long id;
            private String username;

            public CommentUserDto(User user) {
                this.id = user.getId();
                this.username = user.getUsername();
            }
        }

        @Setter
        @Getter
        public static class BoardUserDto { // 게시글을 쓴 유저
            private Long id;
            private String username;

            public BoardUserDto(User user) {
                this.id = user.getId(); // Lazy 로딩 발생함
                this.username = user.getUsername();
            }
        }

        public BoardDetailRespDto(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.user = new BoardUserDto(board.getUser());

            for (Comment c : board.getComment()) {
                this.comment.add(new CommentDto(c));
            }

            // List<CommentDto> <-- List<Comment> 넣어야함
            /*
             * this.comment = board.getComments().stream().map((comment) -> new
             * CommentDto(comment))
             * .collect(Collectors.toList());
             */

        }

    }

    @Setter
    @Getter
    public static class BoardAllRespDto {
        private Long id;
        private String title;
        private UserDto user;

        @Setter
        @Getter
        public static class UserDto {
            private Long id;
            private String username;

            public UserDto(User user) {
                this.id = user.getId();
                this.username = user.getUsername();
            }
        }

        public BoardAllRespDto(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.user = new UserDto(board.getUser());
        }

    }

    @Setter
    @Getter
    public static class BoardUpdateRespDto {
        private Long id;
        private String title;
        private String content;
        private UserDto user;

        @Setter
        @Getter
        public static class UserDto {
            private Long id;

            public UserDto(User user) {
                this.id = user.getId();
            }
        }

        public BoardUpdateRespDto(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.user = new UserDto(board.getUser());
        }
        // private User user; // SessionUser를 써도 됨
    }

}
