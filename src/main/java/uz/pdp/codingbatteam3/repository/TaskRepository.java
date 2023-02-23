package uz.pdp.codingbatteam3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.codingbatteam3.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity,Integer> {

}
