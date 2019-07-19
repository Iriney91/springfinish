package ru.itfbgroup.telecom.services.notificationservice.web.mapper;

import org.mapstruct.Mapper;
import ru.itfbgroup.telecom.services.notificationservice.model.Client;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.ClientInDTO;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.ClientInDTOAdmin;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.ClientOutDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client dtoToClient (ClientInDTO clientDTO);
    ClientOutDTO clientToDto (Client client);

    Client dtoToClient (ClientInDTOAdmin clientInDTOAdmin);

    List<Client> dtoToClient(List<ClientInDTO> clientInDTO);
    List<ClientOutDTO> clientToDto(List<Client> client);
}
