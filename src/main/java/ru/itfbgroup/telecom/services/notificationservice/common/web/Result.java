package ru.itfbgroup.telecom.services.notificationservice.common.web;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Arrays;

    @XmlRootElement
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class Result<T> implements Serializable {

        private int code;
        private String message;
        private Error error;
        private T data;

        public Result(int code, String message, T data) {
            this.code = code;
            this.message = message;
            this.data = data;
        }

        public static Result success() {
            return success(null);
        }

        public static <T> Result<T> success(final T data) {
            return new Result<>(0, null, data);
        }

        public static <T> Result<T> error(int code, String message) {
            return new Result<>(code, message, null);
        }

        public static <T> Result<T> error(int code, String message, final T data) {
            return new Result<>(code, message, data);
        }


        public static <T> Result<T> error(int code, String message, Exception ex) {
            return error(null, code, message, ex);
        }

        public static <T> Result<T> error(final  T data, int code, String message, Exception ex) {
            Result<T> result = new Result<>(code, message, data);

            if (ex != null) {
                Error error = new Error(
                        ex.getMessage(),
                        Arrays.stream(ex.getStackTrace()).map(StackTraceElement::toString).toArray(String[]::new));
                result.setError(error);
            }
            return result;
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class Error {
            private String text;
            private String[] trace;
        }
}
