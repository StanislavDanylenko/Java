package servlets;

import base.Account;
import base.DBService;
import base.Frontend;
import exception.webException.SuchUserAlreadyExistException;
import exception.webException.UserException;
import exception.webException.GetUserException;
import sessions.WebContext;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        String message = null;

        if (!isEnterValid(login, pass)) {
            putAnswerInformation(BAD_REQUEST, response);
            message = "invalid data!";
        } else {

            try {
                if (registerNewUser(dbService, login, pass) != null) {
                    putAnswerInformation(CREATED, response);
                    message = "registered";
                }
            } catch (SuchUserAlreadyExistException e) {
                putAnswerInformation(BAD_REQUEST, response);
                message = "already registered!";
            } catch (GetUserException e) {
                putAnswerInformation(BAD_REQUEST, response);
                message = "invalid data";
            } catch (UserException e) {
                putAnswerInformation(BAD_REQUEST, response);
                message = "registration error";
            }
        }
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("message", message == null ? "" : message);
        response.getWriter().println(PageGenerator.instance().getPage("register_template.html", pageVariables));
    }
}
