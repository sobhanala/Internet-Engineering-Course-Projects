package Business.models;

import java.time.LocalDateTime;
import java.util.List;


public record PurchaseRecord(
        LocalDateTime purchaseDate,
        List<PurchaseItem> items,
        double totalCost
) {}