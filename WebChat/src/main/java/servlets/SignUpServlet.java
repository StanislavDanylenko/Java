package servlets;

import base.Account;
import base.DBService;
import base.Frontend;
import exception.webException.SuchUserAlreadyExistException;
import exception.webException.UserException;
import exception.webException.GetUserException;
import sessions.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet implements Frontend {
    private final DBService dbService;
    private final Account accountService;

    public SignUpServlet(WebContext webContext) {
        this.dbService = webContext.get(DBService.class);
        this.accountService = webContext.get(Account.class);
    }

        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        if (!isEnterValid(login, pass)) {
            putAnswerInformation(BAD_REQUEST, response);
            response.getWriter().println("invalid data!");
            return;
        }

        try {
            if (registerNewUser(dbService, login, pass) != null) {
                putAnswerInformation(CREATED, response);
                response.getWriter().println("registered");
            }
        } catch (SuchUserAlreadyExistException e) {
            putAnswerInformation(BAD_REQUEST, response);
            response.getWriter().println("already registered!");
        } catch (GetUserException e) {
            putAnswerInformation(BAD_REQUEST, response);
            response.getWriter().println("invalid data");
        } catch (UserException e) {
            putAnswerInformation(BAD_REQUEST, response);
            response.getWriter().println("registration error");
        }
    }
}
