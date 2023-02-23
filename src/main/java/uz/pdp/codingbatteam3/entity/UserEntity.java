package uz.pdp.codingbatteam3.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.codingbatteam3.entity.model.DTO.UserRegisterDTO;
import uz.pdp.codingbatteam3.entity.model.Enum.PermissionEnum;
import uz.pdp.codingbatteam3.entity.model.Enum.RoleEnum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static uz.pdp.codingbatteam3.entity.model.Enum.PermissionEnum.READ;
import static uz.pdp.codingbatteam3.entity.model.Enum.RoleEnum.USER;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "users")
public class UserEntity extends BaseEntity implements UserDetails {
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private List<RoleEnum> roleEnumList;
    @Enumerated(EnumType.STRING)
    private List<PermissionEnum> permissionEnumList;

    public static UserEntity of(UserRegisterDTO userRegisterDTO) {
        if (userRegisterDTO.isUser()) {
            return UserEntity
                    .builder()
                    .username(userRegisterDTO.getEmail())
                    .password(userRegisterDTO.getPassword())
                    .roleEnumList(List.of(
                            USER
                    ))
                    .permissionEnumList(List.of(
                            READ
                    ))
                    .build();
        }
        return UserEntity
                .builder()
                .username(userRegisterDTO.getEmail())
                .password(userRegisterDTO.getPassword())
                .roleEnumList(userRegisterDTO.getRoles())
                .permissionEnumList(userRegisterDTO.getPermissions())
                .build();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roleEnumList.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
        permissionEnumList.forEach(permissionEnum -> authorities.add(new SimpleGrantedAuthority(permissionEnum.name())));
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword(){
        return password;
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
