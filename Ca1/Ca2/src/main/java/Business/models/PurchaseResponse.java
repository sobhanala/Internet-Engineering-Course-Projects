package Business.models;

import lombok.Getter;

import java.util.List;
@Getter
public class PurchaseResponse {
    private String username;
    private List<PurchaseRecord> record;

    public PurchaseResponse(String username, List<PurchaseRecord> purchaseHistory) {
        this.username=username;
        this.record=purchaseHistory;
    }
}
