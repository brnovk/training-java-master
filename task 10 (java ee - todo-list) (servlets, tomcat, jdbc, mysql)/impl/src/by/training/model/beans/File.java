package by.training.model.beans;

import by.training.constans.Constants;

public class File {
	
	private int id;
    private String name;
    
	public File() {
        id = Constants.EMPTY_ID;
    }

	public File(String name) {
		super();
		this.name = name;
	}

	public File(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	// getters

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	// setters

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
}
