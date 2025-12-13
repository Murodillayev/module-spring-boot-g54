package uz.pdp.todo;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    @Bean
    public Queue queue() {
        return new Queue("todo_queue_2", false);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("todo_exchange");
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with("todo_routing_key");
    }
}
