package com.example;

import com.example.rabbitmqreciver.ClientCreationReceiver;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RabbitmqReciverApplication {

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
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("example.client.#");
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
											 MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(clientQueueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(ClientCreationReceiver receiver, MessageConverter messageConverter) {
		MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(receiver, messageConverter);
		messageListenerAdapter.setDefaultListenerMethod("receiveMessage");
		return messageListenerAdapter;
	}

	@Bean
	public MessageConverter jsonMessageConverter(){
		final ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return new Jackson2JsonMessageConverter(objectMapper);
	}


	public static void main(String[] args) {
		SpringApplication.run(RabbitmqReciverApplication.class, args);
	}

}
