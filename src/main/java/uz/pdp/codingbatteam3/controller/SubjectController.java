package uz.pdp.codingbatteam3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.codingbatteam3.entity.SubjectEntity;
import uz.pdp.codingbatteam3.entity.TopicEntity;
import uz.pdp.codingbatteam3.service.SubjectService;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping("/{subjectTitle}")
    private String getSubjects(@PathVariable String subjectTitle, Model model){
        List<SubjectEntity> subjectList = subjectService.list();
        List<TopicEntity> topicList = subjectService.getByTitle(subjectTitle).getTopicEntities();
        model.addAttribute("subjectList",subjectList);
        model.addAttribute("topicList",topicList);
        return "home";
    }

}
