package uz.pdp.codingbatteam3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatteam3.common.exception.RecordAlreadyExistException;
import uz.pdp.codingbatteam3.common.exception.RecordNotFoundException;
import uz.pdp.codingbatteam3.entity.TaskEntity;
import uz.pdp.codingbatteam3.entity.model.DTO.TaskRequestDTO;
import uz.pdp.codingbatteam3.repository.TaskRepository;
import uz.pdp.codingbatteam3.service.badMessages.BadMessages;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService implements BaseService<TaskRequestDTO, TaskEntity>, BadMessages {
    private final TaskRepository taskRepository;

    @Override
    public List<TaskEntity> list() {
        List<TaskEntity> taskEntityList = taskRepository.findAll();
        if (taskEntityList.isEmpty()){
            throw new NullPointerException(String.format(TOPIC_LIST_NULL));
        }
        return taskEntityList;
    }

    @Override
    public boolean add(TaskRequestDTO taskRequestDTO) {
        Optional<TaskEntity> taskEntity = taskRepository.findByName(taskRequestDTO.getName());
        if (taskEntity.isPresent()){
            throw new RecordAlreadyExistException(String.format(TASK_ALREADY_EXIST,taskRequestDTO.getName()));
        }
        taskRepository.save(TaskEntity.of(taskRequestDTO));
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        Optional<TaskEntity> taskEntity = taskRepository.findById(id);
        if (taskEntity.isEmpty()){
            throw new RecordNotFoundException(String.format(TASK_NOT_FOUND,id));
        }
        taskRepository.deleteById(id);
        return true;
    }

    @Override
    public TaskEntity update(Integer id, TaskRequestDTO taskRequestDTO) {
        Optional<TaskEntity> taskEntity = taskRepository.findById(id);
        if (taskEntity.isEmpty()){
            throw new RecordNotFoundException(String.format(TASK_NOT_FOUND,id));
        }
        return taskRepository.save(TaskEntity.of(taskRequestDTO));
    }

    @Override
    public TaskEntity get(Integer id) {
        Optional<TaskEntity> taskEntity = taskRepository.findById(id);
        if (taskEntity.isEmpty()){
            throw new RecordNotFoundException(String.format(TASK_NOT_FOUND,id));
        }
        return taskEntity.get();
    }

    @Override
    public TaskEntity getByName(String name) {
        Optional<TaskEntity> taskEntity = taskRepository.findByName(name);
        if (taskEntity.isEmpty()){
            throw new RecordNotFoundException(String.format(TASK_NOT_FOUND,name));
        }
        return taskEntity.get();
    }
}
