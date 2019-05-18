package by.training.model.impls;

import java.util.Date;
import java.util.List;

import by.training.ifaces.INoteDAO;
import by.training.model.beans.File;
import by.training.model.beans.Note;
import by.training.model.beans.User;

public class MemoryNodeImpl implements INoteDAO {

	@Override
	public List<Note> getNotes(User user) {
		throw new UnsupportedOperationException("getNotes");
	}

	@Override
	public void addNote(String content, Date date, User user) {
		throw new UnsupportedOperationException("addNote");
	}

	@Override
	public void performNotes(List<Note> list) {
		throw new UnsupportedOperationException("performNotes");
	}

	@Override
	public void recycleNotes(List<Note> selectedNotes) {
		throw new UnsupportedOperationException("recycleNotes");
	}
	
	@Override
	public void removeNotes(List<Note> selectedNotes) {
		throw new UnsupportedOperationException("removeNotes");
	}

	@Override
	public void restoreNotes(List<Note> selectedNotes) {
		throw new UnsupportedOperationException("restoreNotes");
	}

	@Override
	public List<Note> getTodayNotes(User user) {
		throw new UnsupportedOperationException("getTodayNotes");
	}

	@Override
	public List<Note> getTomorrowNotes(User user) {
		throw new UnsupportedOperationException("getTomorrowNotes");
	}

	@Override
	public List<Note> getSomedayNotes(User user) {
		throw new UnsupportedOperationException("getSomedayNotes");
	}

	@Override
	public List<Note> getPerformedNotes(User user) {
		throw new UnsupportedOperationException("getPerformedNotes");
	}

	@Override
	public List<Note> getRecycleNotes(User user) {
		throw new UnsupportedOperationException("getRecycleNotes");
	}

	@Override
	public Note getNote(User user, int id) {
		throw new UnsupportedOperationException("getNote");
	}

	@Override
	public void updateFileNote(int idNote, File file) {
		throw new UnsupportedOperationException("setFileNote");
	}
}
