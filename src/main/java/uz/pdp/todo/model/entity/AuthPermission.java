package uz.pdp.todo.model.entity;

import jakarta.persistence.Entity;
import lombok.*;
import uz.pdp.todo.model.entity.base.IdEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthPermission extends IdEntity {
    private String name;
    private String code;
}


// user 1(v=1, a=1) ->            user1 user2     agent=1 v=0 <- agent
// user 2(v=1 a=1) ->

//                              []    agent=1 v=1 <- agent


// user 1(v=2)          ->              [user 1]    agent=1 v=1 <- agent
// user 2(v=1 a=1) ->


// user 1(v=2)          ->              []    agent=1 v=2 <- agent
// user 2(v=1 a=1) ->


// user 1(v=2)          ->              [user2]    agent=1 v=3 <- agent
// user 2(v=3 a=1) ->


// user1         ->            [user1,user2] <- agent
// user2

// user1         ->            [user1,user2] <- agent
// user2





