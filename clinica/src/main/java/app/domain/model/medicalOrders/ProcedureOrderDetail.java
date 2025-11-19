package app.domain.model.medicalOrders;

public class ProcedureOrderDetail {
    private String id;
    private int quantity;
    private String frequency;
    private boolean requiresSpecialist;
    private Integer specialtyId;
    private String itemNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public boolean isRequiresSpecialist() {
        return requiresSpecialist;
    }

    public void setRequiresSpecialist(boolean requiresSpecialist) {
        this.requiresSpecialist = requiresSpecialist;
    }

    public Integer getSpecialistTypeId() {
        return specialtyId;
    }

    public void setSpecialistTypeId(Integer specialistTypeId) {
        this.specialtyId = specialistTypeId;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }
}
