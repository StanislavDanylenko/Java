package servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChatServlet extends HttpServlet {
    static private final Logger LOGGER = LogManager.getLogger(SignUpServlet.class.getName());
    public static final String PAGE_URL = "/chat";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        String name = "\"" + req.getSession().getAttribute("login").toString() + "\"";
        pageVariables.put("name",  name == null ? "anonymous" : name);
        resp.getWriter().println(PageGenerator.instance().getPage("chat.html", pageVariables));
    }
}
