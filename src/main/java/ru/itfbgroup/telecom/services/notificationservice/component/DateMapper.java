package ru.itfbgroup.telecom.services.notificationservice.component;


import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateMapper {

    public Long domainToDTO (Date date) {
        return date == null ? null : date.getTime();
    }

    public Date dtoToDomain(Long date) {
        return date == null ? null : new Date(date);
    }
}
