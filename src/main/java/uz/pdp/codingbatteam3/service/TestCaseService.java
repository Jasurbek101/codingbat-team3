package uz.pdp.codingbatteam3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatteam3.entity.TestCaseEntity;

@Service
@RequiredArgsConstructor
public class TestCaseService implements BaseService<TestCaseEntity,TestCaseEntity>{
    @Override
    public TestCaseEntity getList() {
        return null;
    }

    @Override
    public TestCaseEntity add(TestCaseEntity testCaseEntity) {
        return null;
    }

    @Override
    public TestCaseEntity deleteById(Integer id) {
        return null;
    }

    @Override
    public TestCaseEntity update(Integer id, TestCaseEntity testCaseEntity) {
        return null;
    }

    @Override
    public TestCaseEntity getById(Integer id) {
        return null;
    }
}
