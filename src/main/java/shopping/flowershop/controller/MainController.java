package shopping.flowershop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(Model model, Principal principal){
        if(principal == null){
            model.addAttribute("message","Welcome to the flowershop!!");
        }else{
            model.addAttribute("message","Hello " + principal.getName()+" Welcome to the flowershop!!");
        }

        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal){
        model.addAttribute("message","Hello spring security");
        return "index";
    }

    @GetMapping("/entrepreneur")
    public String entrepreneur(Model model,Principal principal){
        model.addAttribute("message","Hello Spring security");
        return "index";
    }
}
