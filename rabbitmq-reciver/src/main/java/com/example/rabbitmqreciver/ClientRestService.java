package com.example.rabbitmqreciver;

import com.example.model.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientRestService {
    private final ClientDao clientDao;

    public ClientRestService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @GetMapping("/client")
    List<Client> getClients() {
        return this.clientDao.findAll();
    }
}
