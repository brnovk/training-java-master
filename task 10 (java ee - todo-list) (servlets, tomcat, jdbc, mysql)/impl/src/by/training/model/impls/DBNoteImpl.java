package by.training.model.impls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.training.constans.ConstantsQueries;
import by.training.constans.ErrorMessage;
import by.training.exceptions.DaoException;
import by.training.ifaces.INoteDAO;
import by.training.model.beans.File;
import by.training.model.beans.Note;
import by.training.model.beans.User;
import by.training.model.db.ControlConnectionDB;

public class DBNoteImpl implements INoteDAO {

    private final ControlConnectionDB controlConnect = new ControlConnectionDB();

    @Override
    public Note getNote(User user, int id) {

        final int REQUEST_INDEX_USER_ID = 1;
        final int REQUEST_INDEX_NOTE_ID = 2;

        final int RESPONSE_INDEX_ID = 1;
        final int RESPONSE_INDEX_CONTENT = 2;
        final int RESPONSE_INDEX_DATE = 3;
        final int RESPONSE_INDEX_PERFORMED = 4;
        final int RESPONSE_INDEX_RECYCLE = 5;
        final int RESPONSE_INDEX_FILE_ID = 6;
        final int RESPONSE_INDEX_FILENAME = 7;

        Connection connection = null;
        PreparedStatement psSelectNotes = null;
        ResultSet resultSet = null;
        try {
            connection = controlConnect.getConnection();
            psSelectNotes = connection.prepareStatement(
                    ConstantsQueries.PS_SELECT_NOTE);
            psSelectNotes.setInt(REQUEST_INDEX_USER_ID, user.getId());
            psSelectNotes.setInt(REQUEST_INDEX_NOTE_ID, id);
            resultSet = psSelectNotes.executeQuery();
            if (resultSet.next()) {
                Note resultNote = new Note();
                resultNote.setId(resultSet.getInt(RESPONSE_INDEX_ID));
                resultNote.setContent(resultSet.getString(RESPONSE_INDEX_CONTENT));
                resultNote.setDate(resultSet.getDate(RESPONSE_INDEX_DATE));
                resultNote.setPerformed(resultSet.getBoolean(RESPONSE_INDEX_PERFORMED));
                resultNote.setRecycle(resultSet.getBoolean(RESPONSE_INDEX_RECYCLE));
                int idFile = resultSet.getInt(RESPONSE_INDEX_FILE_ID);
                if (idFile != 0) {
                    String filename = resultSet.getString(RESPONSE_INDEX_FILENAME);
                    File file = new File(idFile, filename);
                    resultNote.setFile(file);
                }
                return resultNote;
            } else {
                throw new DaoException(ErrorMessage.REQUEST_NOTE_ERROR);
            }
        } catch (ClassNotFoundException | IOException ex) {
            throw new DaoException(ErrorMessage.UNABLE_CONNECT_DB_ERROR, ex);
        } catch (SQLException ex) {
            throw new DaoException(ErrorMessage.UNKNOWN_QUERY_ERROR, ex);
        } finally {
            controlConnect.close(resultSet);
            controlConnect.close(psSelectNotes);
            controlConnect.close(connection);
        }
    }

    @Override
    public void addNote(String content, Date date, User user) {

        final int INDEX_CONTENT = 1;
        final int INDEX_DATE = 2;
        final int INDEX_ID_USER = 3;

        Connection connection = null;
        PreparedStatement psInsertNote = null;
        try {
            connection = controlConnect.getConnection();
            psInsertNote = connection.prepareStatement(
                    ConstantsQueries.PS_INSERT_NOTE);
            psInsertNote.setString(INDEX_CONTENT, content);
            psInsertNote.setDate(INDEX_DATE, new java.sql.Date(date.getTime()));
            psInsertNote.setInt(INDEX_ID_USER, user.getId());
            psInsertNote.executeUpdate();
        } catch (ClassNotFoundException | IOException ex) {
            throw new DaoException(ErrorMessage.UNABLE_CONNECT_DB_ERROR, ex);
        } catch (SQLException ex) {
            throw new DaoException(ErrorMessage.UNKNOWN_QUERY_ERROR, ex);
        } finally {
            controlConnect.close(psInsertNote);
            controlConnect.close(connection);
        }
    }

