package servlets;

import sessions.AccountManager;
import chat.ChatWebSocket;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "WebSocketChatServlet", urlPatterns = {"/chatInner"})
public class WebSocketChatServlet extends WebSocketServlet {
    public static final String PAGE_URL = "/chatInner";
    private final static int LOGOUT_TIME = 10 * 60 * 1000;
    private final AccountManager chatService;

    public WebSocketChatServlet(AccountManager chatService) {
        this.chatService = chatService;
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        factory.setCreator((req, resp) -> new ChatWebSocket(chatService));
    }
}
