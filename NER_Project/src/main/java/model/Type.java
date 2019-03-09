package model;

public enum Type {
	PERSON("Person"),
	CITY("City"),
	STATE_OR_PROVINCE("State_OR_Province"),
	EMAIL("Email"),
	COUNTRY("Country"),
	TITLE("Title");
	
	private String type;
	
	Type(String type){
		this.type = type;
	}
	
	public String getName() {
		return this.type;
	}

}
