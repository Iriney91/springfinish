package ru.itfbgroup.telecom.services.notificationservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.itfbgroup.telecom.services.notificationservice.model.Book;

import java.util.List;

@SuppressWarnings("unused")
@Repository
public interface BookRepository extends JpaRepository <Book, Long>, JpaSpecificationExecutor<Book> {
//
//    Page<Book> findAllByNameLikeAndIccidAndPublishingHouseIdAndAuthors (String name, String iccid, Long publishingHouseId, Long authorId, Pageable pageable);

}
