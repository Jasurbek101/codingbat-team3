package uz.pdp.codingbatteam3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uz.pdp.codingbatteam3.entity.SubjectEntity;
import uz.pdp.codingbatteam3.entity.TopicEntity;
import uz.pdp.codingbatteam3.service.SubjectService;

import java.util.List;

@Controller
@RequestMapping("/subject")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping("/{subjectTitle}")
    private String getSubjects(
            @PathVariable("subjectTitle") String subjectTitle,
            Model model
    ) {
//        List<SubjectEntity> subjectList = subjectService.list();
//        List<TopicEntity> topicList = subjectService.getByName(subjectTitle).getTopicEntities();
//        model.addAttribute("subjectList", subjectList);
//        model.addAttribute("topicList", topicList);
        return "home";
    }

}
