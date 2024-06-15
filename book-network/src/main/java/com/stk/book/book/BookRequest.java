package com.stk.book.book;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record BookRequest(
        Integer id,
        @NotEmpty(message = "100")
        @NotNull(message = "100")
        String title,
        @NotEmpty(message = "101")
        @NotNull(message = "101")
        String authorName,
        @NotEmpty(message = "102")
        @NotNull(message = "102")
        String synopsis,
        @NotEmpty(message = "103")
        @NotNull(message = "103")
        String isbn,
        String bookCover,
        boolean shareable
) {
}
