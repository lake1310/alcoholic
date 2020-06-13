package sso.mac.alcoholic.comm.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
//@DataJpaTest
//@SpringBootTest
public class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void securityTest() throws Exception {
        mockMvc.perform(get("/accounts/index").accept(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void createAccount() throws Exception {
        String accountJson = "{\"username\":\"lake\",\"password\":\"1234\"},\"username\":\"hosu\"},\"nickname\":\"lake\"},\"role\":\"ADMIN\"}";
        mockMvc.perform(post("/accounts/create")
                .contentType(MediaType.APPLICATION_JSON) //json 타입으로 보낼거고
                .accept(MediaType.APPLICATION_JSON) //json 타입으로 리턴을 원한다
                .content(accountJson)) //실제 body
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(equalTo("lake"))))
                .andExpect(jsonPath("$.password", is(equalTo("123"))));
    }
}