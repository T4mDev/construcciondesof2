package app.adapter.in.rest.request;

public class StockItemRequest {

	private String id;
	private String itemName;
	private String quantity;
	private String itemType;

	public StockItemRequest() {
	}

	public StockItemRequest(String id, String itemName, String quantity, String itemType) {
		this.id = id;
		this.itemName = itemName;
		this.quantity = quantity;
		this.itemType = itemType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
}
