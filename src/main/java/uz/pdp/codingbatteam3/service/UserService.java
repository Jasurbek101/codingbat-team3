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

@Service
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

    public Map<String, List<?>> getRolePermissionsAttributes(UserEntity user){
        Map<String, List<?>> listMap = new HashMap<>();
        listMap.put("roles",user.getRoleEnumList());
        listMap.put("permissions",user.getPermissionEnumList());
        listMap.put("users",list());
        return listMap;
    }

    public boolean isSuperAdmin(UserEntity user){
        return user != null && user.getRoleEnumList().stream().anyMatch(roleEnum -> {
            if (roleEnum.name().equals("SUPER_ADMIN")) {
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

        System.out.println(userEntity.toString());
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

    @Override
    public UserEntity get(Integer id) {
        return userRepository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(String.format("user %s not found", id))
        );
    }


}
