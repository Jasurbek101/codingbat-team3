package uz.pdp.codingbatteam3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatteam3.entity.TopicEntity;

@Service
@RequiredArgsConstructor
public class TopicService implements BaseService<TopicEntity,TopicEntity> {

    @Override
    public TopicEntity getList() {
        return null;
    }

    @Override
    public TopicEntity add(TopicEntity topicEntity) {
        return null;
    }

    @Override
    public TopicEntity deleteById(Integer id) {
        return null;
    }

    @Override
    public TopicEntity update(Integer id, TopicEntity topicEntity) {
        return null;
    }

    @Override
    public TopicEntity getById(Integer id) {
        return null;
    }
}
