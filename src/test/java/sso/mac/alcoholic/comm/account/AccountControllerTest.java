package sso.mac.alcoholic.comm.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.util.Jackson2JsonParser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import sso.mac.alcoholic.comm.common.CommonControllerTest;
import sso.mac.alcoholic.comm.entity.AccountRole;
import sso.mac.alcoholic.comm.oauth.OAuth2Properties;

import java.util.Set;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.hateoas.MediaTypes.HAL_JSON;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//@RunWith(SpringRunner.class)
//@WebMvcTest(AccountController.class)
public class AccountControllerTest extends CommonControllerTest {

//    @Autowired
//    MockMvc mockMvc;

//    @Test
//    public void securityTest() throws Exception {
//        mockMvc.perform(get("/accounts/index").accept(MediaType.TEXT_HTML))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(view().name("index"));
//    }
//
//    @Test
//    public void createAccount() throws Exception {
//        String accountJson = "{\"username\":\"lake\",\"password\":\"1234\"},\"username\":\"hosu\"},\"nickname\":\"lake\"},\"role\":\"ADMIN\"}";
//        mockMvc.perform(post("/accounts/create")
//                .contentType(MediaType.APPLICATION_JSON) //json 타입으로 보낼거고
//                .accept(MediaType.APPLICATION_JSON) //json 타입으로 리턴을 원한다
//                .content(accountJson)) //실제 body
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.username", is(equalTo("lake"))))
//                .andExpect(jsonPath("$.password", is(equalTo("123"))));
//    }

    @Test
    public void getUser() throws Exception{
        this.mockMvc.perform(get("/accounts/getUser/{username}", "lake"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("username").exists())
                .andExpect(jsonPath("password").exists())
                .andDo(document("get-account",
                        links(
                                linkWithRel("self").description("Link to self information"),
                                linkWithRel("profile").description("Link to profile"),
                                linkWithRel("create-account").description("Link to update account information")
                        ),
                        responseFields(
                                fieldWithPath("password").type(String.class).description("Identification of account"),
                                fieldWithPath("username").type(String.class).description("username of account"),
                                fieldWithPath("nickname").type(String.class).description("nickname of account"),
                                fieldWithPath("email").type(String.class).description("email of account"),
                                fieldWithPath("roles").type(Set.class).description("roles of account"),
                                fieldWithPath("_links.self.href").type(String.class).description("Link to this"),
                                fieldWithPath("_links.profile.href").type(String.class).description("Link to profile"),
                                fieldWithPath("_links.create-account.href").type(String.class).description("Link to next step")
                        )
                ));
    }

//    @Test
//    public void createUser() throws Exception{
//        AccountDTO accountDTO = AccountDTO.builder()
//                .password("1234")
//                .username("hateos")
//                .email("hateos@naver.com")
//                .nickname("hal")
//                .roles(Set.of(AccountRole.USER))
//                .build();
//
//        this.mockMvc.perform(post("/accounts/register")
//                        .accept(HAL_JSON)
//                        .contentType(APPLICATION_JSON_UTF8)
//                        .header(AUTHORIZATION, getBearerToken())
//                        .content(objectMapper.writeValueAsString(accountDTO)))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("username").exists())
//                .andExpect(jsonPath("password").exists())
//                .andDo(document("create-account",
//                        links(
//                                linkWithRel("self").description("Link to self information"),
//                                linkWithRel("profile").description("Link to profile"),
//                                linkWithRel("get-account").description("Link to update account information")
//                        ),
//                        requestFields(
//                                fieldWithPath("password").type(String.class).description("Identification of account"),
//                                fieldWithPath("username").type(String.class).description("username of account"),
//                                fieldWithPath("nickname").type(String.class).description("nickname of account"),
//                                fieldWithPath("email").type(String.class).description("email of account"),
//                                fieldWithPath("roles").type(Set.class).description("roles of account")
//                        ),
//                        responseFields(
//                                fieldWithPath("password").type(String.class).description("Identification of account"),
//                                fieldWithPath("username").type(String.class).description("username of account"),
//                                fieldWithPath("nickname").type(String.class).description("nickname of account"),
//                                fieldWithPath("email").type(String.class).description("email of account"),
//                                fieldWithPath("roles").type(Set.class).description("roles of account"),
//                                fieldWithPath("_links.self.href").type(String.class).description("Link to this"),
//                                fieldWithPath("_links.profile.href").type(String.class).description("Link to profile"),
//                                fieldWithPath("_links.get-account.href").type(String.class).description("Link to next step")
//                        )
//                ));
//
//
//    }
//
//    /*
//    oAuth 인증
//     */
//    @Autowired
//    public OAuth2Properties oAuth2Properties;
//
//    private String getBearerToken() throws Exception{
//        return "Bearer " + getAccessToken();
//    }
//
//    private String getAccessToken() throws Exception{
//        ResultActions perform = this.mockMvc.perform(post("/oauht/token")
//                .with(httpBasic(oAuth2Properties.getClientId(), oAuth2Properties.getClientSecret()))
//                .param("username", "lake")
//                .param("password", "1234")
//                .param("grant_type", "password"))
//                .andDo(print());
//
//        var responseBody = perform.andReturn().getResponse().getContentAsString();
//        Jackson2JsonParser parser = new Jackson2JsonParser();
//        return parser.parseMap(responseBody).get("access_token").toString();
//    }
}