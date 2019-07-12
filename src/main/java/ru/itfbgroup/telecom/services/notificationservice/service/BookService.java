package ru.itfbgroup.telecom.services.notificationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itfbgroup.telecom.services.notificationservice.common.web.Result;
import ru.itfbgroup.telecom.services.notificationservice.model.Book;
import ru.itfbgroup.telecom.services.notificationservice.repository.BookRepository;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.BookPaginalRequestDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final PublishingHouseService publishingHouseService;

    public List<Book> getPaginatedBySearchRequest(BookPaginalRequestDTO bookPaginalRequestDTO) {
        return bookRepository.findAllByNameLikeAndIccidAndPublishingHouseIdAndAuthors(bookPaginalRequestDTO.getName(), bookPaginalRequestDTO.getIccid(),
                bookPaginalRequestDTO.getPublishingHouseId(), bookPaginalRequestDTO.getAuthorId(), bookPaginalRequestDTO.getPageRequest());
    }

    public Book getById(Long Id) {
        return bookRepository.findById(Id).orElseThrow(IllegalAccessError::new);
    }

    public void update(Book book) {
        if (bookRepository.existsById(book.getId())) {
            bookRepository.save(book);
        } else {
            Result.error(1, "Is not exist", new IllegalArgumentException());
        }
    }

    public void delete(Book book) {
        if (bookRepository.existsById(book.getId())) {
            bookRepository.delete(book);
        } else {
            Result.error(1, "Is not exist", new IllegalArgumentException());
        }
    }

    public Book create(Book book, List<Long> authorIds) {
        if (!bookRepository.existsById(book.getId())) {
            book.setAuthors(authorService.findAllByIdIn(authorIds));
            book.setPublishingHouse(publishingHouseService.getById(book.getPublishingHouseId()));
            bookRepository.save(book);
        } else {
            Result.error(2, "Already exist", new IllegalArgumentException());
        }
        return book;
    }
}


