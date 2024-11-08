package pilot.instagram.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import pilot.instagram.domain.user.UserController;
import pilot.instagram.domain.user.request.UserRequest;
import pilot.instagram.domain.user.service.UserService;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("신규 회원을 등록한다.")
    void saveUser() throws Exception {
        // given
        String id = UUID.randomUUID().toString();
        UserRequest userRequest = UserRequest.builder().id(id).name("이기태").build();


        // when, then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/new")
                        .content(objectMapper.writeValueAsString(userRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.code").value(201))
                .andExpect(jsonPath("$.status").value("CREATED"));
    }

    @Test
    @DisplayName("중복된 아이디로 신규 유저 등록 시 실패한다.")
    void saveDuplicateUser() throws Exception {
        // given
        String id = UUID.randomUUID().toString();
        UserRequest userRequest = UserRequest.builder().id(id).name("이기태").build();

        // when, then
        userService.saveUser(userRequest);
        doThrow(new IllegalArgumentException("DUPLICATE_ID"))
                .when(userService).saveUser(argThat(req -> req.getId().equals(id)));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/new")
                        .content(objectMapper.writeValueAsString(userRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("DUPLICATE_ID"));
    }

}
