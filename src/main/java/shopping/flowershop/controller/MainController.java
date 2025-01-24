package shopping.flowershop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.h2.engine.Mode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shopping.flowershop.domain.MemberRole;
import shopping.flowershop.dto.MemberDto;
import shopping.flowershop.service.MemberService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.Objects;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {
    private final MemberService memberService;
    @GetMapping("/")
    public String index(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(memberService.memberAuthorizationValidation(MemberRole.CUSTOMER)){
            UserDetails user = (UserDetails) auth.getPrincipal();
            model.addAttribute("message", user.getUsername());
        }else{
            model.addAttribute("message",null);
        }

        return "index";
    }


    @GetMapping("/member/login")
    public String loginPage(HttpSession session, Model model){
        String errorData = String.valueOf(session.getAttribute("errorMessage"));
        if(!Objects.equals(errorData, "null")){
            model.addAttribute("errorData",errorData);
        }
        return "login";
    }
    @PostMapping("/member/login")
    public String login(String username, String password,RedirectAttributes attributes,Model model){
        try{
            String name = memberService.login(username, password);

            log.info("attribute name = {}",name);
            attributes.addAttribute("name",name);
        }catch (IllegalArgumentException e){
            attributes.addAttribute("errorData",e.getMessage());
        }
        return "/";
    }

    @GetMapping("/member/signup")
    public String signupPage(Model model , @ModelAttribute("fieldErrorResult") HashMap<String,String> fieldErrorResult){
        if(model.getAttribute("memberForm")==null){
            model.addAttribute("memberForm",new MemberDto());
        }
        log.debug(String.valueOf(fieldErrorResult));
        return "signup";
    }
    @GetMapping("/member/user-info")
    public String userInfo(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(memberService.memberAuthorizationValidation(MemberRole.CUSTOMER)){
            UserDetails user = (UserDetails) auth.getPrincipal();
            Long memberId = memberService.findByLoginId(user.getUsername()).getId();
            model.addAttribute("memberId",memberId);
            return "userInfo";
        }
        return "userInfo";
    }
    @GetMapping("/shop-grid")
    public String shopGrid(){
        return "shop-grid";
    }
    @GetMapping("/shop-details")
    public String shopDetails(){
        return "shop-details";
    }

    @GetMapping("/shoping-cart")
    public String shoppingCart(Model model){
        return "shoping-cart";
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
