package ru.itfbgroup.telecom.services.notificationservice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@SequenceGenerator(name = "MY_SEQ", sequenceName = "client_SEQ")
@Table(indexes = {@Index(name = "client_fullname_idx", columnList = "fullname", unique = true)})
public class Client extends IDIdentity{

    @Column(nullable = false)
    private String fullname;
}
