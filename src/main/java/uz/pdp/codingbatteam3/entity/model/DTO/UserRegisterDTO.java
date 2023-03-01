package uz.pdp.codingbatteam3.entity.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.codingbatteam3.entity.model.Enum.PermissionEnum;
import uz.pdp.codingbatteam3.entity.model.Enum.RoleEnum;

import java.util.List;

@Getter
@Setter
@ToString
public class UserRegisterDTO {
    private int id;
    private String email;
    private String password;
    private List<RoleEnum> roles;
    private List<PermissionEnum> permissions;
    private MultipartFile file;

    @JsonIgnore
    public boolean isUser() {
      return roles == null && permissions == null;
    }

}
