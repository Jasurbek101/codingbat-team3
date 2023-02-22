package uz.pdp.codingbatteam3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatteam3.entity.SubjectEntity;

@Service
@RequiredArgsConstructor
public class SubjectService implements BaseService<SubjectEntity,SubjectEntity> {

    @Override
    public SubjectEntity getList() {
        return null;
    }

    @Override
    public SubjectEntity add(SubjectEntity subjectEntity) {
        return null;
    }

    @Override
    public SubjectEntity deleteById(Integer id) {
        return null;
    }

    @Override
    public SubjectEntity update(Integer id, SubjectEntity subjectEntity) {
        return null;
    }

    @Override
    public SubjectEntity getById(Integer id) {
        return null;
    }
}
