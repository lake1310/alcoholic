package sso.mac.alcoholic.comm.account;

import org.springframework.data.jpa.repository.JpaRepository;
import sso.mac.alcoholic.comm.entity.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);
}
