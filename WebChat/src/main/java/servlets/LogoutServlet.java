package servlets;

import base.AccountManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    static private final Logger LOGGER = LogManager.getLogger(SignInServlet.class.getName());
    public static final String PAGE_URL = "/logout";
    private final AccountManager accountManagerService;

    public LogoutServlet(AccountManager accountManagerService) {
        this.accountManagerService = accountManagerService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(PageGenerator.printPage("static_html" + File.separator + "login.html"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        if (login != null) {
            login = login.replaceAll("\"", "");
            accountManagerService.deleteUser(login);
        }
        resp.getWriter().println(PageGenerator.printPage("static_html" + File.separator + "login.html"));
    }
}
