package ru.itfbgroup.telecom.services.notificationservice.model;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class IDIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MY_SEQ")
    private long id;
}
