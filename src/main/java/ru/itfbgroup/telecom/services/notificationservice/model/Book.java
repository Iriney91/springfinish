package ru.itfbgroup.telecom.services.notificationservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {"publishingHouse", "bookBinary", "authorIds"})
@Entity
@SequenceGenerator(name = "MY_SEQ", sequenceName = "book_SEQ")
@Table(indexes = {@Index(name = "book_iccid_idx", columnList = "iccid", unique = true), @Index(name = "book_name_idx", columnList = "name", unique = false)})
public class Book extends IDIdentity {

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private Date createDate;

    @Column(nullable = false, length = 64)
    private String iccid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer year;

    @JoinColumn(name = "PUB_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private PublishingHouse publishingHouse;

    @Column (name = "PUB_ID", insertable = false, updatable = false, nullable = false, length = 64)
    private Long publishingHouseId;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private BookBinary bookBinary;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "books")
    private Set <Author> authors = new HashSet<>();
}
