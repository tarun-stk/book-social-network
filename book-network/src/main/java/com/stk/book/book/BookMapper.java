package com.stk.book.book;

import org.springframework.stereotype.Service;
import com.stk.book.file.FileUtils;

@Service
public class BookMapper {

    public Book toBook(BookRequest bookRequest) {
        if(bookRequest == null){
            throw new NullPointerException("bookRequest should not be null");
        }
        return Book.builder()
                .id(bookRequest.id())
                .authorName(bookRequest.authorName())
                .isbn(bookRequest.isbn())
                .synopsis(bookRequest.synopsis())
                .title(bookRequest.title())
                .archived(false)
                .shareable(bookRequest.shareable())
                .build();
    }

    public BookResponse toBookResponse(Book book) {
        if(book == null){
            throw new NullPointerException("book should not be null");
        }
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .authorName(book.getAuthorName())
                .isbn(book.getIsbn())
                .synopsis(book.getSynopsis())
                .rate(book.getRate())
                .archived(book.isArchived())
                .shareable(book.isShareable())
                .owner(book.getOwner().getFullName())
                .cover(FileUtils.readFileFromLocation(book.getBookCover()))
                .build();
    }

    public BorrowedBookResponse toBorrowedBookResponse(Book book) {
        if(book == null){
            throw new NullPointerException("book should not be null");
        }
        return BorrowedBookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .authorName(book.getAuthorName())
                .isbn(book.getIsbn())
                .rate(book.getRate())
                .build();
    }
}
