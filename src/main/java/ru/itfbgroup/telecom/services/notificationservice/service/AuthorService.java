package ru.itfbgroup.telecom.services.notificationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.itfbgroup.telecom.services.notificationservice.model.Author;
import ru.itfbgroup.telecom.services.notificationservice.repository.AuthorRepository;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.AuthorPaginalRequestDTO;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Page<Author> getPaginatedBySearchRequest(AuthorPaginalRequestDTO authorPaginalRequestDTO) {
        if (authorPaginalRequestDTO.getSortColumn() == null)
            authorPaginalRequestDTO.setSortColumn("fullName");
        if (authorPaginalRequestDTO.getFullName() == null)

            return authorRepository.findAll(authorPaginalRequestDTO.getPageRequest());

        return authorRepository.findAllByFullNameLike(authorPaginalRequestDTO.getFullName(), authorPaginalRequestDTO.getPageRequest());
    }

    public Set<Author> findAllByIdIn(List<Long> authorsIds) {
        return authorRepository.findAllByIdIn(authorsIds);
    }

    public Author getById(Long id) {
        return authorRepository.findById(id).orElseThrow(IllegalAccessError::new);
    }

    public Author update(Author author) {
        if (authorRepository.existsById(author.getId())) {
            authorRepository.save(author);
        } else {
            throw new IllegalArgumentException("No such author found");
        }
        return author;
    }

    public void delete(Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No such author found");
        }
    }

    public Author create(Author author) {
            return authorRepository.save(author);
    }
}
