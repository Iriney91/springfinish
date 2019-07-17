package ru.itfbgroup.telecom.services.notificationservice.web.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.itfbgroup.telecom.services.notificationservice.common.web.PaginalResult;
import ru.itfbgroup.telecom.services.notificationservice.common.web.Result;
import ru.itfbgroup.telecom.services.notificationservice.model.Client;
import ru.itfbgroup.telecom.services.notificationservice.service.ClientService;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.ClientInDTO;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.ClientOutDTO;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.ClientPaginalRequestDTO;
import ru.itfbgroup.telecom.services.notificationservice.web.mapper.ClientMapper;

import java.util.List;

@Api
@RequiredArgsConstructor
@RequestMapping("/client")
@RestController
public class ClientController {

    private final ClientService clientService;

    private final ClientMapper clientMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    PaginalResult<List<ClientOutDTO>> getClients(ClientPaginalRequestDTO requestDto) {
        Page<Client> paginatedBySearchRequest = clientService.getPaginatedBySearchRequest(requestDto);
        List<ClientOutDTO> clientOutDTOS = clientMapper.clientToDto(paginatedBySearchRequest.getContent());
        return PaginalResult.success(clientOutDTOS, requestDto.getOffset(),
                requestDto.getLimit(), paginatedBySearchRequest.getTotalElements());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result<ClientOutDTO> getById(@PathVariable Long id) {
        return Result.success(clientMapper.clientToDto(clientService.getById(id)));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result<ClientOutDTO> update(@PathVariable Long id, @RequestBody ClientInDTO clientInDTO) {
        Client client = clientMapper.dtoToClient(clientInDTO);
        client.setId(id);
        return Result.success(clientMapper.clientToDto(clientService.update(client)));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result <ClientOutDTO> create(@RequestBody ClientInDTO clientInDTO) {
        return Result.success(clientMapper.clientToDto(clientService.create(clientMapper.dtoToClient(clientInDTO))));
    }

    @DeleteMapping(value = "/{id}")
    Result delete(@PathVariable Long id) {
        clientService.delete(id);
        return Result.success();
    }
}
