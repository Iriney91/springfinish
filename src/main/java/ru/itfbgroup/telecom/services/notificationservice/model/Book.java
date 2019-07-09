package ru.itfbgroup.telecom.services.notificationservice.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(indexes = {@Index(name = "book_iccid_idx", columnList = "iccid", unique = true), @Index(name = "book_name_idx", columnList = "name", unique = false)})
public class Book {

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private Date createDate;

    @Column(nullable = false, length = 64)
    private String iccid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer year;
}
