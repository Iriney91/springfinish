package ru.itfbgroup.telecom.services.notificationservice.web.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itfbgroup.telecom.services.notificationservice.common.web.PaginalResult;
import ru.itfbgroup.telecom.services.notificationservice.common.web.Result;
import ru.itfbgroup.telecom.services.notificationservice.model.Client;
import ru.itfbgroup.telecom.services.notificationservice.service.AdminService;
import ru.itfbgroup.telecom.services.notificationservice.service.ClientService;
import ru.itfbgroup.telecom.services.notificationservice.service.ClientServiceAuthorization;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.ClientInDTO;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.ClientInDTOAdmin;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.ClientOutDTO;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.ClientPaginalRequestDTO;
import ru.itfbgroup.telecom.services.notificationservice.web.mapper.ClientMapper;

import java.util.List;

@Api(value = "Client management")
@RequiredArgsConstructor
@RequestMapping("/client")
@RestController
@Slf4j
public class ClientController {

    private final ClientService clientService;

    private final ClientServiceAuthorization clientServiceAuthorization;

    private final AdminService adminService;

    private final ClientMapper clientMapper;

    @PreAuthorize("hasAuthority('ADMIN')")
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

    @PostMapping(value = "/createClient", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result create(@RequestBody ClientInDTOAdmin clientInDTOadmin) {
        clientServiceAuthorization.create(clientMapper.dtoToClient(clientInDTOadmin));
        return Result.success();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/authorization", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result clientAuthorization (@RequestBody Long id) {
        adminService.clientAuthorization(id);
        return Result.success();
    }

    @DeleteMapping(value = "/{id}")
    Result delete(@PathVariable Long id) {
        clientService.delete(id);
        return Result.success();
    }
}
