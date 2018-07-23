package servlets;

import base.Account;
import base.DBService;
import base.Frontend;
import exception.webException.GetUserException;
import exception.webException.ValidationUserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sessions.WebContext;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class SignInServlet extends HttpServlet implements Frontend {
    static private final Logger LOGGER = LogManager.getLogger(SignInServlet.class.getName());
    public static final String PAGE_URL = "/signin";
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
        String message = null;

        LOGGER.info("Name: {}", login);

        if (!isEnterValid(login, pass)) {
            putAnswerInformation(UNAUTHORIZED, response);
            message = "Unauthorized";
        } else {

            try {
                if (loginUser(dbService, login, pass, accountService, request) != null) {
                    putAnswerInformation(OK, response);
                    message = "Authorized: " + login;
                    LOGGER.info("User: {} success!", login);
                    request.getSession().setAttribute("login", login);
                    request.getRequestDispatcher("/chat").forward(request,response);
                }
            } catch (ValidationUserException e) {
                putAnswerInformation(BAD_REQUEST, response);
                message = e.getMessage();
            } catch (GetUserException e) {
                putAnswerInformation(BAD_REQUEST, response);
                message = "invalid data";
            }
        }

        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("message", message == null ? "" : message);
        response.getWriter().println(PageGenerator.instance().getPage("login_template.html", pageVariables));
    }

}
