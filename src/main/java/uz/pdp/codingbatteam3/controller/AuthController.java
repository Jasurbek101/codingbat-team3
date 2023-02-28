package uz.pdp.codingbatteam3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbatteam3.entity.model.DTO.UserRegisterDTO;
import uz.pdp.codingbatteam3.service.UserService;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "logins";
    }

    @PostMapping("/register")
    public String register(
            @ModelAttribute UserRegisterDTO userRegisterDTO,
            Model model
    ) {
        if (userService.add(userRegisterDTO)) {
            model.addAttribute("user", userService.getByName(
                    userRegisterDTO.getEmail()
            ));
            return "home";
        }
        return "redirect:/auth/register";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user",new UserRegisterDTO());
        return "register";
    }
}
