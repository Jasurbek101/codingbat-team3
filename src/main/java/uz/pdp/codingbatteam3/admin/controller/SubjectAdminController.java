//package uz.pdp.codingbatteam3.admin.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import uz.pdp.codingbatteam3.service.SubjectService;
//
//@Controller
//@RequestMapping("/admin/subject")
//@RequiredArgsConstructor
//public class SubjectAdminController {
//
//    private final SubjectService subjectService;
//
//    @PreAuthorize("hasRole('ADMIN') or hasAuthority('SUBJECT_ADD')")
//    @PostMapping("/add")
//    public String addSubject(
//            @RequestParam("name") String name
//    ) {
//        subjectService.addSubject(name);
//        return "";
//    }
//    @GetMapping("/list")
//    public String getSubjectList(
//            Model model
//    ){
//        model.addAttribute("subject_list", subjectService.getSubjectList());
//        return "";
//    }
//
//    @PreAuthorize("hasRole('ADMIN') or hasAuthority('SUBJECT_EDIT')")
//    @PutMapping("/edit")
//    public String editSubject(
//            @RequestParam("old_name") String oldName,
//            @RequestParam("new_name") String newName
//    ){
//        subjectService.editSubject(oldName,newName);
//        return "";
//    }
//
//    @PreAuthorize("hasRole('ADMIN') or hasAuthority('SUBJECT_DELETE')")
//    @DeleteMapping("/{title}")
//    public String deleteSubject(
//            @PathVariable String title
//    ){
//        subjectService.deleteSubject(title);
//        return "";
//    }
//}
