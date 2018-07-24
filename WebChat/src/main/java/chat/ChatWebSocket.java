package chat;

import sessions.AccountManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebSocket
public class ChatWebSocket {
    private AccountManager accountManager;
    private Session session;
    static private final Logger LOGGER = LogManager.getLogger(ChatWebSocket.class.getName());
    private int count = 0;
    private String login;
    private String timeStamp;

    public ChatWebSocket(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        this.session = session;
        LOGGER.info("Connected: {}", session);
    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        if (count++ == 0) {
            login = data;
            accountManager.addSocketSession(login, this);
            sendMessage(login + " joined to the chat!");
        } else {
            sendMessage(data);
        }
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        accountManager.addSocketSession(login, null);
        sendMessage(login + " left the chat!");
        LOGGER.info("Disconnected: {}, {}, reason - {}, statusCode - {}", session, login, reason, statusCode);
    }

    public void sendString(String data) {
        try {
            timeStamp = new SimpleDateFormat("dd.MM.yyyy HH:mm | ").format(Calendar.getInstance().getTime());
            session.getRemote().sendString(timeStamp + data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void sendMessage(String data) {
        for (ChatWebSocket chat : accountManager.getWebSocketServletList()) {
            chat.sendString(data);
        }
    }
}
