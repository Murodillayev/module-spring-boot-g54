package uz.pdp.todo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Test {
    @Id
    private String id;

    private String name;
}
