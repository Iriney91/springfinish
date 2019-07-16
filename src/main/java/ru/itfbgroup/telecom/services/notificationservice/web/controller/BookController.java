package ru.itfbgroup.telecom.services.notificationservice.web.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itfbgroup.telecom.services.notificationservice.common.web.PaginalResult;
import ru.itfbgroup.telecom.services.notificationservice.common.web.Result;
import ru.itfbgroup.telecom.services.notificationservice.model.Book;
import ru.itfbgroup.telecom.services.notificationservice.model.BookBinary;
import ru.itfbgroup.telecom.services.notificationservice.service.BookService;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.BookInDTO;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.BookOutDTO;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.BookPaginalRequestDTO;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.BookSpecification;
import ru.itfbgroup.telecom.services.notificationservice.web.mapper.BookMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Api
@RequiredArgsConstructor
@RequestMapping("/book")
@RestController
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;


    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    PaginalResult<List<BookOutDTO>> getBooks(BookPaginalRequestDTO requestDto) {
        Page<Book> paginatedBySearchRequest = bookService.getPaginatedBySpecification(new BookSpecification(requestDto));
        List<BookOutDTO> bookOutDTOS = bookMapper.bookToDto(paginatedBySearchRequest.getContent());
        return PaginalResult.success(bookOutDTOS, requestDto.getOffset(), requestDto.getLimit(), paginatedBySearchRequest.getTotalElements());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result<BookOutDTO> getById(@PathVariable Long id) {
        return Result.success(bookMapper.bookToDto(bookService.getById(id)));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result<BookOutDTO> update(@PathVariable Long id, @RequestBody BookInDTO bookInDTO) {
        Book book = bookMapper.dtoToBook(bookInDTO);
        book.setId(id);
        return Result.success(bookMapper.bookToDto(bookService.update(book, bookInDTO.getAuthorIds())));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result create(@RequestBody BookInDTO bookInDTO) {
        bookService.create(bookMapper.dtoToBook(bookInDTO), bookInDTO.getAuthorIds());
        return Result.success();
    }

    @DeleteMapping(value = "/{id}")
    Result delete(@PathVariable Long id) {
        bookService.delete(id);
        return Result.success();
    }

    @GetMapping(
            value = "/{id}/content",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )

    public void getBookBinaryById(@PathVariable Long id, HttpServletResponse response)  throws IOException {

        BookBinary bookBinary = bookService.getBookBinareById(id);
        response.setContentType(bookBinary.getMineType());
        response.getOutputStream().write(bookBinary.getBinaryContent());
    }


    @PostMapping(value = "/{id}/content")
    public Result BookBinary(@PathVariable Long id, @RequestParam("content") MultipartFile file) throws IOException {
        BookBinary bookBinary = new BookBinary();
        bookBinary.setBinaryContent(file.getBytes());
        bookBinary.setMineType(file.getContentType());
        //  bookBinary.setMainType(Files.probeContentType(new File(bookBinary.getMainType()).toPath()));
        bookBinary.setFileName(file.getOriginalFilename());
        Book book = bookService.getById(id);
        book.setBookBinary(bookBinary);
        bookService.update(book, id);
    }
}
