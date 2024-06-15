package com.stk.book.book;

import com.stk.book.feedback.Feedback;
import com.stk.book.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookMapperTest {

    private BookMapper mapper;

    @BeforeEach
    public void testBookRequestToBook() {
        mapper = new BookMapper();
    }

    @Test
    public void shouldMapBookToBookResponse() {
//        set up data
        User owner = User.builder()
                .id(1)
                .firstname("user")
                .lastname("1")
                .email("user@example.com")
                .accountLocked(false)
                .enabled(false)
                .build();
        Book book = Book.builder()
                .id(1)
                .isbn("123465")
                .title("Clean Code")
                .authorName("Arthur")
                .archived(false)
                .synopsis("Clean Code")
                .owner(owner)
                .feedbacks(Collections.singletonList(
                        Feedback.builder()
                                .note(3.0)
                                .comment("Average")
                                .build())
                )
                .build();
        BookResponse bookResponse = mapper.toBookResponse(book);
        assertEquals(book.getId(), bookResponse.getId());
        assertEquals(book.getSynopsis(), bookResponse.getSynopsis());
        assertEquals(book.getTitle(), bookResponse.getTitle());
        assertEquals(book.getOwner().getFullName(), bookResponse.getOwner());
        assertEquals(book.getAuthorName(), bookResponse.getAuthorName());
        assertEquals(book.getIsbn(), bookResponse.getIsbn());

    }

    @Test
    public void shouldThrowNullPointerExceptionWhenBookIsNull(){
        var exp = assertThrows(NullPointerException.class, () -> mapper.toBookResponse(null));
        assertEquals("book should not be null", exp.getMessage());

        var exp1 = assertThrows(NullPointerException.class, () -> mapper.toBorrowedBookResponse(null));
        assertEquals("book should not be null", exp1.getMessage());
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenBookRequestIsNull(){
        var exp = assertThrows(NullPointerException.class, () -> mapper.toBook(null));
        assertEquals("bookRequest should not be null", exp.getMessage());
    }

    @Test
    public void shouldMapBookRequestToBook() {
        BookRequest bookRequest = new BookRequest(1, "Clean code", "Aurther",
                "synopsis", "isbn", "/serc", false);

        Book book = mapper.toBook(bookRequest);
        assertEquals(bookRequest.id(), book.getId());
        assertEquals(bookRequest.synopsis(), book.getSynopsis());
        assertEquals(bookRequest.title(), book.getTitle());
        assertEquals(bookRequest.authorName(), book.getAuthorName());
        assertEquals(bookRequest.isbn(), book.getIsbn());
    }

    @Test
    public void shouldMapBookToBorrowedBookResponse() {
        Book book = Book.builder()
                .id(1)
                .isbn("123465")
                .title("Clean Code")
                .authorName("Arthur")
                .archived(false)
                .synopsis("Clean Code")
                .feedbacks(Collections.singletonList(
                        Feedback.builder()
                                .note(3.0)
                                .comment("Average")
                                .build())
                )
                .build();

        BorrowedBookResponse borrowedBookResponse = mapper.toBorrowedBookResponse(book);
        assertEquals(book.getId(), borrowedBookResponse.getId());
        assertEquals(book.getTitle(), borrowedBookResponse.getTitle());
        assertEquals(book.getAuthorName(), borrowedBookResponse.getAuthorName());
        assertEquals(book.getIsbn(), borrowedBookResponse.getIsbn());
        assertEquals(book.getRate(), borrowedBookResponse.getRate());
    }

}