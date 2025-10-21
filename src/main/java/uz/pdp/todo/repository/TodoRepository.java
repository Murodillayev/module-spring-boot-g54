package uz.pdp.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.todo.model.Todo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, String> {

    List<Todo> findAllByTitleOrderByTitle(String title);

    List<Todo> findAllByIdIn(Collection<String> ids);

    Optional<Todo> findByTitle(String title);

    List<Todo> findAllByTitleIsLikeIgnoreCase(String title);


    @Query(value = """
            select * from todo t where t.title ilike :s or t.description ilike :s
            """
            , nativeQuery = true)
    List<Todo> findAllBySearch1(@Param(value = "s") String search);

    @Query(value = """
            select * from todo t where t.title ilike :search or t.description ilike :search
            """
            , nativeQuery = true)
    List<Todo> findAllBySearch2(String search);

    @Query(value = """
            select * from todo t where t.title ilike ?1 or t.description ilike ?1
            """
            , nativeQuery = true)
    List<Todo> findAllBySearch3(String search);

    @Query(value = """
            from Todo t where t.title ilike ?1 or t.description ilike ?1
            """)
    List<Todo> findAllBySearch4(String search);

    @Query(value = """
            insert into todo(id, title) values (:id, :title)
            """, nativeQuery = true)
    @Modifying
    void insert(String id, String title); // 99 %


}
