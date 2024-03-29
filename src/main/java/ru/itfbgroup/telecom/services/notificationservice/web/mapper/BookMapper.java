package ru.itfbgroup.telecom.services.notificationservice.web.mapper;

import org.mapstruct.Mapper;
import ru.itfbgroup.telecom.services.notificationservice.component.DateMapper;
import ru.itfbgroup.telecom.services.notificationservice.model.Book;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.BookInDTO;
import ru.itfbgroup.telecom.services.notificationservice.web.dto.BookOutDTO;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                DateMapper.class, PublishingHouseMapper.class
        })
public interface BookMapper {

    Book dtoToBook (BookInDTO bookDTO);
    BookOutDTO bookToDto (Book book);

    List<Book> dtoToBook(List<BookInDTO> bookInDTO);
    List<BookOutDTO> bookToDto(List<Book> book);
}