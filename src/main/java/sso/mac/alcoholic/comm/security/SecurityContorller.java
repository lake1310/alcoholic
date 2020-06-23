package sso.mac.alcoholic.comm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sso.mac.alcoholic.comm.account.AccountService;

import java.util.Collection;

@Controller
@RequestMapping("/security")
public class SecurityContorller {

    @Autowired
    private AccountService accountService;

    @GetMapping("/home")
    public String home(){
        return "security/home";
    }

    @GetMapping("/admin")
    public ModelAndView showAdmin(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        GrantedAuthority authority = authorities.iterator().next(); // 현재는 한개니깐

        ModelAndView admin = new ModelAndView("security/admin");
        admin.addObject("name", user.getUsername());
        admin.addObject("authority", authority);

        return admin;
    }

    @GetMapping("/profile")
    public ModelAndView showProfile(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        ModelAndView profile = new ModelAndView("security/profile");
        profile.addObject("account", accountService.getAccount(user.getUsername()));

        return profile;
    }
}
