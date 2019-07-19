package ru.itfbgroup.telecom.services.notificationservice.web.dto;

import lombok.Data;

@Data
public class ClientOutDTO {

    private Long id;
    private String fullName;
    private String login;
    private String userRole;
    private Boolean isConfirmed;
}
