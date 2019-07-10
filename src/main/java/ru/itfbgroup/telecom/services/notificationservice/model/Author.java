package ru.itfbgroup.telecom.services.notificationservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(indexes = {@Index(name = "author_fullname_idx", columnList = "fullname", unique = true)})
public class Author {

    @Column(nullable = false)
    private String fullname;
}
