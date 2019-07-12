package ru.itfbgroup.telecom.services.notificationservice.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookInDTO {

    private String name;
    private String iccid;
    private Integer year;
    private List <Long> authorIds;
    private Long publishingHouseId;
}
