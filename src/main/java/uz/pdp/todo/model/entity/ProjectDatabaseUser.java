package uz.pdp.todo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.todo.model.entity.base.BaseEntity;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProjectDatabaseUser extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AuthUser authUser;

    private String username;
    private String password;

    @ManyToOne
    @JoinColumn(name = "database_id")
    private ProjectDatabase database;

    @ManyToMany
    @JoinTable(
            name = "database_user_role",
            joinColumns = @JoinColumn(name = "database_user_id"),
            inverseJoinColumns = @JoinColumn(name = "database_role_id")
    )
    private List<DatabaseRole> roles;

    private Integer version;
}


// db user 1  v=1
// db user 2  v=4      filter v > a_v         <- v=3;
// db user 3  v=3
// db user 4  v=1
// db user 5  v=1