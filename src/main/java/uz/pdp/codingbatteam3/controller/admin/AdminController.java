package uz.pdp.codingbatteam3.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    public String home(){
        return "admin/adminPage";
    }
}
