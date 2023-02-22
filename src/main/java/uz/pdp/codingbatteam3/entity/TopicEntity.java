package uz.pdp.codingbatteam3.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "topics")
public class TopicEntity extends BaseEntity{
    private String name;
    @ManyToOne
    private SubjectEntity subjectEntity;


}
