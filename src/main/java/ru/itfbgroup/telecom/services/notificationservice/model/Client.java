package ru.itfbgroup.telecom.services.notificationservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@SequenceGenerator(name = "MY_SEQ", sequenceName = "client_SEQ")
@Table(indexes = {@Index(name = "client_fullname_idx", columnList = "fullname", unique = true)})
public class Client extends IDIdentity{

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String login;

    @Column
    private String password;

    @Column(updatable = false)
    private String userRole;

    @Column
    private Boolean isConfirmed;
}
