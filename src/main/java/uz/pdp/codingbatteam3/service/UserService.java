package uz.pdp.codingbatteam3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatteam3.entity.UserEntity;
import uz.pdp.codingbatteam3.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements BaseService<UserEntity,UserEntity>{
    private final UserRepository userRepository;
    @Override
    public UserEntity getList() {

        return null;
    }

    @Override
    public UserEntity add(UserEntity userEntity) {
        return null;
    }

    @Override
    public UserEntity deleteById(Integer id) {
        return null;
    }

    @Override
    public UserEntity update(Integer id, UserEntity userEntity) {
        return null;
    }

    @Override
    public UserEntity getById(Integer id) {
        return null;
    }
}
