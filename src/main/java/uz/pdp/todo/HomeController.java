package uz.pdp.todo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.todo.dao.TodoDao;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final TodoDao dao;

    @GetMapping
    public ModelAndView indexPage() {
        List<Todo> todos = dao.findAll();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todos", todos);
        return modelAndView;
    }
}
