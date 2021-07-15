package com.example.rabbitmqsender;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.support.converter.MessageConverter;

import java.text.SimpleDateFormat;

@SpringBootApplication
public class RabbitmqSenderApplication {

	static final String clientTopicExchangeName = "client-exchange";

	static final String clientQueueName = "clientQueue";

	@Bean
	Queue clientQueue() {
		return new Queue(clientQueueName, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(clientTopicExchangeName);
	}

	@Bean
	public MessageConverter jsonMessageConverter(){
		final ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return new Jackson2JsonMessageConverter(objectMapper);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("example.client.#");
	}

	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqSenderApplication.class, args);
	}

}
