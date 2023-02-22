package uz.pdp.codingbatteam3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatteam3.common.exception.RecordNotFoundException;
import uz.pdp.codingbatteam3.entity.UserEntity;
import uz.pdp.codingbatteam3.entity.model.DTO.UserRegisterDTO;
import uz.pdp.codingbatteam3.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements BaseService<UserRegisterDTO, UserEntity> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity getByEmail(String email){
        Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(email);
        if (optionalUserEntity.isEmpty())
            throw new RecordNotFoundException(String.format("user %s not found", email));
        return optionalUserEntity.get();
    }

    @Override
    public List<UserEntity> list() {
        List<UserEntity> userEntityList = userRepository.findAll();
        if (userEntityList.isEmpty()) throw new NullPointerException("Empty list");

        return userEntityList;
    }

    @Override
    public boolean add(UserRegisterDTO userRegisterDTO) {
        Optional<UserEntity> userEntity =
                userRepository.findByEmail(userRegisterDTO.getEmail());

        if (userEntity.isPresent())
            throw new IllegalArgumentException(String.format("user %s already exists", userRegisterDTO.getEmail()));
        UserEntity savedUserEntity = UserEntity.of(userRegisterDTO);
        savedUserEntity.setPassword(passwordEncoder.encode(
                userRegisterDTO.getPassword()
        ));
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
        UserEntity savedUserEntity = userRepository.save(UserEntity.of(userRegisterDTO));
        return savedUserEntity;
    }

    @Override
    public UserEntity get(Integer id) {
        Optional<UserEntity> optionalUserEntity =
                userRepository.findById(id);
        if (optionalUserEntity.isEmpty())
            throw new RecordNotFoundException(String.format("user %s not found", id));
        return optionalUserEntity.get();
    }


}
