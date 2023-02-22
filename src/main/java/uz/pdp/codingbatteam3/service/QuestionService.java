package uz.pdp.codingbatteam3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatteam3.entity.QuestionEntity;

@Service
@RequiredArgsConstructor
public class QuestionService implements BaseService<QuestionEntity,QuestionEntity>{

    @Override
    public QuestionEntity getList() {
        return null;
    }

    @Override
    public QuestionEntity add(QuestionEntity questionEntity) {
        return null;
    }

    @Override
    public QuestionEntity deleteById(Integer id) {
        return null;
    }

    @Override
    public QuestionEntity update(Integer id, QuestionEntity questionEntity) {
        return null;
    }

    @Override
    public QuestionEntity getById(Integer id) {
        return null;
    }
}
