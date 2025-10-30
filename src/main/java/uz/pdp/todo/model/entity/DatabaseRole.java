package uz.pdp.todo.model.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.todo.model.entity.base.BaseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class DatabaseRole extends BaseEntity {
    private String name;
    private String code;
    private String description;
}
