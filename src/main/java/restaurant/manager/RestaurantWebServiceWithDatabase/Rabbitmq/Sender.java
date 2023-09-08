package restaurant.manager.RestaurantWebServiceWithDatabase.Rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import restaurant.manager.RestaurantWebServiceWithDatabase.RestaurantWebServiceWithDatabaseApplication;

@Component
public class Sender implements CommandLineRunner {
    private final RabbitTemplate rabbitTemplate;

    public Sender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
    }

    public Boolean sendStringMessage(String message) {
        rabbitTemplate.convertAndSend(RestaurantWebServiceWithDatabaseApplication.topicExchangeName,
                "foo.bar.baz", message);
        return true;
    }

    public Boolean sendBytesMessage(byte[] message) {
        rabbitTemplate.convertAndSend(RestaurantWebServiceWithDatabaseApplication.topicExchangeName,
                "foo.bar.baz", message);
        return true;
    }
}