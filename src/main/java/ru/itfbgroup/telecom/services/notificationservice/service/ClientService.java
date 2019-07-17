package ru.itfbgroup.telecom.services.notificationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itfbgroup.telecom.services.notificationservice.model.Client;
import ru.itfbgroup.telecom.services.notificationservice.repository.ClientRepository;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.ClientPaginalRequestDTO;


@Service
@RequiredArgsConstructor
public class ClientService {

    private final ApplicationContext context;

    private final ClientRepository clientRepository;

    public Page<Client> getPaginatedBySearchRequest(ClientPaginalRequestDTO clientPaginalRequestDTO) {
        if (clientPaginalRequestDTO.getSortColumn() == null)
            clientPaginalRequestDTO.setSortColumn("fullName");
        if (clientPaginalRequestDTO.getFullName() == null)

            return clientRepository.findAll(clientPaginalRequestDTO.getPageRequest());

        return clientRepository.findClientByLogin(clientPaginalRequestDTO.getLogin(), clientPaginalRequestDTO.getPageRequest());
    }

    public Client getById(Long id) {
        return clientRepository.findById(id).orElseThrow(IllegalAccessError::new);
    }

    public Client update(Client client) {
        if (clientRepository.existsById(client.getId())) {
            clientRepository.save(client);
        } else {
            throw new IllegalArgumentException("No such client found");
        }
        return client;
    }

    public void delete(Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No such client found");
        }
    }

    public Client create(Client client) {
        return clientRepository.save(client);
    }

    public Client create (String login, String password, String name, String role) {
        Client client = null;
        if (!clientRepository.existsByLogin(login)) {
            client = new Client();
            client.setLogin(login);
            client.setFullName(name);
            client.setPassword(context.getBean(PasswordEncoder.class).encode(password));
            client.setUserRole(role);
            clientRepository.save(client);

        }return client;
    }
}
