package sso.mac.alcoholic.comm.account;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sso.mac.alcoholic.comm.entity.Account;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    ModelMapper modelMapper;

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


    @GetMapping("/getUser/{username}")
    public @ResponseBody ResponseEntity<Account> getUser(@PathVariable String username){
        Account user = accountService.getUser(username);

        return new ResponseEntity<Account>(user, HttpStatus.OK);
    }


    @GetMapping("/hateos/getUser/{username}")
    public @ResponseBody ResponseEntity<AccountResource> getUserByHateos(@PathVariable String username){
        Account user = accountService.getUser(username);
        AccountDTO accountDTO = modelMapper.map(user, AccountDTO.class);

        //HATEOS Resource 생성 : json converter시 자동으로 hateos 설정 처리
        AccountResource accountResource = new AccountResource(accountDTO);
        //SELF Link
        accountResource.add(linkTo(AccountController.class).slash("getUser").slash(username).withSelfRel());
        //Profile
        accountResource.add(new Link("/docs/account.html#resources-account-get").withRel("profile"));
        //Next Link
        accountResource.add(linkTo(AccountController.class).withRel("create-account"));

        return ResponseEntity.ok(accountResource);
    }

    @GetMapping("/listUser")
    public @ResponseBody ResponseEntity<List<Account>> accountList(){
        List<Account> listAccount = accountService.accountList();

        return new ResponseEntity<List<Account>>(listAccount, HttpStatus.OK);
    }

    @GetMapping("/hateos/listUser")
    public @ResponseBody ResponseEntity<List<Account>> accountListByHateos(){
        List<Account> listAccount = accountService.accountList();

        return new ResponseEntity<List<Account>>(listAccount, HttpStatus.OK);
    }
}
