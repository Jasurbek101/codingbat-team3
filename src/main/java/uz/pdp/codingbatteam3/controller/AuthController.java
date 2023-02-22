package uz.pdp.codingbatteam3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.codingbatteam3.entity.dto.UserRegisterDTO;
import uz.pdp.codingbatteam3.service.UserService;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/register")
    public String register(
            @ModelAttribute UserRegisterDTO user
            ){
        if (userService.add(user)) return "redirect:/auth/register";

        return "home";
    }

    @GetMapping("/register")
    public String getRegister(){
        return "register";
    }
}
