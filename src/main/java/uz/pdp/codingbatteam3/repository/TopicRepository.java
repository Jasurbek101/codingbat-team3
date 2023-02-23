package uz.pdp.codingbatteam3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.codingbatteam3.entity.TopicEntity;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<TopicEntity,Integer> {
    Optional<TopicEntity> findByName(String name);
}
