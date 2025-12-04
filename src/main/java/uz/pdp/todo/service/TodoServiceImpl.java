package uz.pdp.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.todo.BadRequestException;
import uz.pdp.todo.mapper.TodoMapper;
import uz.pdp.todo.model.Todo;
import uz.pdp.todo.model.TodoCreateDto;
import uz.pdp.todo.model.TodoDto;
import uz.pdp.todo.model.TodoUpdateDto;
import uz.pdp.todo.repo.TodoRepository;
import uz.pdp.todo.utils.ErrorMessages;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    public List<TodoDto> getAll() {
        return todoRepository.findAll().stream()
                .map(todoMapper::toDto)
                .toList();
    }

    public TodoDto get(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo topilmadi: " + id));
        return todoMapper.toDto(todo);
    }

    public TodoDto create(TodoCreateDto dto) {

        if (dto.getTitle() == null) {
            throw new BadRequestException(ErrorMessages.TITLE_IS_REQUIRED);
        }
//
        if (dto.getTitle().length() < 5) {
            throw new BadRequestException(ErrorMessages.TITLE_IS_TOO_SHORT);
        }
//
//        if (dto.getTitle().length() > 50) {
//            throw new BadRequestException("Title is too long");
//        }
//
        Todo todo = todoMapper.fromDto(dto);
        Todo saved = todoRepository.save(todo);
        return todoMapper.toDto(saved);
    }

    public TodoDto update(Long id, TodoUpdateDto updateDto) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo topilmadi: " + id));

        todoMapper.fromDto(updateDto, todo);

        if (updateDto.getCompleted() != null) {
            todo.setCompleted(updateDto.getCompleted());
        }

        Todo updated = todoRepository.save(todo);
        return todoMapper.toDto(updated);
    }

    public void delete(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new RuntimeException("Todo topilmadi: " + id);
        }
        todoRepository.deleteById(id);
    }
}