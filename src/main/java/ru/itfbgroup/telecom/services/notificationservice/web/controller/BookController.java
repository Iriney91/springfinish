package ru.itfbgroup.telecom.services.notificationservice.web.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.itfbgroup.telecom.services.notificationservice.common.web.PaginalResult;
import ru.itfbgroup.telecom.services.notificationservice.common.web.Result;
import ru.itfbgroup.telecom.services.notificationservice.model.Book;
import ru.itfbgroup.telecom.services.notificationservice.service.BookService;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.BookInDTO;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.BookOutDTO;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.BookPaginalRequestDTO;
import ru.itfbgroup.telecom.services.notificationservice.web.mapper.BookMapper;

import java.util.List;

@Api
@RequiredArgsConstructor
@RequestMapping("/book")
@RestController
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;


    @GetMapping(value = "/get/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    PaginalResult<List<BookOutDTO>> getBooks(BookPaginalRequestDTO requestDto) {
        Page<Book> paginatedBySearchRequest = bookService.getPaginatedBySearchRequest(requestDto);
        List<BookOutDTO> bookOutDTOS = bookMapper.bookToDto(paginatedBySearchRequest.getContent());
        return PaginalResult.success(bookOutDTOS, requestDto.getOffset(), requestDto.getLimit(), paginatedBySearchRequest.getTotalElements());
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result<BookOutDTO> getById(@PathVariable Long id) {
        return Result.success(bookMapper.bookToDto(bookService.getById(id)));
    }

    @PutMapping(value = "/put/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result<BookOutDTO> update(@PathVariable Long id, @RequestBody BookInDTO bookInDTO) {
        Book book = bookMapper.dtoToBook(bookInDTO);
        book.setId(id);
        return Result.success(bookMapper.bookToDto(bookService.update(book)));
    }

    @PostMapping(value = "/post/")
    Result create(@RequestBody BookInDTO bookInDTO, List <Long> authorIds) {
        bookService.create(bookMapper.dtoToBook(bookInDTO), authorIds);
        return Result.success();
    }

    @DeleteMapping(value = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result delete(@PathVariable Long id) {
        bookService.delete(id);
        return Result.success();
    }
}
