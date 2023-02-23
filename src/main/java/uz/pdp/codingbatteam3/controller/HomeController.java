package uz.pdp.codingbatteam3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.codingbatteam3.service.SubjectService;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
    private final SubjectService subjectService;
    private final String java = "Java";

    @GetMapping("")
    public String home(
            Model model
            ) {
//        List<SubjectEntity> subjectEntityList = subjectService.list();
//        List<TopicEntity> topicEntityList = subjectService.getByName(java).getTopicEntities();
//        model.addAttribute("subjectList", subjectEntityList);
//        model.addAttribute("topicList", topicEntityList);
        return "home";
    }

}
