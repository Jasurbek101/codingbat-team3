package uz.pdp.codingbatteam3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.codingbatteam3.entity.UserEntity;
import uz.pdp.codingbatteam3.service.SubjectService;
import uz.pdp.codingbatteam3.service.UserService;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
    private final SubjectService subjectService;
    private final UserService userService;

    @GetMapping("")
    public String home(
            Model model,
            @AuthenticationPrincipal UserEntity user
    ) {
        model.addAllAttributes(subjectService.getSubjectAndTopicListAttributes());
        model.addAllAttributes(userService.getUsernameLogoUrlAttributes(user));
        if (user != null && userService.isAnyAdmin(user)) {
            model.addAllAttributes(userService.getRolePermissionsAttributes(user));
            model.addAttribute("show",false);
            return "adminUser";
        }
        return "home";
    }

    @GetMapping("home")
    public String getHome( Model model,
                           @AuthenticationPrincipal UserEntity user){
        model.addAllAttributes(subjectService.getSubjectAndTopicListAttributes());
        model.addAllAttributes(userService.getUsernameLogoUrlAttributes(user));
        model.addAttribute("ADMIN", user != null && userService.isAnyAdmin(user));
        return "home";
    }
}

