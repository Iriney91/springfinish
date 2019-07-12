package ru.itfbgroup.telecom.services.notificationservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itfbgroup.telecom.services.notificationservice.model.Author;

import java.util.List;
import java.util.Set;

@SuppressWarnings("unused")
@Repository
public interface AuthorRepository extends JpaRepository <Author, Long>{

    List<Author> findAllByFullNameLike (String fullName, Pageable pageable);
    Set<Author> findAllByIdIn (List<Long> authorIds);
}
