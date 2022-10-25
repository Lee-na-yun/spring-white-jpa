package site.metacoding.white;

import org.junit.jupiter.api.Test;

import site.metacoding.white.domain.Board;
import site.metacoding.white.dto.BoardRespDto;
import site.metacoding.white.dto.BoardRespDto.BoardSaveRespDto;
import site.metacoding.white.dto.BoardRespDto.BoardSaveRespDto.UserDto;

public class BoardSaveRespDtoTest {

    @Test
    public void innerclass_테스트() {
        BoardSaveRespDto boardSaveRespDto = new BoardSaveRespDto(new Board());
    }
}
