package ru.itfbgroup.telecom.services.notificationservice.web.dto;

import lombok.Data;

@Data
public class ClientInDTOAdmin {

    private String fullName;
    private String login;
    private String userRole;
    private Boolean isConfirmed = false;
}
