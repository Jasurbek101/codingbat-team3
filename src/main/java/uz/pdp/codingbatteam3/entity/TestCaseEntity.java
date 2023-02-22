package uz.pdp.codingbatteam3.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
@Table(name = "testcases")
public class TestCaseEntity extends BaseEntity {

    @ElementCollection
    private List<String> testCaseQuestionList;
    @ElementCollection
    private List<String> answerList;

    @ManyToOne
    private QuestionEntity questionEntity;

}
