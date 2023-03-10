package uz.pdp.codingbatteam3.entity;


import jakarta.persistence.*;
import lombok.*;
import uz.pdp.codingbatteam3.entity.model.DTO.TopicRequestDTO;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "topics")
@Builder
public class TopicEntity extends BaseEntity {
    private String name;
    private String description;
    @ManyToOne
    private SubjectEntity subjectEntity;
    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "topicEntity",
            cascade = CascadeType.ALL
    )
    private List<TaskEntity> taskEntities;


    public static TopicEntity of(TopicRequestDTO topicRequestDTO) {
        return TopicEntity.builder()
                .name(topicRequestDTO.getName())
                .taskEntities(topicRequestDTO.getTaskEntities())
                .build();
    }
}
