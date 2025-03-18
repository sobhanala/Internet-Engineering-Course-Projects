package Business.models;

import java.time.LocalDateTime;

public record BorrowedBook(Book book, LocalDateTime expiration) {}