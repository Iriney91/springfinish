package ru.itfbgroup.telecom.services.notificationservice.web.dto;

import lombok.Data;
import java.util.List;

@Data
public class BookOutDTO {

    private Long id;
    private Long createDate;
    private String name;
    private String iccid;
    private Integer year;
    private PublishingHouseOutDTO publishingHouse;
    private List <AuthorOutDTO> authors;
}
