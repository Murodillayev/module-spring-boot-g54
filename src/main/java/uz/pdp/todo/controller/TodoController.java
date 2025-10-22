package uz.pdp.todo.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.todo.exception.NotFoundException;
import uz.pdp.todo.model.AuthUser;
import uz.pdp.todo.model.Todo;
import uz.pdp.todo.model.dto.DataResponse;
import uz.pdp.todo.model.dto.TodoDto;
import uz.pdp.todo.repository.TodoIdTitleDto;
import uz.pdp.todo.repository.TodoIdTitleDtoClass;
import uz.pdp.todo.repository.TodoRepository;
import uz.pdp.todo.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestMapping("/todo")
@RestController
@RequiredArgsConstructor
public class TodoController {
    private final TodoRepository repository;
    private final UserRepository userRepository;

    //
    @GetMapping("/{id}")
    public Todo get(@PathVariable String id) {
        Todo todo = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Id = %s not found".formatted(id))
        );
        return todo;
    }

    @GetMapping
    public DataResponse<Todo> getAll(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size
    ) {

        Sort sort = Sort.by(Sort.Direction.ASC, "title")
                .and(Sort.by(Sort.Direction.DESC, "description"));
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Todo> byPage = repository.findAll(pageable);

        return new DataResponse<>(byPage.getContent(), byPage.getTotalElements(), byPage.getTotalPages());

    }

    @GetMapping("/bySort")
    public List<Todo> getAllBySort() {

        Sort sort = Sort.by(Sort.Direction.ASC, "title")
                .and(Sort.by(Sort.Direction.DESC, "description"));

        List<Todo> all = repository.findAll(sort);
        return all;

    }

    @GetMapping("/native")
    public DataResponse<Todo> getAllNative(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size
    ) {

        Sort sort = Sort.by(Sort.Direction.ASC, "title")
                .and(Sort.by(Sort.Direction.DESC, "description"));
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<Todo> byPage = repository.findAllCustom(pageable);
        return new DataResponse<>(byPage.getContent(), byPage.getTotalElements(), byPage.getTotalPages());

    }

    @DeleteMapping("/{id}")
    public List<Todo> delete(@PathVariable String id) {
        repository.deleteById(id);

        return repository.findAll();

    }

    @PostMapping
    public Todo create(@RequestBody TodoDto dto) {

        AuthUser authUser = userRepository.findById(dto.getUserId()).orElseThrow();
        authUser.setPassword("dsadasdsadsa");
//        userRepository.save(authUser);
        Todo todo = Todo.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .completed(false)
                .user(authUser)
                .id(UUID.randomUUID().toString())
                .build();

        return repository.save(todo);
    }

    @PutMapping("/{id}")
    public Todo update(@PathVariable String id, @RequestBody TodoDto dto) {
        Todo todo = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Id = %s not found".formatted(id))
        );
        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        return repository.save(todo);
    }

    @DeleteMapping("/by-title")
    public ResponseEntity<Void> update(@RequestParam String title) {
        repository.deleteByTitle(title);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/id-title")
    public ResponseEntity<List<TodoIdTitleDto>> idTitle() {
        List<TodoIdTitleDto> idTitleDto = repository.findIdTitleDto();
        return ResponseEntity.ok(idTitleDto);
    }

    @GetMapping("/id-title-class")
    public ResponseEntity<List<TodoIdTitleDtoClass>> idTitleClass() {
        List<TodoIdTitleDtoClass> idTitleDto = repository.findIdTitleDtoClass();
        return ResponseEntity.ok(idTitleDto);
    }

    @GetMapping("/id-title-class-native")
    public ResponseEntity<List<TodoIdTitleDtoClass>> idTitleClassNative() {
        List<Object[]> rsList = repository.findIdTitleDtoClassNative();

        List<TodoIdTitleDtoClass> idTitleDto = rsList.stream()
                .map(e -> new TodoIdTitleDtoClass((String) e[0], (String) e[1]))
                .toList();

        return ResponseEntity.ok(idTitleDto);
    }


}
