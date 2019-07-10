package ru.itfbgroup.telecom.services.notificationservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(indexes = {@Index(name = "publishingHouse_INN_idx", columnList = "INN", unique = true)})
public class PublishingHouse {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 10)
    private String INN;

    @Column(nullable = false)
    private String address;
}
