package ua.kh.epam.dao.db.memory;

public final class SQLQueriesConstants {

    public static final String SQL_CREATE_USER = "INSERT INTO users VALUES (?, ?, ?, ?, ?)";

    public static final String SQL_GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";

    public static final String SQL_GET_ALL_USERS = "SELECT * FROM users";

    public static final String SQL_UPDATE_USER = "UPDATE users SET first_name=?, last_name=?, login=?, email=?, password=?";

    public static final String SQL_DELETE_USER = "DELETE FROM users WHERE login=?";


    public static final String SQL_CREATE_VINYL = "INSERT INTO vinyls VALUES (0, ?, ?, ?, ?, ?)";

    public static final String SQL_GET_ALL_VINYLS = "SELECT * FROM vinyls";

    public static final String SQL_GET_VINYL_BY_ID = "SELECT * FROM vinyls WHERE id_vinyl=?";

    public static final String SQL_UPDATE_VINYL = "UPDATE vinyls SET id_album=?, price=?, date_arrival=?, length=?, width=? WHERE id_vinyl=?";

    public static final String SQL_DELETE_VINYL = "DELETE FROM vinyls WHERE id_vinyl=?";

}
