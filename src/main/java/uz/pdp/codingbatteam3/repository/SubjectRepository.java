package uz.pdp.codingbatteam3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.codingbatteam3.entity.SubjectEntity;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<SubjectEntity,Integer> {
    Optional<SubjectEntity> findByTitle(String title);
}
