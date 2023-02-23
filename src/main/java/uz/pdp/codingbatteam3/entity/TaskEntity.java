package uz.pdp.codingbatteam3.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import uz.pdp.codingbatteam3.entity.model.DTO.TaskRequestDTO;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tasks")
@Builder
public class TaskEntity extends BaseEntity {
   private String name;
   private String data;
   private String methodData;
   @ManyToOne
   private TopicEntity topicEntity;

   public static TaskEntity of(TaskRequestDTO taskRequestDTO) {
      return TaskEntity.builder()
              .name(taskRequestDTO.getName())
              .data(taskRequestDTO.getData())
              .methodData(taskRequestDTO.getMethodData())
              .build();
   }
}
