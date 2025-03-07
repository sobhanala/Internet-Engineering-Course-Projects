package Business.models;

import java.time.LocalDateTime;

record BorrowedBook(Book book, LocalDateTime expiration) {}