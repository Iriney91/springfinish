package ru.itfbgroup.telecom.services.notificationservice.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;
import ru.itfbgroup.telecom.services.notificationservice.common.web.PaginalRequest;

@Data
@EqualsAndHashCode(callSuper = true)
public class PublishingHousePaginalRequestDTO extends PaginalRequest {

    private String name;
    private String inn;
}
