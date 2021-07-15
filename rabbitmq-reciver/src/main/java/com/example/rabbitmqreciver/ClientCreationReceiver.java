package com.example.rabbitmqreciver;

import com.example.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientCreationReceiver {

    private final ClientDao clientDao;

    public ClientCreationReceiver(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public void receiveMessage(Client client) {
        System.out.println("Received <" + client + ">");
        clientDao.save(client);
    }
}
