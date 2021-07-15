package com.example.rabbitmqsender;

import com.example.model.Client;

public interface ClientDao {
    void createClient(Client client);
}
