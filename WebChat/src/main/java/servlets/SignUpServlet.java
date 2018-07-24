package servlets;

import base.DBService;
import base.Frontend;
import exception.webException.GetUserException;
import exception.webException.SuchUserAlreadyExistException;
import exception.webException.UserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sessions.WebContext;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SignUpServlet extends HttpServlet implements Frontend {
    static private final Logger LOGGER = LogManager.getLogger(SignUpServlet.class.getName());
    public static final String PAGE_URL = "/signup";
    private final DBService dbService;

    public SignUpServlet(WebContext webContext) {
        this.dbService = webContext.get(DBService.class);
    }

        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println(PageGenerator.printPage("static_html" + File.separator + "register.html"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        LOGGER.info("Connected user name: {}", login);

        String message = null;
        Map<String, Object> pageVariables = new HashMap<>();

        if (!isEnterValid(login, pass)) {
            putAnswerInformation(BAD_REQUEST, response);
            message = "invalid data!";
        } else {

            try {
                if (registerNewUser(dbService, login, pass) != null) {
                    putAnswerInformation(CREATED, response);
                    message = "registered";
                    LOGGER.info("User: {} registered!", login);
                    pageVariables.put("message", message);
                    response.getWriter().println(PageGenerator.instance().getPage("register_template_successed.html", pageVariables));
                    return;
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

        LOGGER.info("User: {} fail!", login);
        pageVariables.put("message", message == null ? "" : message);
        response.getWriter().println(PageGenerator.instance().getPage("register_template_failed.html", pageVariables));
    }
}
