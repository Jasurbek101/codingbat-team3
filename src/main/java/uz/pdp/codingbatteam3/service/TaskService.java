package uz.pdp.codingbatteam3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.codingbatteam3.entity.TaskEntity;
import uz.pdp.codingbatteam3.entity.model.DTO.TaskRequestDTO;
import uz.pdp.codingbatteam3.repository.TaskRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TaskService implements BaseService<TaskRequestDTO, TaskEntity> {
    private final TaskRepository taskRepository;
    @Override
    public List<TaskEntity> list() {
        return null;
    }

    @Override
    public boolean add(TaskRequestDTO taskRequestDTO) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public TaskEntity update(Integer id, TaskRequestDTO taskRequestDTO) {
        return null;
    }

    @Override
    public TaskEntity get(Integer id) {
        return null;
    }

    @Override
    public TaskEntity getByName(String name) {
        return null;
    }
}
