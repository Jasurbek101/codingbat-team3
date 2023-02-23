package uz.pdp.codingbatteam3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatteam3.common.exception.RecordNotFoundException;
import uz.pdp.codingbatteam3.entity.TopicEntity;
import uz.pdp.codingbatteam3.entity.model.DTO.TopicRequestDTO;
import uz.pdp.codingbatteam3.repository.TopicRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TopicService implements BaseService<TopicRequestDTO, TopicEntity> {
    private final TopicRepository topicRepository;
    @Override
    public List<TopicEntity> list() {
        return null;
    }

    @Override
    public boolean add(TopicRequestDTO topicRequestDTO) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public TopicEntity update(Integer id, TopicRequestDTO topicRequestDTO) {
        return null;
    }

    @Override
    public TopicEntity get(Integer id) {
        return null;
    }

    @Override
    public TopicEntity getByName(String name) {
        return topicRepository.findByName(name).orElseThrow(() ->
                new RecordNotFoundException(String.format("topic %s not found", name))
        );
    }
}
