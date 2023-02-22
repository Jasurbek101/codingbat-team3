package uz.pdp.codingbatteam3.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.codingbatteam3.model.PermissionEnum;
import uz.pdp.codingbatteam3.model.RoleEnum;
import uz.pdp.codingbatteam3.model.dto.UserRegisterDTO;

import java.util.Collection;
import java.util.List;

import static uz.pdp.codingbatteam3.model.PermissionEnum.*;
import static uz.pdp.codingbatteam3.model.RoleEnum.USER;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "users")
public class UserEntity extends BaseEntity implements UserDetails {
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private List<RoleEnum> roleEnum;
    @Enumerated(EnumType.STRING)
    private List<PermissionEnum> permissionEnumList;

    public static UserEntity of(UserRegisterDTO userRegisterDTO) {
        if (userRegisterDTO.isUser()) {
            return UserEntity
                    .builder()
                    .email(userRegisterDTO.getEmail())
                    .password(userRegisterDTO.getPassword())
                    .roleEnum(List.of(
                            USER
                    ))
                    .permissionEnumList(List.of(
                            READ_TOPIC,
                            READ_SUBJECT,
                            READ_QUESTION
                    ))
                    .build();
        }
        return UserEntity
                .builder()
                .email(userRegisterDTO.getEmail())
                .password(userRegisterDTO.getPassword())
                .roleEnum(userRegisterDTO.getRoles())
                .permissionEnumList(userRegisterDTO.getPermissions())
                .build();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
