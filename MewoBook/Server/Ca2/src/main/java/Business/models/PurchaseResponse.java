package Business.models;

import java.util.List;

public class PurchaseResponse {
    private String username;
    private List<PurchaseRecord> record;

    public PurchaseResponse(String username, List<PurchaseRecord> purchaseHistory) {
        this.username=username;
        this.record=purchaseHistory;
    }
}
