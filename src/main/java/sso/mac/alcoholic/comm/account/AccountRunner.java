package sso.mac.alcoholic.comm.account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import sso.mac.alcoholic.comm.entity.AccountRole;

import java.util.Set;

@Component
@Slf4j
public class AccountRunner implements ApplicationRunner {

    @Autowired
    AccountController accountController;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("Account Create");
        AccountDTO accountDTO = AccountDTO.builder()
                .password("1234")
                .username("lake")
                .nickname("lake")
                .email("hosu.choi@miracom.co.kr")
                .roles(Set.of(AccountRole.ADMIN, AccountRole.USER))
                .build();

        accountController.accountRegister(accountDTO);
    }
}
