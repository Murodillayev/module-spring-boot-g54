package uz.pdp.todo.model.entity.base;


import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@MappedSuperclass
public class IdEntity {

    @Id
    private String id = UUID.randomUUID().toString();

}



