package sso.mac.alcoholic.comm.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sso.mac.alcoholic.comm.entity.Account;
import sso.mac.alcoholic.comm.util.MapperUtil;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Account getAccount(String username){
        Optional<Account> byUsername = repository.findByUsername(username);
        return byUsername.orElse(new Account());
    }

    public Account createAccount(AccountDTO accountDTO){
        accountDTO.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        Account account = MapperUtil.getInstance().convertValue(accountDTO, Account.class);

        Account savedAccount = repository.save(account);
        return repository.save(account);
    }

    public List<Account> accountList() {
        return repository.findAll();
    }

    public Account getUser(String username) {
        Optional<Account> byUsername = repository.findByUsername(username);
        return byUsername.orElseThrow(() -> new NoSuchElementException());
    }
}
