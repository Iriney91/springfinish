package ru.itfbgroup.telecom.services.notificationservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(callSuper = true, exclude = "books")
@SequenceGenerator(name = "MY_SEQ", sequenceName = "publishingHouse_SEQ")
@Table(indexes = {@Index(name = "publishingHouse_INN_idx", columnList = "INN", unique = true)})
public class PublishingHouse extends IDIdentity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 10)
    private String INN;

    @Column(nullable = false)
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "publishingHouse")
    private Set <Book> books = new HashSet<>();
}
