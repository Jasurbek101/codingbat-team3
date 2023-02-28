package uz.pdp.codingbatteam3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbatteam3.entity.UserEntity;
import uz.pdp.codingbatteam3.entity.model.DTO.UserRegisterDTO;
import uz.pdp.codingbatteam3.service.UserService;

import java.util.List;

@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public String addUser(
            Model model,
            @ModelAttribute UserRegisterDTO userRegisterDTO
    ) {
        boolean isSuccess = userService.add(userRegisterDTO);
        model.addAttribute("user", userService.getByName(
                userRegisterDTO.getEmail()
        ));
        //before check isSuccess and logic for return
        return "";
    }


    @GetMapping("/list")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
    public String list(
            Model model
    ) {
        List<UserEntity> userEntities = userService.list();
        model.addAttribute("userList", userEntities);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN') or hasRole('ADMIN')")
    public String get(
            Model model,
            @PathVariable Integer id
    ) {
        UserEntity user = userService.get(id);
        model.addAttribute("user", user);
        return "";
    }


    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN') or (hasRole('ADMIN') and hasAuthority('DELETE'))")
    public String delete(
            @PathVariable Integer id
    ) {
        boolean isSuccess = userService.delete(id);

        //before check isSuccess and logic for return
        return "";
    }


    @PostMapping("/update/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN') or (hasRole('ADMIN') and hasAuthority('UPDATE'))")
    public String update(
            @PathVariable Integer id,
            @ModelAttribute UserRegisterDTO userRegisterDTO,
            Model model
    ) {
        UserEntity userEntity = userService.update(id, userRegisterDTO);
        model.addAttribute("user",userEntity);
        return "";
    }
}
