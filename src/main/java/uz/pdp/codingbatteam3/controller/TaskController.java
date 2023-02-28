package uz.pdp.codingbatteam3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.codingbatteam3.entity.SubjectEntity;
import uz.pdp.codingbatteam3.entity.TaskEntity;
import uz.pdp.codingbatteam3.service.SubjectService;
import uz.pdp.codingbatteam3.service.TaskService;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final SubjectService subjectService;

    @GetMapping("{subjectName}/{topicName}/{taskName}")
    public String getTask(
            Model model,
            @PathVariable String subjectName,
            @PathVariable String topicName,
            @PathVariable String taskName
    ){
        List<SubjectEntity> subjectList = subjectService.list();
        model.addAttribute("subjectList", subjectList);
        model.addAttribute("topicName",topicName);
        TaskEntity taskByName = taskService.getByName(taskName);
        model.addAttribute("task",taskByName);
        return "taskItem";
    }
}
