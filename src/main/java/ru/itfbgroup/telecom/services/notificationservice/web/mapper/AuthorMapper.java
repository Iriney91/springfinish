package ru.itfbgroup.telecom.services.notificationservice.web.mapper;

import org.mapstruct.Mapper;
import ru.itfbgroup.telecom.services.notificationservice.model.Author;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.AuthorInDTO;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.AuthorOutDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    Author dtoToAuthor (AuthorInDTO authorDTO);
    AuthorOutDTO authorToDto (Author author);

    List<Author> dtoToAuthor(List<AuthorInDTO> authorInDTO);
    List<AuthorOutDTO> authorToDto(List<Author> author);
}