    @Override
    public void performNotes(List<Note> selectedNotes) {
        final int INDEX_PERFORMED = 1;
        final int INDEX_ID = 2;
        final boolean VALUE_PERFORMED = true;

        Connection connection = null;
        PreparedStatement psPerformNote = null;
        try {
            connection = controlConnect.getConnection();
            psPerformNote = connection.prepareStatement(
                    ConstantsQueries.PS_PERFORMED_UPDATE_NOTE);
            for (Note currentNote : selectedNotes) {
                psPerformNote.setBoolean(INDEX_PERFORMED, VALUE_PERFORMED);
                psPerformNote.setInt(INDEX_ID, currentNote.getId());
                psPerformNote.executeUpdate();
            }
        } catch (ClassNotFoundException | IOException ex) {
            throw new DaoException(ErrorMessage.UNABLE_CONNECT_DB_ERROR, ex);
        } catch (SQLException ex) {
            throw new DaoException(ErrorMessage.UNKNOWN_QUERY_ERROR, ex);
        } finally {
            controlConnect.close(psPerformNote);
            controlConnect.close(connection);
        }
    }

    @Override
    public void recycleNotes(List<Note> selectedNotes) {
        final int INDEX_RECYCLED = 1;
        final int INDEX_ID = 2;
        final boolean VALUE_RECYCLED = true;

        Connection connection = null;
        PreparedStatement psPerformNote = null;
        try {
            connection = controlConnect.getConnection();
            psPerformNote = connection.prepareStatement(
                    ConstantsQueries.PS_RECYCLED_UPDATE_NOTE);
            for (Note currentNote : selectedNotes) {
                psPerformNote.setBoolean(INDEX_RECYCLED, VALUE_RECYCLED);
                psPerformNote.setInt(INDEX_ID, currentNote.getId());
                psPerformNote.executeUpdate();
            }
        } catch (ClassNotFoundException | IOException ex) {
            throw new DaoException(ErrorMessage.UNABLE_CONNECT_DB_ERROR, ex);
        } catch (SQLException ex) {
            throw new DaoException(ErrorMessage.UNKNOWN_QUERY_ERROR, ex);
        } finally {
            controlConnect.close(psPerformNote);
            controlConnect.close(connection);
        }
    }

    @Override
    public void removeNotes(List<Note> selectedNotes) {
        final int INDEX_ID = 1;
        Connection connection = null;
        PreparedStatement psPerformNote = null;
        try {
            connection = controlConnect.getConnection();
            psPerformNote = connection.prepareStatement(
                    ConstantsQueries.PS_DELETE_NOTE);
            for (Note currentNote : selectedNotes) {
                psPerformNote.setInt(INDEX_ID, currentNote.getId());
                psPerformNote.executeUpdate();
            }
        } catch (ClassNotFoundException | IOException ex) {
            throw new DaoException(ErrorMessage.UNABLE_CONNECT_DB_ERROR, ex);
        } catch (SQLException ex) {
            throw new DaoException(ErrorMessage.UNKNOWN_QUERY_ERROR, ex);
        } finally {
            controlConnect.close(psPerformNote);
            controlConnect.close(connection);
        }
    }

    @Override
    public void restoreNotes(List<Note> selectedNotes) {
        final int INDEX_PERFORMED = 1;
        final int INDEX_RECYCLED = 2;
        final int INDEX_DATE = 3;
        final int INDEX_ID = 4;
        final boolean VALUE_PERFORMED = false;
        final boolean VALUE_RECYCLED = false;
        final Date todayDate = new Date();

        Connection connection = null;
        PreparedStatement psPerformNote = null;
        try {
            connection = controlConnect.getConnection();
            psPerformNote = connection.prepareStatement(
                    ConstantsQueries.PS_RESTORE_UPDATE_NOTE);
            for (Note currentNote : selectedNotes) {
                psPerformNote.setBoolean(INDEX_PERFORMED, VALUE_PERFORMED);
                psPerformNote.setBoolean(INDEX_RECYCLED, VALUE_RECYCLED);
                psPerformNote.setDate(INDEX_DATE,
                        new java.sql.Date(todayDate.getTime()));
                psPerformNote.setInt(INDEX_ID, currentNote.getId());
                psPerformNote.executeUpdate();
            }
        } catch (ClassNotFoundException | IOException ex) {
            throw new DaoException(ErrorMessage.UNABLE_CONNECT_DB_ERROR, ex);
        } catch (SQLException ex) {
            throw new DaoException(ErrorMessage.UNKNOWN_QUERY_ERROR, ex);
        } finally {
            controlConnect.close(psPerformNote);
            controlConnect.close(connection);
        }
    }

