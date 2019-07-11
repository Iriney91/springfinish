package ru.itfbgroup.telecom.services.notificationservice.web.mapper;

import org.mapstruct.Mapper;
import ru.itfbgroup.telecom.services.notificationservice.model.Client;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.ClientInDTO;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.ClientOutDTO;

@Mapper
public interface ClientMapper {

    Client dtoToClient (ClientInDTO clientDTO);
    ClientOutDTO clientToDto (Client client);
}
