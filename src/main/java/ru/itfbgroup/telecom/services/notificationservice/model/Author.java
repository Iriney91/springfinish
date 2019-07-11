package ru.itfbgroup.telecom.services.notificationservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, exclude = "books")
@Entity
@SequenceGenerator(name = "MY_SEQ", sequenceName = "author_SEQ")
@Table(indexes = {@Index(name = "author_fullname_idx", columnList = "fullname", unique = true)})
public class Author extends IDIdentity{

    @Column(nullable = false)
    private String fullName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Authors_Books",
            joinColumns = @JoinColumn(name = "Author_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "Book_ID", referencedColumnName = "ID")
    )
    private Set <Book> books = new HashSet<>();
}
