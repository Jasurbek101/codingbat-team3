package uz.pdp.codingbatteam3.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.codingbatteam3.entity.model.DTO.SubjectRequestDTO;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subjects")
@Builder
public class SubjectEntity extends BaseEntity {
    private String title;
    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "subjectEntity",
            cascade = CascadeType.ALL
    )
    private List<TopicEntity> topicEntities;

    public static SubjectEntity of(SubjectRequestDTO subjectRequestDTO) {
        return SubjectEntity.builder()
                .title(subjectRequestDTO.getTitle())
                .topicEntities(subjectRequestDTO.getTopicEntities())
                .build();
    }

}

