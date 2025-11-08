package uz.pdp.todo;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ByWebClient implements TodoRemoteService {


    private final WebClient webClient;
    private final String root = "https://jsonplaceholder.typicode.com/todos";


    public ByWebClient() {
        this.webClient = WebClient.create();
    }

    @Override
    public List<Todo> getTodos() {
        return webClient
                .get()
                .uri(root)
                .retrieve()
                .bodyToFlux(Todo.class)
                .collectList()
                .block();
    }

    @Override
    public Todo getTodo(Integer id) {


        return webClient
                .get()
                .uri(root + "/" + id)
                .retrieve()
                .bodyToMono(Todo.class)
                .block();
    }

    @Override
    public Todo createTodo(Todo todo) {
        return webClient.post()
                .uri(root)
                .header("Authorization", "Bearer jwt_token")
                .bodyValue(todo)
                .retrieve()
                .bodyToMono(Todo.class)
                .block();
    }

    @Override
    public Todo updateTodo(String title, Integer id) {
        return null;
    }

    @Override
    public Todo completeTodo(Integer id) {
        Todo todo = getTodo(id);
        todo.setCompleted(true);
        return webClient.put()
                .uri(root)
                .bodyValue(todo)
                .retrieve()
                .bodyToMono(Todo.class)
                .block();
    }

    @Override
    public void deleteTodo(Integer id) {
        webClient.delete()
                .uri(root)
                .header("Authorization", "Bearer jwt_token")
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
