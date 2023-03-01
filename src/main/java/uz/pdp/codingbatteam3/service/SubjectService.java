package uz.pdp.codingbatteam3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatteam3.common.exception.RecordNotFoundException;
import uz.pdp.codingbatteam3.entity.SubjectEntity;
import uz.pdp.codingbatteam3.entity.TopicEntity;
import uz.pdp.codingbatteam3.entity.model.DTO.SubjectRequestDTO;
import uz.pdp.codingbatteam3.repository.SubjectRepository;
import uz.pdp.codingbatteam3.service.badMessages.BadMessages;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class SubjectService implements BaseService<SubjectRequestDTO, SubjectEntity>, BadMessages {
    private final SubjectRepository subjectRepository;

    @Override
    public List<SubjectEntity> list() {
        List<SubjectEntity> subjectList = subjectRepository.findAll();
        if (subjectList.isEmpty()) throw new NullPointerException(SUBJECT_LIST_NULL);
        return subjectList;
    }

    @Override
    public SubjectEntity getByName(String title) {
        Optional<SubjectEntity> subjectEntity = subjectRepository.findByTitle(title);
        if (subjectEntity.isEmpty()){
            throw new RecordNotFoundException(String.format(SUBJECT_NOT_FOUND,title));
        }
        return subjectEntity.get();
    }

    public List<TopicEntity> getTopicList(){
        List<TopicEntity> topicList = getByName("Java").getTopicEntities();
        return topicList;
    }

    public Map<String, List> getSubjectAndTopicListAttributes(){
        Map<String , List> listMap = new ConcurrentHashMap<>();
        listMap.put("topicList", getTopicList());
        listMap.put("subjectList", list());
        return listMap;
    }

    @Override
    public boolean add(SubjectRequestDTO subjectRequestDTO) {
        Optional<SubjectEntity> subjectEntity = subjectRepository.findByTitle(subjectRequestDTO.getTitle());
        if (subjectEntity.isPresent()){
            throw new RecordNotFoundException(String.format(SUBJECT_NOT_FOUND,subjectRequestDTO.getTitle()));
        }
        subjectRepository.save(SubjectEntity.of(subjectRequestDTO));
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        Optional<SubjectEntity> subjectEntity = subjectRepository.findById(id);
        if (subjectEntity.isEmpty()){
            throw new RecordNotFoundException(String.format(SUBJECT_NOT_FOUND,id));
        }
        subjectRepository.deleteById(id);
        return true;
    }

    @Override
    public SubjectEntity update(Integer id, SubjectRequestDTO subjectRequestDTO) {
        Optional<SubjectEntity> subjectEntity = subjectRepository.findById(id);
        if (subjectEntity.isEmpty()){
            throw new RecordNotFoundException(String.format(SUBJECT_NOT_FOUND,id));
        }
       return subjectRepository.save(SubjectEntity.of(subjectRequestDTO));
    }

    @Override
    public SubjectEntity get(Integer id) {
        Optional<SubjectEntity> subjectEntity = subjectRepository.findById(id);
        if (subjectEntity.isEmpty()){
            throw new RecordNotFoundException(String.format(SUBJECT_NOT_FOUND,id));
        }
        return subjectEntity.get();
    }
}
