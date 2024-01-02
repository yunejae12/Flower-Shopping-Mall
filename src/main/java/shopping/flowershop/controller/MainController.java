package shopping.flowershop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shopping.flowershop.dto.MemberDto;
import shopping.flowershop.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {
    private MemberService memberService;
    @GetMapping("/")
    public String index(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getAuthorities().stream().anyMatch( grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_CUSTOMER"))){
            UserDetails user = (UserDetails) auth.getPrincipal();
            model.addAttribute("message", user.getUsername());
        }else{
            model.addAttribute("message",null);
        }

        return "index";
    }


    @GetMapping("/member/login")
    public String loginPage( HttpServletRequest request, Model model){
        String errorData = String.valueOf(request.getSession().getAttribute("errorMessage"));
        if(errorData!="null"){
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
    public String signupPage(Model model){
        model.addAttribute("memberForm",new MemberDto());
        return "signup";
    }
    @GetMapping("/shop-grid")
    public String shopGrid(){
        return "shop-grid";
    }
    @GetMapping("/shop-details")
    public String shopDetails(){
        return "shop-details";
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
