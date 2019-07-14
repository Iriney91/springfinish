package ru.itfbgroup.telecom.services.notificationservice.web.dto;

import lombok.Data;

@Data
public class PublishingHouseOutDTO {

    private Long id;
    private String name;
    private String inn;
    private String address;
}
