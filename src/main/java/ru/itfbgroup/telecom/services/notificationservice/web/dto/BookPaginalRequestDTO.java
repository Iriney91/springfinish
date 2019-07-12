package ru.itfbgroup.telecom.services.notificationservice.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.itfbgroup.telecom.services.notificationservice.common.web.PaginalRequest;

@Data
@EqualsAndHashCode(callSuper = true)
public class BookPaginalRequestDTO extends PaginalRequest {

    private String name;
    private String iccid;
    private Long publishingHouseId;
    private Long authorId;

}
