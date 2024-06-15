package com.stk.book.book;

import com.stk.book.file.FileStorageService;
import com.stk.book.history.BookTransactionHistoryRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    @InjectMocks
    private BookService service;

    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;
    @Mock
    private BookTransactionHistoryRepository transactionHistoryRepository;
    @Mock
    private FileStorageService fileStorageService;

}