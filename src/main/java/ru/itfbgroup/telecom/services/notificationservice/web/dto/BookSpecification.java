package ru.itfbgroup.telecom.services.notificationservice.web.dto;

import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import ru.itfbgroup.telecom.services.notificationservice.model.Book;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class BookSpecification implements Specification<Book> {

    @Getter
    private BookPaginalRequestDTO bookPaginalRequestDTO;

    public BookSpecification(BookPaginalRequestDTO bookPaginalRequestDTO) {
        this.bookPaginalRequestDTO = bookPaginalRequestDTO;
    }

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();

        if (bookPaginalRequestDTO.getName() != null) {
            predicates.add(builder.like(builder.lower(root.get("name")),
                    "%" + bookPaginalRequestDTO.getName().toLowerCase() + "%"));
        }
        if (bookPaginalRequestDTO.getIccid()!=null){
            predicates.add(builder.like(builder.lower(root.get("iccid")),
                    "%" + bookPaginalRequestDTO.getName().toLowerCase() + "%"));
        }
        if (bookPaginalRequestDTO.getPublishingHouseId() != null){
            predicates.add(builder.like(builder.lower(root.get("publishingHouseId")),
                    "%" + bookPaginalRequestDTO.getName().toLowerCase() + "%"));
        }
        if (bookPaginalRequestDTO.getAuthorId() != null){
            predicates.add(builder.like(builder.lower(root.get("authorId")),
                    "%" + bookPaginalRequestDTO.getName().toLowerCase() + "%"));
        }
        return builder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
