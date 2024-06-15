package com.stk.book.feedback;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public record FeedbackRequest(
        @Positive(message = "200")
        @Max(message = "201", value = 5)
        @Min(message = "202", value = 0)
        Double note,
        @NotNull(message = "203")
        @NotBlank(message = "203")
        @NotEmpty(message = "203")
        String comment,
        @NotNull(message = "Book id cannot be null")
        Integer bookId
) {
}
