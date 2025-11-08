package uz.pdp.todo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;

@Service
@Slf4j
public class ByRestTemplate implements TodoRemoteService {

    private final RestTemplate restTemplate;
    private final String root = "https://jsonplaceholder.typicode.com";

    public ByRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Todo> getTodos() {
        //https://jsonplaceholder.typicode.com/todos

        String apiUrl = root + "/todos";
//        return (List<Todo>) restTemplate.getForObject(apiUrl, List.class);
//        ResponseEntity<Todo[]> response = restTemplate.getForEntity(apiUrl, Todo[].class);
        ResponseEntity<Todo[]> response = restTemplate.exchange(apiUrl, HttpMethod.GET, null, Todo[].class);

        HttpHeaders headers = response.getHeaders();
        System.out.println(headers);
        System.out.println(response.getStatusCode());
        Todo[] body = response.getBody();

        return (body == null) ? Collections.emptyList() : List.of(body);

//        restTemplate.getForObject(); Object []
//        restTemplate.getForEntity(); ResponseEntity<Object[]>
//        restTemplate.exchange();  ResponseEntity<Object[]>
    }


    @Override
    public Todo createTodo(Todo todo) {

        String apiUrl = root + "/todos";

        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", List.of("Bearer jwt_token")); //headers.setBearerAuth("jwt_token");
        HttpEntity<Todo> requestBody = new HttpEntity<>(todo, headers);
        Todo resp = restTemplate.postForObject(apiUrl, requestBody, Todo.class);

        log.info("Successfully created | {}", resp);

        return resp;
    }

    @Override
    public Todo updateTodo(String title, Integer id) {


        return null;
    }


    @Override
    public Todo getTodo(Integer id) {

        return null;
    }

    @Override
    public Todo completeTodo(Integer id) {

        return null;
    }

    @Override
    public void deleteTodo(Integer id) {

    }

    private static class ListParameterizedTypeReference extends ParameterizedTypeReference<List<Todo>> {
    }
}
