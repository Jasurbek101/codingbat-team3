package uz.pdp.codingbatteam3.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatteam3.common.exception.RecordAlreadyExistException;
import uz.pdp.codingbatteam3.common.exception.RecordNotFoundException;
import uz.pdp.codingbatteam3.entity.UserEntity;
import uz.pdp.codingbatteam3.entity.model.DTO.UserRegisterDTO;
import uz.pdp.codingbatteam3.fileUtils.FileUtil;
import uz.pdp.codingbatteam3.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static uz.pdp.codingbatteam3.entity.model.Enum.RoleEnum.SUPER_ADMIN;

@Service
@SuppressWarnings("deprecation")
@RequiredArgsConstructor
public class UserService implements BaseService<UserRegisterDTO, UserEntity> {
    private final FileUtil fileUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity getByName(String email) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(email);
        return optionalUserEntity.orElseThrow(() ->
                new RecordNotFoundException(String.format("user %s not found", email))
        );
    }

    public Map<String, List<?>> getRolePermissionsAttributes(UserEntity user) {
        Map<String, List<?>> listMap = new HashMap<>();
        listMap.put("roles", user.getRoleEnumList());
        listMap.put("permissions", user.getPermissionEnumList());
        listMap.put("users", list());
        return listMap;
    }

    public Map<String, String> getUsernameLogoUrlAttributes(UserEntity user){
        Map<String , String > listMap = new HashMap<>();
        listMap.put("username",user != null ?user.getUsername() : "");
        listMap.put("logo",user != null ?user.getLogoUrl() : "");
        return listMap;
    }

    public boolean isAnyAdmin(UserEntity user) {
        return user != null && user.getRoleEnumList().stream().anyMatch(roleEnum -> {
            if (roleEnum.name().equals("SUPER_ADMIN") ||
                roleEnum.name().equals("ADMIN")){
                return true;
            }
            return false;
        });
    }

    @Override
    public List<UserEntity> list() {
        List<UserEntity> userEntityList = userRepository.findAll();
        if (userEntityList.isEmpty()) throw new NullPointerException("Empty list");
        return userEntityList;
    }

    @Override
    @SneakyThrows
    public boolean add(UserRegisterDTO userRegisterDTO) {
        Optional<UserEntity> userEntity =
                userRepository.findByUsername(userRegisterDTO.getEmail());

        if (userEntity.isPresent())
            throw new RecordAlreadyExistException(String.format("user %s already exists", userRegisterDTO.getEmail()));

        UserEntity savedUserEntity = UserEntity.of(userRegisterDTO);
        savedUserEntity = fileUtil.saveLogo(savedUserEntity, userRegisterDTO.getFile());
        savedUserEntity.setPassword(
                passwordEncoder.encode(userRegisterDTO.getPassword())
        );
        userRepository.save(savedUserEntity);
        return true;

    }

    @Override
    public boolean delete(Integer id) {
        Optional<UserEntity> userEntity =
                userRepository.findById(id);
        if (userEntity.isEmpty())
            throw new RecordNotFoundException(String.format("user %s not found", id));
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public UserEntity update(Integer id, UserRegisterDTO userRegisterDTO) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty())
            throw new RecordNotFoundException(String.format("user %s not found", id));
        return userRepository.save(UserEntity.of(userRegisterDTO));
    }

    @SneakyThrows
    public void updateAdmin(Integer id, UserRegisterDTO userRegisterDTO) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty())
            throw new RecordNotFoundException(String.format("user %s not found", id));
       UserEntity foundUser = userRegisterDTOCastToUserEntity(userRegisterDTO);
        foundUser = fileUtil.saveLogo(foundUser, userRegisterDTO.getFile());

        userRepository.save(foundUser);
    }

    private UserEntity userRegisterDTOCastToUserEntity(UserRegisterDTO user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setUsername(user.getEmail());
        userEntity.setRoleEnumList(user.getRoles());
        userEntity.setPermissionEnumList(user.getPermissions());
        return userEntity;
    }


    public UserRegisterDTO userRegisterDTOBuilder(UserEntity user){
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setPermissions(user.getPermissionEnumList());
        userRegisterDTO.setRoles(user.getRoleEnumList());
        userRegisterDTO.setEmail(user.getUsername());
        userRegisterDTO.setId(user.getId());
        userRegisterDTO.setPassword(user.getPassword());
        return userRegisterDTO;
    }

    @Override
    public UserEntity get(Integer id) {
        return userRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(String.format("user %s not found", id))
        );
    }


}
