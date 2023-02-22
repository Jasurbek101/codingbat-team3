package uz.pdp.codingbatteam3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.codingbatteam3.entity.TestCaseEntity;

public interface TestCasesRepository extends JpaRepository<TestCaseEntity,Integer> {
}
