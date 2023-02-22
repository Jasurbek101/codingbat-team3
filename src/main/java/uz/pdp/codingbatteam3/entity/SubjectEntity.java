package uz.pdp.codingbatteam3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "subjects")
public class SubjectEntity extends BaseEntity {

    private String title;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "subjectEntity")
    private List<TopicEntity> topicEntities;

}
