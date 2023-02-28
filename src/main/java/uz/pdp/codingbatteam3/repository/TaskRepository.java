package uz.pdp.codingbatteam3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.codingbatteam3.entity.TaskEntity;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskEntity,Integer> {
    Optional<TaskEntity> findByName(String name);
}
