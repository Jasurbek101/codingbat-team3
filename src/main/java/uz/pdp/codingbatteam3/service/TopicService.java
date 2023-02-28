package uz.pdp.codingbatteam3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatteam3.common.exception.RecordNotFoundException;
import uz.pdp.codingbatteam3.entity.TopicEntity;
import uz.pdp.codingbatteam3.entity.model.DTO.TopicRequestDTO;
import uz.pdp.codingbatteam3.repository.TopicRepository;
import uz.pdp.codingbatteam3.service.badMessages.BadMessages;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicService implements BaseService<TopicRequestDTO, TopicEntity>, BadMessages {
    private final TopicRepository topicRepository;
    @Override
    public List<TopicEntity> list() {
        List<TopicEntity> topicEntityList = topicRepository.findAll();
        if (topicEntityList.isEmpty()){
            throw  new NullPointerException(TOPIC_LIST_NULL);
        }
        return topicEntityList;
    }

    @Override
    public boolean add(TopicRequestDTO topicRequestDTO) {
        Optional<TopicEntity> foundTopic = topicRepository.findByName(topicRequestDTO.getName());
        if (foundTopic.isPresent()){
            throw new IllegalArgumentException(String.format(TOPIC_ALREADY_EXISTS, topicRequestDTO.getName()));
        }
        topicRepository.save(TopicEntity.of(topicRequestDTO));
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        Optional<TopicEntity> foundTopicEntity = topicRepository.findById(id);
        if (foundTopicEntity.isEmpty()){
            throw new RecordNotFoundException(String.format(TOPIC_NOT_FOUND,id));
        }
        topicRepository.deleteById(id);
        return true;
    }

    @Override
    public TopicEntity update(Integer id, TopicRequestDTO topicRequestDTO) {
        Optional<TopicEntity> foundTopic = topicRepository.findById(id);
        if (foundTopic.isEmpty()){
            throw new RecordNotFoundException(String.format(TOPIC_NOT_FOUND,topicRequestDTO.getName()));
        }
        return topicRepository.save(TopicEntity.of(topicRequestDTO));
    }

    @Override
    public TopicEntity get(Integer id) {
        Optional<TopicEntity> foundTopic = topicRepository.findById(id);
        if (foundTopic.isEmpty()){
            throw new RecordNotFoundException(String.format(TOPIC_NOT_FOUND, id));
        }
        return foundTopic.get();
    }

    @Override
    public TopicEntity getByName(String name) {
        Optional<TopicEntity> foundTopic = topicRepository.findByName(name);
        if (foundTopic.isEmpty()){
            throw new RecordNotFoundException(String.format(TOPIC_NOT_FOUND,name));
        }
        return foundTopic.get();
    }
}
