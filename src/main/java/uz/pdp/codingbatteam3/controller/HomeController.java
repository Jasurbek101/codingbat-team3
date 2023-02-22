package uz.pdp.codingbatteam3.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.codingbatteam3.entity.UserEntity;

@RequestMapping("/")
@Controller
public class HomeController {

    @GetMapping
    public String home(
            @AuthenticationPrincipal UserEntity userEntity,
            Model model
    ){
        if (userEntity != null)
        model.addAttribute("user",userEntity);
        return "home";
    }
}
