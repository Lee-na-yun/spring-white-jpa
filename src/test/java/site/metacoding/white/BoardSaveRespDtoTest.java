package site.metacoding.white;

import org.junit.jupiter.api.Test;

import site.metacoding.white.dto.BoardRespDto;
import site.metacoding.white.dto.BoardRespDto.BoardSaveRespDto;
import site.metacoding.white.dto.BoardRespDto.BoardSaveRespDto.UserDto;

public class BoardSaveRespDtoTest {

    @Test
    public void innerclass_테스트() {
        BoardSaveRespDto boardSaveRespDto = new BoardSaveRespDto();
        boardSaveRespDto.setId(1L);
        boardSaveRespDto.setTitle("제목1");
        boardSaveRespDto.setContent("내용1");
        boardSaveRespDto.setUser(null);

        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setUsername("ssar");

        boardSaveRespDto.setUser(userDto);
    }
}
