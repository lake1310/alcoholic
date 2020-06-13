package sso.mac.alcoholic.comm.account;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sso.mac.alcoholic.comm.constants.AccountConstants;
import sso.mac.alcoholic.comm.entity.Account;
import sso.mac.alcoholic.comm.entity.base.Used;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    AccountRepository repository;

    @Test
    public void baseEntityTest(){
        Account account = Account.builder()
                                .username("hosu")
                                .password("dasdsadas")
                                .nickname("lake")
                                .phoneNumber("01091850581")
                                .role(AccountConstants.roleAdmin)
                                .build();

        Account savedAccount = repository.save(account);

        List<Account> all = repository.findAll();
        assertThat(all.size()).isEqualTo(1);
        System.out.println("===============");
        System.out.println(all.get(0).getUsed());
        System.out.println(all.get(0).getCreated());
        System.out.println("===============");
        assertThat(all.get(0).getUsed()).isEqualTo(Used.Y);

        savedAccount.setNickname("hirit");
        List<Account> updatedAll = repository.findAll();
        assertThat(updatedAll.get(0).getUpdatedBy()).isEqualTo("updater");
        assertThat(all.get(0).getUsed()).isEqualTo(Used.Y);
    }
}