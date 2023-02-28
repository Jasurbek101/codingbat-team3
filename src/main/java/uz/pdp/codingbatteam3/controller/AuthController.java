package uz.pdp.codingbatteam3.controller;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import uz.pdp.codingbatteam3.entity.model.DTO.UserRegisterDTO;
import uz.pdp.codingbatteam3.service.UserService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

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
    public RedirectView register(
            @ModelAttribute UserRegisterDTO userRegisterDTO,
            @RequestParam MultipartFile logo,
            Model model
            ) {
        if (userService.add(userRegisterDTO,logo)) {
            model.addAttribute("user", userService.getByName(
                    userRegisterDTO.getEmail()
            ));
            return new RedirectView("/");
        }
        return new RedirectView("/auth/register");
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
