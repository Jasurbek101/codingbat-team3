package uz.pdp.codingbatteam3.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.codingbatteam3.entity.UserEntity;
import uz.pdp.codingbatteam3.entity.model.DTO.UserRegisterDTO;
import uz.pdp.codingbatteam3.service.UserService;

import java.util.List;

import static uz.pdp.codingbatteam3.entity.model.Enum.PermissionEnum.*;
import static uz.pdp.codingbatteam3.entity.model.Enum.RoleEnum.ADMIN;
import static uz.pdp.codingbatteam3.entity.model.Enum.RoleEnum.USER;
import static uz.pdp.codingbatteam3.model.PermissionEnum.SUPER_ADMIN;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/user")
public class AdminController {
    private final UserService userService;
    @GetMapping("/add")
    public String getUserAddingPage(Model model) {
        model.addAttribute("add",true);
        model.addAttribute("user",new UserRegisterDTO());
        model.addAttribute("roles", List.of(SUPER_ADMIN,ADMIN,USER));
        model.addAttribute("permissions", List.of(READ,DELETE,UPDATE,CREATE));
        return "adminUser";
    }

    @PostMapping("/add")
    public String addUser(Model model,@ModelAttribute UserRegisterDTO user){
        userService.add(user);
        model.addAttribute("users",userService.list());
        return "adminUser";
    }

    @GetMapping("/update/{id}")
    public String getUserUpdatePage(
                             @PathVariable("id") int id,
                             Model model){
        UserRegisterDTO user = userService.userRegisterDTOBuilder(userService.get(id));
        model.addAttribute("updatingUser",user);
        model.addAttribute("update", true);
        model.addAttribute("roles", List.of(SUPER_ADMIN,ADMIN,USER));
        model.addAttribute("permissions", List.of(READ,DELETE,UPDATE,CREATE));
        return "adminUser";
    }

    @PostMapping("/update")
    public String updateUser(Model model,@ModelAttribute UserRegisterDTO user){
        userService.updateAdmin(user.getId(),user);
        model.addAttribute("users",userService.list());
        return "redirect:/";
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(Model model,@PathVariable("id") int id){
        userService.delete(id);
        model.addAttribute("users", userService.list());
        return "redirect:/";
    }
}
