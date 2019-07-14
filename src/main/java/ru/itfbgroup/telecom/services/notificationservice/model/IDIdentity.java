package ru.itfbgroup.telecom.services.notificationservice.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class IDIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MY_SEQ")
    private Long id;
}
