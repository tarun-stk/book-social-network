package com.stk.book.feedback;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackResponse {
    private String comment;
    private Double note;
    private boolean ownFeedback;
}
