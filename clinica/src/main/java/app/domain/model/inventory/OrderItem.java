package app.domain.model.inventory;

public class OrderItem {
    private String itemCode;
    private String category;
    private String linkedId;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLinkedId() {
        return linkedId;
    }

    public void setLinkedId(String linkedId) {
        this.linkedId = linkedId;
    }
}
