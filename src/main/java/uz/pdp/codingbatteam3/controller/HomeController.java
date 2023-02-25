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
        model.addAllAttributes(subjectService.getSubjectTopicListAttributes());
        model.addAttribute("username", user != null ? user.getUsername() : "");
        model.addAttribute("logo", user != null ? user.getLogoUrl() : "");
        if (user != null && userService.isSuperAdmin(user)) {
            model.addAllAttributes(userService.getRolePermissionsAttributes(user));
            return "adminPage";
        }
        return "home";
    }
}

