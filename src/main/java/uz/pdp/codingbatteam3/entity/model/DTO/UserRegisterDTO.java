package uz.pdp.codingbatteam3.entity.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.codingbatteam3.entity.model.Enum.PermissionEnum;
import uz.pdp.codingbatteam3.entity.model.Enum.RoleEnum;

import java.io.File;
import java.util.List;

@Getter
@Setter
public class UserRegisterDTO {
    private String email;
    private String password;
    private List<RoleEnum> roles;
    private List<PermissionEnum> permissions;

    @JsonIgnore
    public boolean isUser() {
      return roles == null && permissions == null;
    }

}
