package uz.pdp.codingbatteam3.controller;

import lombok.RequiredArgsConstructor;
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

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;
    private final SubjectService subjectService;
    private final String java = "Java";

    @GetMapping("")
    public String home(
            Model model,
            Principal principal
            ) {

        if (principal != null) {
            String username = principal.getName();
            UserEntity currentUser = userService.getByName(username);
            model.addAttribute("user",currentUser);
        }
        List<SubjectEntity> subjectEntityList = subjectService.list();
        List<TopicEntity> topicEntityList = subjectService.getByName(java).getTopicEntities();
        model.addAttribute("subjectList", subjectEntityList);
        model.addAttribute("topicList", topicEntityList);
        return "home";
    }

}
