package shopping.flowershop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shopping.flowershop.dto.MemberDto;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String index(Model model, Principal principal){


        if(principal == null){
            model.addAttribute("message",null);
        }else{
            model.addAttribute("message","" + principal.getName()+"회원님 환영합니다.");
        }

        return "index";
    }

    @GetMapping("/member/login")
    public String loginPage(Model model){
        return "login";
    }

    @GetMapping("/member/signup")
    public String signupPage(Model model){
        model.addAttribute("memberForm",new MemberDto());
        return "signup";
    }


    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal){
        //model.addAttribute("message","Hello spring security");
        return "index";
    }

    @GetMapping("/entrepreneur")
    public String entrepreneur(Model model,Principal principal){
        //model.addAttribute("message","Hello Spring security");
        return "index";
    }
}
