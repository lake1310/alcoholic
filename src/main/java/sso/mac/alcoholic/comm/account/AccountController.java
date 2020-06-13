package sso.mac.alcoholic.comm.account;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sso.mac.alcoholic.comm.entity.Account;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping(value = "/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/index")
    public String showAnyone(){
        return "index";
    }

    @GetMapping("/admin")
    public ModelAndView showAdmin(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        GrantedAuthority authority = authorities.iterator().next(); // 현재는 한개니깐

        ModelAndView admin = new ModelAndView("admin");
        admin.addObject("account", user);
        admin.addObject("authority", authority);

        return admin;
    }

    @GetMapping("/profile")
    public ModelAndView showProfile(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        ModelAndView profile = new ModelAndView("profile");
        profile.addObject("account", accountService.getAccount(user.getUsername()));

        return profile;
    }

    @PostMapping("/create")
    public @ResponseBody ResponseEntity<Account> accountCreate(@RequestBody AccountDTO accountDTO){
        Account savedAccount = accountService.createAccount(accountDTO);
        return new ResponseEntity<Account>(savedAccount, HttpStatus.OK);
    }

    @GetMapping("/list")
    public @ResponseBody ResponseEntity<List<Account>> accountList(){
        List<Account> listAccount = accountService.accountList();
        return new ResponseEntity<List<Account>>(listAccount, HttpStatus.OK);
    }
}
