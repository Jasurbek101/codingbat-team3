package uz.pdp.codingbatteam3.entity.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.codingbatteam3.entity.TopicEntity;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRequestDTO {
    private String title;
    private List<TopicEntity> topicEntities;
}
