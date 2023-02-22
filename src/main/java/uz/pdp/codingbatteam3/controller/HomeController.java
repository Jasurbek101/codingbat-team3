package uz.pdp.codingbatteam3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.codingbatteam3.entity.SubjectEntity;
import uz.pdp.codingbatteam3.service.SubjectService;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
    private final SubjectService subjectService;
    private final String java = "Java";
    @GetMapping
    public String home(Model model){
        List<SubjectEntity> subjectList = subjectService.list();
        model.addAttribute("subjectList",subjectList);
        model.addAttribute("topicList",subjectService.getByTitle(java).getTopicEntities());
        return "home";
    }

}
