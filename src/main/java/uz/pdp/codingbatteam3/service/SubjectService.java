package uz.pdp.codingbatteam3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatteam3.common.exception.RecordNotFoundException;
import uz.pdp.codingbatteam3.entity.SubjectEntity;
import uz.pdp.codingbatteam3.entity.model.DTO.SubjectRequestDTO;
import uz.pdp.codingbatteam3.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectService implements BaseService<SubjectRequestDTO, SubjectEntity> {
    private final SubjectRepository subjectRepository;

    @Override
    public List<SubjectEntity> list() {
        List<SubjectEntity> subjectList = subjectRepository.findAll();
        if (subjectList.isEmpty()) throw new NullPointerException("Empty list");
        return subjectList;
    }

    @Override
    public SubjectEntity getByName(String title) {
        Optional<SubjectEntity> optionalSubjectEntity = subjectRepository.findByTitle(title);
        return optionalSubjectEntity.orElseThrow(() ->
                new RecordNotFoundException(String.format("subject %s not found", title))
        );
    }

    @Override
    public boolean add(SubjectRequestDTO subjectRequestDTO) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public SubjectEntity update(Integer id, SubjectRequestDTO subjectRequestDTO) {
        return null;
    }

    @Override
    public SubjectEntity get(Integer id) {
        return null;
    }
}
