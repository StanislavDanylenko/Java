package servlets;

import base.AccountManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

public class ChatServlet extends HttpServlet {
    static private final Logger LOGGER = LogManager.getLogger(SignUpServlet.class.getName());
    public static final String PAGE_URL = "/chat";

    public ChatServlet(AccountManager accountManagerService) {
        this.accountManagerService = accountManagerService;
    }

    private final AccountManager accountManagerService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (!session.isNew() && accountManagerService.getHttpSessoinList().contains(session)) {
            doPost(req, resp);
        } else {
            LOGGER.error("No such session registered!");
            resp.getWriter().println(PageGenerator.printPage("static_html" + File.separator + "login.html"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        String name = req.getSession().getAttribute("login").toString();
        String nameToJS = "\"" + name + "\"";
        LOGGER.info("User {} added to the chat", nameToJS);
        pageVariables.put("name", name == null ? "anonymous" : nameToJS);
        resp.getWriter().println(PageGenerator.instance().getPage("chat.html", pageVariables));
    }
}
