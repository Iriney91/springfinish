package ru.itfbgroup.telecom.services.notificationservice.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;
import ru.itfbgroup.telecom.services.notificationservice.common.web.PaginalRequest;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientPaginalRequestDTO extends PaginalRequest {

    private  String fullName;
    private String login;
    private String userRole;
    private Boolean isConfirmed;
}
