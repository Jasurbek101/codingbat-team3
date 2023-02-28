package uz.pdp.codingbatteam3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.codingbatteam3.entity.SubjectEntity;
import uz.pdp.codingbatteam3.entity.TaskEntity;
import uz.pdp.codingbatteam3.entity.TopicEntity;
import uz.pdp.codingbatteam3.service.SubjectService;
import uz.pdp.codingbatteam3.service.TopicService;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class TopicController {
    private final TopicService topicService;
    private final SubjectService subjectService;
    @GetMapping("/{subjectName}/{topicName}")
    public String getTaskByTopic(
            Model model,
            @PathVariable String subjectName,
            @PathVariable String topicName
    ){
        List<SubjectEntity> subjectList = subjectService.list();
        TopicEntity topicEntity = topicService.getByName(topicName);
        List<TaskEntity> taskList = topicEntity.getTaskEntities();
        model.addAttribute("taskList",taskList);
        model.addAttribute("subjectList",subjectList);
        model.addAttribute("subjectName",subjectName);
        model.addAttribute("topic",topicEntity);
        return "task";
    }

}
