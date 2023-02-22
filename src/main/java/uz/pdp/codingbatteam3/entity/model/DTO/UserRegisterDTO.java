package uz.pdp.codingbatteam3.entity.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import uz.pdp.codingbatteam3.entity.model.Enum.PermissionEnum;
import uz.pdp.codingbatteam3.entity.model.Enum.RoleEnum;

import java.util.List;

@Getter
public class UserRegisterDTO {
    private String email;
    private String password;
    private List<RoleEnum> roles;
    private List<PermissionEnum> permissions;

    @JsonIgnore
    public boolean isUser() {
      return roles.isEmpty() && permissions.isEmpty();
    }

}
