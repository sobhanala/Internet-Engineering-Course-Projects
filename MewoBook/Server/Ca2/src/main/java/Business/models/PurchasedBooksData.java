package Business.models;


import java.util.List;
public record PurchasedBooksData(
        String username,
        List<PurchaseItem> books
) {}
