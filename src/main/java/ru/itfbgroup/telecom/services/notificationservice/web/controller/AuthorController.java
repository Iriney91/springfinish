package ru.itfbgroup.telecom.services.notificationservice.web.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.itfbgroup.telecom.services.notificationservice.common.web.PaginalResult;
import ru.itfbgroup.telecom.services.notificationservice.common.web.Result;
import ru.itfbgroup.telecom.services.notificationservice.model.Author;
import ru.itfbgroup.telecom.services.notificationservice.service.AuthorService;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.AuthorInDTO;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.AuthorOutDTO;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.AuthorPaginalRequestDTO;
import ru.itfbgroup.telecom.services.notificationservice.web.mapper.AuthorMapper;

import java.util.List;

@Api
@RequiredArgsConstructor
@RequestMapping("/author")
@RestController
public class AuthorController {

    private final AuthorService authorService;

    private final AuthorMapper authorMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    PaginalResult<List<AuthorOutDTO>> getAuthors(AuthorPaginalRequestDTO requestDto) {
        Page<Author> paginatedBySearchRequest = authorService.getPaginatedBySearchRequest(requestDto);
        List<AuthorOutDTO> authorOutDTOS = authorMapper.authorToDto(paginatedBySearchRequest.getContent());
        return PaginalResult.success(authorOutDTOS, requestDto.getOffset(),
                requestDto.getLimit(), paginatedBySearchRequest.getTotalElements());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result<AuthorOutDTO> getById(@PathVariable Long id) {
        return Result.success(authorMapper.authorToDto(authorService.getById(id)));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result<AuthorOutDTO> update(@PathVariable Long id, @RequestBody AuthorInDTO authorInDTO) {
        Author author = authorMapper.dtoToAuthor(authorInDTO);
        author.setId(id);
        return Result.success(authorMapper.authorToDto(authorService.update(author)));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Result <AuthorOutDTO> create(@RequestBody AuthorInDTO authorInDTO) {
        return Result.success(authorMapper.authorToDto(authorService.create(authorMapper.dtoToAuthor(authorInDTO))));
    }

    @DeleteMapping(value = "/{id}")
    Result delete(@PathVariable Long id) {
        authorService.delete(id);
        return Result.success();
    }
}