    @Override
    public List<Note> getNotes(User user) {
        return getSelectNotesByUser(user, ConstantsQueries.PS_SELECT_NOTES);
    }

    @Override
    public List<Note> getTodayNotes(User user) {
        return getSelectNotesByUser(user, ConstantsQueries.PS_SELECT_TODAY_NOTES);
    }

    @Override
    public List<Note> getTomorrowNotes(User user) {
        return getSelectNotesByUser(user, ConstantsQueries.PS_SELECT_TOMORROW_NOTES);
    }

    @Override
    public List<Note> getSomedayNotes(User user) {
        return getSelectNotesByUser(user, ConstantsQueries.PS_SELECT_SOMEDAY_NOTES);
    }

    @Override
    public List<Note> getPerformedNotes(User user) {
        return getSelectNotesByUser(user, ConstantsQueries.PS_SELECT_PERFORMED_NOTES);
    }

    @Override
    public List<Note> getRecycleNotes(User user) {
        return getSelectNotesByUser(user, ConstantsQueries.PS_SELECT_RECYCLE_NOTES);
    }

    private List<Note> getSelectNotesByUser(User user, String query) {

        final int EMPTY_ID_FILE = 0;

        final int INDEX_ID = 1;
        final int INDEX_CONTENT = 2;
        final int INDEX_DATE = 3;
        final int INDEX_PERFORMED = 4;
        final int INDEX_RECYCLE = 5;
        final int INDEX_FILE_ID = 6;
        final int INDEX_FILENAME = 7;

        List<Note> notes = new ArrayList<>();

        Connection connection = null;
        PreparedStatement psSelectNotes = null;
        ResultSet resultSet = null;
        try {
            connection = controlConnect.getConnection();
            psSelectNotes = connection.prepareStatement(query);
            psSelectNotes.setInt(INDEX_ID, user.getId());
            resultSet = psSelectNotes.executeQuery();
            while (resultSet.next()) {
                int currentId = resultSet.getInt(INDEX_ID);
                String currentContent = resultSet.getString(INDEX_CONTENT);
                Date currentDate = resultSet.getDate(INDEX_DATE);
                boolean currentPerformed = resultSet.getBoolean(INDEX_PERFORMED);
                boolean currentRecycle = resultSet.getBoolean(INDEX_RECYCLE);
                int idFile = resultSet.getInt(INDEX_FILE_ID);
                File currentFile = null;
                if (idFile != EMPTY_ID_FILE) {
                    String filename = resultSet.getString(INDEX_FILENAME);
                    currentFile = new File(idFile, filename);
                }
                notes.add(new Note(currentId, currentContent, currentDate,
                        currentPerformed, currentRecycle, user, currentFile));
            }
            return notes;
        } catch (ClassNotFoundException | IOException ex) {
            throw new DaoException(ErrorMessage.UNABLE_CONNECT_DB_ERROR, ex);
        } catch (SQLException ex) {
            throw new DaoException(ErrorMessage.UNKNOWN_QUERY_ERROR, ex);
        } finally {
            controlConnect.close(resultSet);
            controlConnect.close(psSelectNotes);
            controlConnect.close(connection);
        }
    }

    @Override
    public void updateFileNote(int idNote, File file) {
        final int INDEX_ID_FILE = 1;
        final int INDEX_ID_NOTE = 2;

        Connection connection = null;
        PreparedStatement psInsertNote = null;
        try {
            connection = controlConnect.getConnection();
            psInsertNote = connection.prepareStatement(
                    ConstantsQueries.PS_UPDATE_FILE_NOTE);
			if (file == null) {
				psInsertNote.setObject(INDEX_ID_FILE, null);
			} else {
				psInsertNote.setInt(INDEX_ID_FILE, file.getId());
			}
            psInsertNote.setInt(INDEX_ID_NOTE, idNote);
            psInsertNote.executeUpdate();
        } catch (ClassNotFoundException | IOException ex) {
            throw new DaoException(ErrorMessage.UNABLE_CONNECT_DB_ERROR, ex);
        } catch (SQLException ex) {
            throw new DaoException(ErrorMessage.UNKNOWN_QUERY_ERROR, ex);
        } finally {
            controlConnect.close(psInsertNote);
            controlConnect.close(connection);
        }
    }
}
