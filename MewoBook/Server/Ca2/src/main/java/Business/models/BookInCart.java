package Business.models;

import java.util.List;

public record  BookInCart(String username, long total, List<BorrowDetails> borrowDetails) {}
