package com.example.rabbitmqsender;

import com.example.model.Client;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientRestService {
    private final ClientDao clientDao;

    public ClientRestService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @PutMapping("/client")
    Client createClient(@RequestBody Client client) {
        System.out.println(client);
        this.clientDao.createClient(client);
        return client;
    }
}
