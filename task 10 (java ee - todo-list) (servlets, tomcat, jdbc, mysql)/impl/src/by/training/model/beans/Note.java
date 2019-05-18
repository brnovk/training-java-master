package by.training.model.beans;

import java.io.Serializable;
import java.util.Date;

import by.training.constans.Constants;

public class Note implements Serializable {

	private static final long serialVersionUID = 8980382488464139098L;

	private int id;
	private String content;
	private Date date;
	private boolean performed;
    private boolean recycle;
	private User user;
    private File file;

	public Note() {
		id = Constants.EMPTY_ID;
	}
	
	public Note(int id, String content, Date date) {
		this.id = id;
		this.content = content;
		this.date = date;
	}
	
	public Note(int id, String content, Date date, boolean performed,
			boolean recycle, User user) {
		this.id = id;
		this.content = content;
		this.date = date;
		this.performed = performed;
		this.recycle = recycle;
		this.user = user;
	}
	
	public Note(int id, String content, Date date, boolean performed,
			boolean recycle, User user, File file) {
		this.id = id;
		this.content = content;
		this.date = date;
		this.performed = performed;
		this.recycle = recycle;
		this.user = user;
		this.file = file;
	}

	// getters
	
	public int getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public Date getDate() {
		return date;
	}

	public User getUser() {
		return user;
	}
	
	public boolean isPerformed() {
		return performed;
	}
	
	public boolean isRecycle() {
		return recycle;
	}
	
	public File getFile() {
		return file;
	}
	
	// setters

	public void setId(int id) {
		this.id = id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setPerformed(boolean performed) {
		this.performed = performed;
	}

	public void setRecycle(boolean recycle) {
		this.recycle = recycle;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
