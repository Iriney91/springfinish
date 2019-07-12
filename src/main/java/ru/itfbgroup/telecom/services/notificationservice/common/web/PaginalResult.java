package ru.itfbgroup.telecom.services.notificationservice.common.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuppressWarnings({"unused", "WeakerAccess"})
public class PaginalResult<T> extends Result<T> {
    private int offset;
    private int limit;
    private long total;

    public PaginalResult() {
    }

    public PaginalResult(T data, int offset, int limit, long total) {
        super(0, null, data);
        this.offset = offset;
        this.limit = limit;
        this.total = total;
    }

    public static <T> PaginalResult<T> success(final T data, int offset, int limit, long total) {
        return new PaginalResult<>(data, offset, limit, total);
    }

}

