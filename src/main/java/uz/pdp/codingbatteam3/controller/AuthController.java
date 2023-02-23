package uz.pdp.codingbatteam3.controller;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
        return "login";
    }

    @PostMapping("/register")
    public String register(
            @ModelAttribute UserRegisterDTO userRegisterDTO,
            @RequestParam MultipartFile logo,
            Model model
            ) {
        if (userService.add(userRegisterDTO,logo)) {
            model.addAttribute("user", userService.getByName(
                    userRegisterDTO.getEmail()
            ));
            return "home";
        }
        return "redirect:/auth/register";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
