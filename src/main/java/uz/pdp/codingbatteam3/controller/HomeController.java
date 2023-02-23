package uz.pdp.codingbatteam3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.codingbatteam3.entity.SubjectEntity;
import uz.pdp.codingbatteam3.entity.TopicEntity;
import uz.pdp.codingbatteam3.entity.UserEntity;
import uz.pdp.codingbatteam3.service.SubjectService;
import uz.pdp.codingbatteam3.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
    private final SubjectService subjectService;
    private final UserService userService;
    private final String java = "JAVA";
    @GetMapping("")
    public String home(
            Model model,
            @AuthenticationPrincipal UserEntity user
            ) {
        List<SubjectEntity> subjectEntityList = subjectService.list();
        List<TopicEntity> topicEntityList = subjectService.getByName(java).getTopicEntities();
        model.addAttribute("subjectList", subjectEntityList);
        model.addAttribute("topicList", topicEntityList);
        if (user != null && user.getRoleEnumList().stream().anyMatch(roleEnum -> {
            if (roleEnum.name().equals("SUPER_ADMIN")) {
                return true;
            }
            return false;
        })) {
            model.addAttribute("roles",user.getRoleEnumList());
            model.addAttribute("permissions",user.getPermissionEnumList());
            model.addAttribute("users",userService.list());
            return "adminUser";
        }
        return "home";
    }

}
