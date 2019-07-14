package ru.itfbgroup.telecom.services.notificationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itfbgroup.telecom.services.notificationservice.common.web.Result;
import ru.itfbgroup.telecom.services.notificationservice.model.Client;
import ru.itfbgroup.telecom.services.notificationservice.repository.ClientRepositore;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.ClientPaginalRequestDTO;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepositore clientRepositore;

    public List<Client> getPaginatedBySearchRequest(ClientPaginalRequestDTO clientPaginalRequestDTO){
        return clientRepositore.findAllByFullNameLikeAndLogin(clientPaginalRequestDTO.getFullName(), clientPaginalRequestDTO.getLogin(), clientPaginalRequestDTO.getPageRequest());
    }

    public Client getById(Long id){
        return clientRepositore.findById(id).orElseThrow(IllegalAccessError::new);
    }

    public void update(Client client) {
        if (clientRepositore.existsById(client.getId())) {
            clientRepositore.save(client);
        } else {
            Result.error(1, "Is not exist", new IllegalArgumentException());
        }
    }

    public void delete(Client client) {
        if (clientRepositore.existsById(client.getId())) {
            clientRepositore.delete(client);
        } else {
            Result.error(1, "Is not exist", new IllegalArgumentException());
        }
    }

    public Client create(Client client) {
        if (!clientRepositore.existsById(client.getId())) {
            clientRepositore.save(client);
        } else {
            Result.error(2, "Already exist", new IllegalArgumentException());
        }
        return client;
    }
}
