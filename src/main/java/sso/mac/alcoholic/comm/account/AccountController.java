package sso.mac.alcoholic.comm.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sso.mac.alcoholic.comm.entity.Account;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Controller
//@RequestMapping(value = "/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("account", new Account());

        return "login";
    }

    @PostMapping("/loginProcess")
    public String accountConfirm(@Valid Account account){

        return "redirect:/index";
    }

    @GetMapping("/register")
    public String reqAccountResister(Model model){
        model.addAttribute("account", new Account());

        return "register";
    }

    @PostMapping("/register")
    public @ResponseBody ResponseEntity<Account> accountRegister(@RequestBody AccountDTO accountDTO){
        Account savedAccount = accountService.createAccount(accountDTO);

        return new ResponseEntity<Account>(savedAccount, HttpStatus.OK);
    }

    @GetMapping("/list")
    public @ResponseBody ResponseEntity<List<Account>> accountList(){
        List<Account> listAccount = accountService.accountList();

        return new ResponseEntity<List<Account>>(listAccount, HttpStatus.OK);
    }
}
