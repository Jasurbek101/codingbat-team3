package uz.pdp.codingbatteam3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.codingbatteam3.entity.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity,Integer> {

}
