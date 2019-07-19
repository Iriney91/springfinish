package ru.itfbgroup.telecom.services.notificationservice.web.dto;

import lombok.Data;

@Data
public class ClientInDTO {

    private String fullName;
    private String login;
    private String password;
    private String userRole;
    private Boolean isConfirmed;
}
