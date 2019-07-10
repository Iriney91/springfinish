package ru.itfbgroup.telecom.services.notificationservice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@SequenceGenerator(name = "MY_SEQ", sequenceName = "author_SEQ")
@Table(indexes = {@Index(name = "author_fullname_idx", columnList = "fullname", unique = true)})
public class Author extends IDIdentity{

    @Column(nullable = false)
    private String fullname;
}
