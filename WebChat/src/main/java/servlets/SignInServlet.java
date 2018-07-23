package servlets;

import base.Account;
import base.DBService;
import base.Frontend;
import exception.DBException;
import dbService.dataSets.UsersDataSet;
import exception.webException.GetUserException;
import exception.webException.UserException;
import exception.webException.ValidationUserException;
import sessions.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SignInServlet extends HttpServlet implements Frontend {
    private final DBService dbService;
    private final Account accountService;

    public SignInServlet(WebContext webContext) {
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
            putAnswerInformation(UNAUTHORIZED, response);
            response.getWriter().println("Unauthorized");
            return;
        }

        try {
            if (loginUser(dbService, login, pass, accountService, request) != null) {
                putAnswerInformation(OK, response);
                response.getWriter().println("Authorized: " + login);
            }
        } catch (ValidationUserException e) {
            putAnswerInformation(BAD_REQUEST, response);
            response.getWriter().println(e.getMessage());
        } catch (GetUserException e) {
            putAnswerInformation(BAD_REQUEST, response);
            response.getWriter().println("invalid data");
        }

    }

}
