package uz.pdp.todo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.todo.model.Todo;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, String> {

    List<Todo> findAllByTitleOrderByTitle(String title);

    List<Todo> findAllByIdIn(Collection<String> ids);

    Optional<Todo> findByTitle(String title);


    @Query(value = "from Todo t where t.title like :title")
    Optional<Todo> findByTitleNative(String title);

    List<Todo> findAllByIdInAndCompletedIsFalseOrderByTitleAsc(Collection<String> ids);

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
    void insert(String id, String title);

    @Modifying
    @Transactional
    @Query(value = "delete from Todo t where t.title = :title")
    void deleteByTitle(String title);

    @Query(value = "from Todo t")
    Page<Todo> findAllCustom(Pageable pageable);


    @Query(value = "select t.id as id, t.title as title from Todo t")
    List<TodoIdTitleDto> findIdTitleDto();

    @Query(value = """
            select 
              new uz.pdp.todo.repository.TodoIdTitleDtoClass(t.id, t.title) 
            from Todo t""")
    List<TodoIdTitleDtoClass> findIdTitleDtoClass();

    @Query(value = """
            select 
              t.id,
              t.title
            from todo t""", nativeQuery = true)
    List<Object[]> findIdTitleDtoClassNative();


}
