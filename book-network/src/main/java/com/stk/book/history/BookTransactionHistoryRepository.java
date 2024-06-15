package com.stk.book.history;

import com.stk.book.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookTransactionHistoryRepository extends JpaRepository<BookTransactionHistory, Integer> {
    @Query("""
            SELECT history.book
            FROM BookTransactionHistory history
            WHERE history.user.id = :userId
            """)
    Page<Book> findAllBorrowedBooks(Pageable pageable, Integer userId);
    @Query("""
            SELECT history.book
            FROM BookTransactionHistory history
            WHERE history.user.id = :userId
            AND history.returned = true
            """)
    Page<Book> findAllReturnBooks(Pageable pageable, Integer userId);

    @Query("""
            SELECT
            (COUNT(*) > 1)
            FROM BookTransactionHistory history
            WHERE history.user.id = :userId
            AND history.book.id = :bookId
            AND history.returnApproved = false
            """)
    boolean isAlreadyBorrowedByUser(Integer bookId, Integer userId);

    @Query("""
            SELECT
            (COUNT(*) > 1)
            FROM BookTransactionHistory history
            WHERE history.book.id = :bookId
            AND history.returnApproved = false
            """)
    boolean isAlreadyBorrowed(Integer bookId);
    @Query("""
            SELECT history
            FROM BookTransactionHistory history
            WHERE history.book.id = :bookId
            AND history.user.id = :userId
            AND history.returned = false
            AND history.returnApproved = false
            """)
    Optional<BookTransactionHistory> findByUserIdAndBookId(Integer bookId, Integer userId);
    @Query("""
            SELECT history
            FROM BookTransactionHistory history
            WHERE history.book.id = :bookId
            AND history.book.owner.id = :userId
            AND history.returned = true
            AND history.returnApproved = false
            """)
    Optional<BookTransactionHistory> findByUserIdAndBookIdWithApproveReturnFalseAndReturnedTrue(Integer bookId, Integer userId);
}
