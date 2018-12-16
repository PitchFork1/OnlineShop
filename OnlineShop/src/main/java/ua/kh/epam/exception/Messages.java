package ua.kh.epam.exception;

public final class Messages {

    // /////////////////////////////////////////////////////////////////
    // //////////////////DB exceptions/////////////////////////////////////
    // ////////////////////////////////////////////////////////////////

    public static final String ERROR_CANNOT_INIT_POOL_CONNECTIONS = "error.db.init.pooledDataSource";

    public static final String ERROR_CANNOT_OBTAIN_CONNECTION = "error.db.obtain.connection";

    public static final String ERROR_CANNOT_ROLLBACK_TRANSACTION = "error.db.rollback.transaction";

    public static final String ERROR_CANNOT_CLOSE_CONNECTION = "error.db.close.connection";

    public static final String ERROR_CANNOT_CLOSE_RESULT_SET = "error.db.close.resultSet";

    public static final String ERROR_CANNOT_CLOSE_STATEMENT = "error.db.close.statement";

    public static final String ERROR_CANNOT_OBTAIN_DATA_SOURCE = "error.db.datasource";


    public static final String ERROR_CANNOT_OBTAIN_USERS = "error.db.obtain.users";

    public static final String ERROR_CANNOT_OBTAIN_USER_BY_LOGIN = "error.db.obtain.user.login";

    public static final String ERROR_CANNOT_CREATE_USER = "error.db.create.user";

    public static final String ERROR_CANNOT_UPDATE_USER = "error.db.update.user";

    public static final String ERROR_CANNOT_UPDATE_USER_ROLE = "error.db.update.user.role";

    public static final String ERROR_CANNOT_UPDATE_USER_BLOCK = "error.db.update.user.block";

    public static final String ERROR_CANNOT_DELETE_USER = "error.db.delete.user";


    public static final String ERROR_CANNOT_CREATE_VINYL = "error.db.create.vinyl";

    public static final String ERROR_CANNOT_OBTAIN_VINYLS = "error.db.obtain.vinyls";

    public static final String ERROR_CANNOT_OBTAIN_VINYL_BEANS = "error.db.obtain.vinylBeans";

    public static final String ERROR_CANNOT_OBTAIN_VINYL_BY_ID = "error.db.obtain.vinyl.id";

    public static final String ERROR_CANNOT_UPDATE_VINYL = "error.db.update.vinyl";

    public static final String ERROR_CANNOT_DELETE_VINYL = "error.db.delete.vinyl";

    // /////////////////////////////////////////////////////////////////
	// //////////////////App exceptions/////////////////////////////////////
	// ////////////////////////////////////////////////////////////////

    public static final String EXCEPTION_REGISTRATION_LOGIN_EXISTS = "error.registration.login.exists";

    public static final String EXCEPTION_REGISTRATION_EMAIL_EXISTS = "error.registration.email.exists";

    public static final String EXCEPTION_REGISTRATION_PASSWORD_EXISTS = "error.registration.password.exists";

    public static final String EXCEPTION_CAPTCHA_WRONG = "error.registration.captcha.wrong";

    public static final String EXCEPTION_REGISTRATION_TIMEOUT = "error.registration.timeout";

}
