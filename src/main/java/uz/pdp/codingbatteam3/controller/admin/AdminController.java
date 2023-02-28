package uz.pdp.codingbatteam3.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("user/add")
    public String getUserAddPage(Model model) {
        model.addAttribute("add",true);
        return "adminUser";
    }
}
