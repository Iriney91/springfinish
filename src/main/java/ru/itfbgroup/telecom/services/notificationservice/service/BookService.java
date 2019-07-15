package ru.itfbgroup.telecom.services.notificationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    public Page<Book> getPaginatedBySearchRequest(BookPaginalRequestDTO bookPaginalRequestDTO) {
        return bookRepository.findAllByNameLikeAndIccidAndPublishingHouseIdAndAuthors(bookPaginalRequestDTO.getName(), bookPaginalRequestDTO.getIccid(),
                bookPaginalRequestDTO.getPublishingHouseId(), bookPaginalRequestDTO.getAuthorId(), bookPaginalRequestDTO.getPageRequest());
    }

    public Book getById(Long id) {
        return bookRepository.findById(id).orElseThrow(IllegalAccessError::new);
    }

    public Book update(Book book) {
        if (bookRepository.existsById(book.getId())) {
            bookRepository.save(book);
        } else {
            throw new IllegalArgumentException("No such author found");
        }return book;
    }

    public void delete(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No such author found");
        }
    }

    public Book create(Book book, List<Long> authorIds) {
        if (!bookRepository.existsById(book.getId())) {
            book.setAuthors(authorService.findAllByIdIn(authorIds));
            book.setPublishingHouse(publishingHouseService.getById(book.getPublishingHouseId()));
            bookRepository.save(book);
        } else {
            throw new IllegalArgumentException("Already exist");
        }
        return book;
    }
}


