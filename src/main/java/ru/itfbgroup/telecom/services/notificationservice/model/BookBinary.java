package ru.itfbgroup.telecom.services.notificationservice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

@Data
@Entity
@SequenceGenerator(name = "MY_SEQ", sequenceName = "bookBinary_SEQ")
public class BookBinary extends IDIdentity{

    private byte [] binaryContent;
}
