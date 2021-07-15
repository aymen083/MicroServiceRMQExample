package com.example.rabbitmqsender;

import com.example.model.Client;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClientSender implements ClientDao {
    private final RabbitTemplate rabbitTemplate;

    public ClientSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void createClient(Client client) {
        rabbitTemplate.convertAndSend(RabbitmqSenderApplication.clientTopicExchangeName, "example.client.create", client);
    }
}
