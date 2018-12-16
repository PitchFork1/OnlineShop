package ua.kh.epam.exception;

import java.sql.SQLException;

public class DBException extends SQLException {

	public DBException() {
		super();
	}

	public DBException(String message, Throwable cause) {
		super(message, cause);
	}

}
