package uz.pdp.codingbatteam3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.codingbatteam3.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,UserEntity> {

    Optional<UserEntity> findByUsername(String username);

}
