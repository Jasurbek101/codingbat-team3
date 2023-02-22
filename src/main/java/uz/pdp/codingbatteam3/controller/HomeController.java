package uz.pdp.codingbatteam3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uz.pdp.codingbatteam3.entity.SubjectEntity;
import uz.pdp.codingbatteam3.entity.TopicEntity;
import uz.pdp.codingbatteam3.entity.UserEntity;
import uz.pdp.codingbatteam3.service.SubjectService;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
    private final SubjectService subjectService;
    private final String java = "Java";

    @GetMapping
    @ResponseBody
    public String home(
            Model model,
            @AuthenticationPrincipal UserEntity userEntity
    ) {
        List<SubjectEntity> subjectList = subjectService.list();
        List<TopicEntity> javaTopicList = subjectService.getByName(java).getTopicEntities();
        model.addAttribute("subjectList", subjectList);
        model.addAttribute("topicList", javaTopicList);
        model.addAttribute("user",userEntity);
        return "home";
    }

}
