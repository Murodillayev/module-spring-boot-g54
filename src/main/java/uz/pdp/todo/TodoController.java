package uz.pdp.todo;


import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.todo.dao.TodoDao;

@Controller
@RequestMapping("/todo")
public class TodoController {

    private final TodoDao dao;

    public TodoController(TodoDao dao) {
        this.dao = dao;
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        dao.deleteById(id);
        return "redirect:/";
    }
}
