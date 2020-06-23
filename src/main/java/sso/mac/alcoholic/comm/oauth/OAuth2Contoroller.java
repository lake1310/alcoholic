package sso.mac.alcoholic.comm.oauth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sso.mac.alcoholic.comm.entity.Account;

@RestController
public class OAuth2Contoroller {

    @GetMapping("/user")
    public Account getUser(){
        return Account.builder().username("user").build();
    }

    @GetMapping("/got")
    public Account getAdmin(){
        return Account.builder().username("admin").build();
    }
}
