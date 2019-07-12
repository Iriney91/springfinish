package ru.itfbgroup.telecom.services.notificationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itfbgroup.telecom.services.notificationservice.common.web.Result;
import ru.itfbgroup.telecom.services.notificationservice.model.Author;
import ru.itfbgroup.telecom.services.notificationservice.repository.AuthorRepository;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.AuthorPaginalRequestDTO;

import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> getPaginatedBySearchRequest(AuthorPaginalRequestDTO authorPaginalRequestDTO){
        return authorRepository.findAllByFullNameLike(authorPaginalRequestDTO.getFullName(), authorPaginalRequestDTO.getPageRequest());
    }

    public Set<Author> findAllByIdIn(List<Long> authorsIds) {
        return authorRepository.findAllByIdIn(authorsIds);
    }

    public Author getById(Long Id){
        return authorRepository.findById(Id).orElseThrow(IllegalAccessError::new);
    }

    public void update(Author author) {
        if (authorRepository.existsById(author.getId())) {
            authorRepository.save(author);
        } else {
            Result.error(1, "Is not exist", new IllegalArgumentException());
        }
    }

    public void delete(Author author) {
        if (authorRepository.existsById(author.getId())) {
            authorRepository.delete(author);
        } else {
            Result.error(1, "Is not exist", new IllegalArgumentException());
        }
    }

    public Author create(Author author) {
        if (!authorRepository.existsById(author.getId())) {
            authorRepository.save(author);
        } else {
            Result.error(2, "Already exist", new IllegalArgumentException());
        }
        return author;
    }
}
