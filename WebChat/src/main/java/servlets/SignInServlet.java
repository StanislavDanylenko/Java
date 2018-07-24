package servlets;

import base.AccountManager;
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
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class SignInServlet extends HttpServlet implements Frontend {
    static private final Logger LOGGER = LogManager.getLogger(SignInServlet.class.getName());
    public static final String PAGE_URL = "/signin";
    private final DBService dbService;
    private final AccountManager accountManagerService;

    public SignInServlet(WebContext webContext) {
        this.dbService = webContext.get(DBService.class);
        this.accountManagerService = webContext.get(AccountManager.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = request.getParameter("login");
        if (!session.isNew() && accountManagerService.getHttpSessoinList().contains(session) && accountManagerService.getSocketSession(login) == null) {
            request.getRequestDispatcher("/chat").forward(request, response);
        } else {
            LOGGER.error("No such session registered!");
            response.getWriter().println(PageGenerator.printPage("static_html" + File.separator + "login.html"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        String message = null;

        LOGGER.info("Try to log in name: {}", login);

        if (!isEnterValid(login, pass)) {
            putAnswerInformation(UNAUTHORIZED, response);
            message = "Unauthorized";
        } else {

            try {
                if (loginUser(dbService, login, pass, accountManagerService, request) != null) {
                    putAnswerInformation(OK, response);
                    LOGGER.info("User: {} authorized!", login);
                    request.getSession().setAttribute("login", login);
                    request.getRequestDispatcher("/chat").forward(request, response);
                    return;
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
