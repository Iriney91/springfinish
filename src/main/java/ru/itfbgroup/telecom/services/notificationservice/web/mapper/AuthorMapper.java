package ru.itfbgroup.telecom.services.notificationservice.web.mapper;

import org.mapstruct.Mapper;
import ru.itfbgroup.telecom.services.notificationservice.model.Author;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.AuthorInDTO;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.AuthorOutDTO;

@Mapper
public interface AuthorMapper {

    Author dtoToAuthor (AuthorInDTO authorDTO);
    AuthorOutDTO authorToDto (Author author);
}
