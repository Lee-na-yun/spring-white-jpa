package site.metacoding.white.web;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import site.metacoding.white.domain.Board;
import site.metacoding.white.domain.BoardRepository;
import site.metacoding.white.domain.Comment;
import site.metacoding.white.domain.CommentRepository;
import site.metacoding.white.domain.User;
import site.metacoding.white.domain.UserRepository;
import site.metacoding.white.dto.BoardRequestDto.BoardSaveReqDto;
import site.metacoding.white.dto.SessionUser;
import site.metacoding.white.util.SHA256;

@ActiveProfiles("test")
@Sql("classpath:truncate.sql") // 메서드 실행직전에 data를 다 날리고 truncate실행
@Transactional // 트랜잭션 안붙이면 영속성 컨텍스트에서 DB로 flush 안됨(Hibernate사용시)
@AutoConfigureMockMvc // MockMvc = ioc 컨테이너에 등록
@SpringBootTest(webEnvironment = WebEnvironment.MOCK) // 가짜 환경으로 실행
public class BoardApiControllerTest {

    private static final String APPLICATION_JSON = "application/json; charset=utf-8";

    @Autowired
    private MockMvc mvc; // 통신

    @Autowired
    private ObjectMapper om;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private SHA256 sha256; // service를 안쓰고 repository를 쓰니까 필요함

    private MockHttpSession session;

    private static HttpHeaders headers;

    // ---------------------- 여기까지 다 가짜 IOC에 뜸 -----------------------------//

    /*
     * @BeforeAll
     * public static void init() {
     * headers = new HttpHeaders();
     * headers.setContentType(MediaType.APPLICATION_JSON);
     * }
     */

    @BeforeEach // 트랜잭션 발동 시작
    public void sessionInit() {
        session = new MockHttpSession();
        User user = User.builder().id(1L).username("ssar").build();
        session.setAttribute("sessionUser", new SessionUser(user));
    }

    @BeforeEach // 트랜잭션 발동 시작
    public void dataInit() {
        String encPassword = sha256.encrypt("1234");
        User user = User.builder().username("ssar").password(encPassword).build();
        User userPS = userRepository.save(user);

        Board board = Board.builder()
                .title("스프링1강")
                .content("트랜잭션관리")
                .user(userPS)
                .build();
        Board boardPS = boardRepository.save(board);

        Comment comment = Comment.builder()
                .content("내용좋아요")
                .board(boardPS)
                .user(userPS)
                .build();

        commentRepository.save(comment);
    }

    @Test
    public void findById_test() throws Exception {
        // given
        Long id = 1L;

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.get("/board/" + id).accept("application/json; charset=utf-8"));

        // then
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
        resultActions.andExpect(MockMvcResultMatchers.status().isOk()); // 200 체크
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.data.title").value("스프링1강"));
    }

    @Test
    public void save_test() throws Exception {
        // given
        BoardSaveReqDto boardSaveReqDto = new BoardSaveReqDto();
        boardSaveReqDto.setTitle("스프링1강");
        boardSaveReqDto.setContent("트랜잭션관리");

        String body = om.writeValueAsString(boardSaveReqDto);

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders.post("/board").content(body)
                        .contentType(APPLICATION_JSON).accept(APPLICATION_JSON)
                        .session(session));

        // then
        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("디버그 : " + mvcResult.getResponse().getContentAsString());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.code").value("스프링1강"));
    }
}