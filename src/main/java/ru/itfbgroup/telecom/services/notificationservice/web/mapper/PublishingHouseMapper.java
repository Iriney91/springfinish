package ru.itfbgroup.telecom.services.notificationservice.web.mapper;

import org.mapstruct.Mapper;
import ru.itfbgroup.telecom.services.notificationservice.model.PublishingHouse;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.PublishingHouseInDTO;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.PublishingHouseOutDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PublishingHouseMapper {

    PublishingHouse dtoToPublishingHouse (PublishingHouseInDTO publishingHouseDTO);
    PublishingHouseOutDTO publishingHouseToDto (PublishingHouse publishingHouse);

    List<PublishingHouse>dtoToPublishingHouse(List<PublishingHouseInDTO> publishingHouseDTO);
    List<PublishingHouseOutDTO> publishingHouseToDto(List<PublishingHouse> publishingHouse);
}
