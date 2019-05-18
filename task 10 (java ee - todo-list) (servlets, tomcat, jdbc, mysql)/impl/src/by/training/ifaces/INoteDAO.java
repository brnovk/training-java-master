package by.training.ifaces;

import java.util.Date;
import java.util.List;

import by.training.model.beans.*;

public interface INoteDAO {
	
	public Note getNote(User user, int id);
    public List<Note> getNotes(User user);
    public void addNote(String content, Date date, User user);
	public void performNotes(List<Note> list);
	public void recycleNotes(List<Note> selectedNotes);
	public void removeNotes(List<Note> selectedNotes);
	public void restoreNotes(List<Note> selectedNotes);

	public List<Note> getTodayNotes(User user);
	public List<Note> getTomorrowNotes(User user);
    public List<Note> getSomedayNotes(User user);
    public List<Note> getPerformedNotes(User user);
    public List<Note> getRecycleNotes(User user);

    // TODO There is no certainty about this method
    public void updateFileNote(int idNote, File file);
}
