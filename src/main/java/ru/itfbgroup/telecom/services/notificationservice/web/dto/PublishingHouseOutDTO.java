package ru.itfbgroup.telecom.services.notificationservice.web.dto;

import lombok.Data;

@Data
public class PublishingHouseOutDTO {

    private long Id;
    private String name;
    private String INN;
    private String address;
}
