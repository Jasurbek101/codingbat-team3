package uz.pdp.codingbatteam3.entity.model.DTO;

import lombok.Data;
import uz.pdp.codingbatteam3.entity.TaskEntity;

import java.util.List;

@Data
public class TopicRequestDTO {
    private List<TaskEntity> taskEntities;
    private String name;
}
