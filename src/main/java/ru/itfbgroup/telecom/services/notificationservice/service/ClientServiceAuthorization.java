package ru.itfbgroup.telecom.services.notificationservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itfbgroup.telecom.services.notificationservice.model.Client;
import ru.itfbgroup.telecom.services.notificationservice.repository.ClientRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceAuthorization {

    private final ClientRepository clientRepository;

    public Client create(Client client) {
        if (client == null) return null;
        return clientRepository.save(client);
    }
}
