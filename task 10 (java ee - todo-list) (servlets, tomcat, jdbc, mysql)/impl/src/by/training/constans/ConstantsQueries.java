package by.training.constans;

public class ConstantsQueries {

    public final static String PS_SELECT_USER
            = "SELECT id, login, password, email "
            + "FROM users "
            + "WHERE login=? AND password=?";

    public final static String PS_SELECT_LOGIN_USER
            = "SELECT login FROM users WHERE login=?";

    public final static String PS_INSERT_USER
            = "INSERT INTO users (login, password, email) VALUES(?,?,?)";

    public final static String PS_INSERT_NOTE
            = "INSERT INTO notes (content, date, userId) VALUES(?,?,?)";

    public final static String PS_SELECT_NOTE
            = "SELECT notes.id, content, date, performed, recycle, files.id, filename "
            + "FROM notes "
            + "LEFT JOIN files ON notes.fileId = files.id "
            + "WHERE userId=? AND notes.id=?";

    public final static String PS_SELECT_NOTES
            = "SELECT notes.id, content, date, performed, recycle, files.id, filename "
            + "FROM notes "
            + "LEFT JOIN files ON notes.fileId = files.id "
            + "WHERE userId=? ";

    public final static String PS_PERFORMED_UPDATE_NOTE
            = "UPDATE notes SET performed = ? WHERE id = ?";

    public final static String PS_RECYCLED_UPDATE_NOTE
            = "UPDATE notes SET recycle = ? WHERE id = ?";

    public final static String PS_RESTORE_UPDATE_NOTE
            = "UPDATE notes SET performed = ?, recycle = ?, date = ?  "
            + "WHERE id = ?";

    public final static String PS_DELETE_NOTE
            = "DELETE notes, files "
            + "FROM notes "
            + "LEFT JOIN files ON notes.fileId = files.id "
            + "WHERE notes.id = ?";

    public final static String PS_SELECT_TODAY_NOTES
            = "SELECT notes.id, content, date, performed, recycle, files.id, filename "
            + "FROM notes "
            + "LEFT JOIN files ON notes.fileId = files.id "
            + "WHERE userId = ? "
            + "AND date = DATE(NOW()) "
            + "AND recycle = FALSE "
            + "AND performed = FALSE";

    public final static String PS_SELECT_TOMORROW_NOTES
            = "SELECT notes.id, content, date, performed, recycle, files.id, filename "
            + "FROM notes "
            + "LEFT JOIN files ON notes.fileId = files.id "
            + "WHERE userId = ? "
            + "AND date = DATE(NOW() + INTERVAL 1 DAY) "
            + "AND recycle = FALSE "
            + "AND performed = FALSE";

    public final static String PS_SELECT_SOMEDAY_NOTES
            = "SELECT notes.id, content, date, performed, recycle, files.id, filename "
            + "FROM notes "
            + "LEFT JOIN files ON notes.fileId = files.id "
            + "WHERE userId = ? "
            + "AND `date` <> DATE(NOW()) "
            + "AND `date` <> DATE(NOW() + INTERVAL 1 DAY) "
            + "AND recycle = FALSE "
            + "AND performed = FALSE";

    public final static String PS_SELECT_PERFORMED_NOTES
            = "SELECT notes.id, content, date, performed, recycle, files.id, filename "
            + "FROM notes "
            + "LEFT JOIN files ON notes.fileId = files.id "
            + "WHERE userId = ? "
            + "AND recycle = FALSE "
            + "AND performed = TRUE";

    public final static String PS_SELECT_RECYCLE_NOTES
            = "SELECT notes.id, content, date, performed, recycle, files.id, filename "
            + "FROM notes "
            + "LEFT JOIN files ON notes.fileId = files.id "
            + "WHERE userId = ? "
            + "AND recycle = TRUE";

    public final static String PS_UPDATE_FILE_NOTE
            = "UPDATE notes SET fileId=? WHERE id=?";

    public final static String PS_INSERT_FILE
            = "INSERT INTO files (filename) VALUES(?)";

    public final static String PS_DELETE_FILE
            = "DELETE files "
            + "FROM files "
            + "WHERE id = ?";
}
