package ru.itfbgroup.telecom.services.notificationservice.web.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.itfbgroup.telecom.services.notificationservice.common.web.PaginalResult;
import ru.itfbgroup.telecom.services.notificationservice.common.web.Result;
import ru.itfbgroup.telecom.services.notificationservice.model.PublishingHouse;
import ru.itfbgroup.telecom.services.notificationservice.service.PublishingHouseService;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.PublishingHouseInDTO;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.PublishingHouseOutDTO;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.PublishingHousePaginalRequestDTO;
import ru.itfbgroup.telecom.services.notificationservice.web.mapper.PublishingHouseMapper;

import java.util.List;

@Api
@RequiredArgsConstructor
@RequestMapping("/publishingHouse")
@RestController
public class PublishingHouseController {

    private final PublishingHouseService publishingHouseService;

    private PublishingHouseMapper publishingHouseMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    PaginalResult<List<PublishingHouseOutDTO>> getPublishingHouses(PublishingHousePaginalRequestDTO requestDto) {
        Page<PublishingHouse> paginatedBySearchRequest = publishingHouseService.getPaginatedBySearchRequest(requestDto);
        List<PublishingHouseOutDTO> publishingHouseOutDTOS = publishingHouseMapper.publishingHouseToDto(paginatedBySearchRequest.getContent());
        return PaginalResult.success(publishingHouseOutDTOS, requestDto.getOffset(),
                requestDto.getLimit(), paginatedBySearchRequest.getTotalElements());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result<PublishingHouseOutDTO> getById(@PathVariable Long id) {
        return Result.success(publishingHouseMapper.publishingHouseToDto(publishingHouseService.getById(id)));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result<PublishingHouseOutDTO> update(@PathVariable Long id, @RequestBody PublishingHouseInDTO publishingHouseInDTO) {
        PublishingHouse publishingHouse = publishingHouseMapper.dtoToPublishingHouse(publishingHouseInDTO);
        publishingHouse.setId(id);
        return Result.success(publishingHouseMapper.publishingHouseToDto(publishingHouseService.update(publishingHouse)));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result <PublishingHouseOutDTO> create(@RequestBody PublishingHouseInDTO publishingHouseInDTO) {
        return Result.success(publishingHouseMapper.publishingHouseToDto(publishingHouseService.create(publishingHouseMapper.dtoToPublishingHouse(publishingHouseInDTO))));
    }

    @DeleteMapping(value = "/{id}")
    Result delete(@PathVariable Long id) {
        publishingHouseService.delete(id);
        return Result.success();
    }
}
