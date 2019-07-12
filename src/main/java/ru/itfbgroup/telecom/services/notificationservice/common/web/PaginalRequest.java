package ru.itfbgroup.telecom.services.notificationservice.common.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

@Data
public class PaginalRequest {

    Integer limit = 20;
    Integer offset = 0;
    String direction = "ASC";
    String sortColumn;

    public Pageable getPageRequest(String sortColumn) {
        this.sortColumn = sortColumn;
        return getPageRequest();
    }

    public Pageable getPageRequest(String direction, String sortColumn) {
        this.sortColumn = sortColumn;
        this.direction = direction;
        return getPageRequest();
    }

    @JsonIgnore
    public Pageable getPageRequest() {
        if (StringUtils.hasText(this.sortColumn))
            return PageRequest.of(offset / limit, limit, Sort.Direction.fromString(direction), sortColumn);
        return PageRequest.of(offset / limit, limit);
    }
}
