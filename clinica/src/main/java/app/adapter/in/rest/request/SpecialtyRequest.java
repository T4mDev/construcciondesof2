package app.adapter.in.rest.request;

public class SpecialtyRequest {

	private String id;
	private String name;

	public SpecialtyRequest() {
	}

	public SpecialtyRequest(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
