package base;

import exception.DBException;
import dbService.dataSets.UsersDataSet;
import exception.webException.SuchUserAlreadyExistException;
import exception.webException.UserException;
import exception.webException.GetUserException;
import exception.webException.ValidationUserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Frontend {

    int UNAUTHORIZED = 401;
    int OK = 200;
    int CREATED = 201;
    int BAD_REQUEST = 400;

    default Long registerNewUser(DBService dbService, String login, String password)
            throws UserException {
        UsersDataSet existingUser = null;
        try {
            existingUser = getUserWithParams(dbService, login);
        } catch (DBException e) {
            throw new GetUserException(e);
        }

        if (existingUser != null) {
            throw new SuchUserAlreadyExistException("User with such login is already exists");
        }
        Long newUserId = null;
        try {
            newUserId = dbService.addUser(login, password);
        } catch (DBException e) {
            throw new UserException(e);
        }

        return newUserId;
    }

    default Long loginUser(DBService dbService, String login, String password, AccountManager accountManagerService, HttpServletRequest request)
            throws GetUserException, ValidationUserException {
        UsersDataSet profile = validateUser(dbService, login, password);
        return addUser(profile, accountManagerService, request);
    }

    private UsersDataSet validateUser(DBService dbService, String login, String password) throws GetUserException, ValidationUserException {
        UsersDataSet profile = null;
        try {
            profile = dbService.getUser(login);
        } catch (DBException e) {
            throw new GetUserException(e);
        }
        if (profile == null || !profile.getPassword().equals(password)) {
            throw new ValidationUserException("Such combinations of log and password are not found");
        }
        return profile;
    }

    private Long addUser(UsersDataSet profile, AccountManager accountManagerService, HttpServletRequest request) {
        String login = profile.getLogin();

        accountManagerService.addUser(login);
        accountManagerService.addHttpSession(login, request.getSession());
        accountManagerService.addUserDataSet(login, profile);
        return profile.getId();

    }

    private UsersDataSet getUserWithParams(DBService dbService, String login) throws DBException {
        return dbService.getUser(login);
    }

    default boolean isEnterValid(String login, String password) {
        if (login == null || password == null || "".equals(login) || "".equals(password)) {
            return false;
        }
        return true;
    }

    default void putAnswerInformation(int returnCode, HttpServletResponse response) throws IOException {
        switch (returnCode) {
            case UNAUTHORIZED:
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                break;
            case BAD_REQUEST:
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            case CREATED:
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_CREATED);
            default:
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
        }
    }

}
