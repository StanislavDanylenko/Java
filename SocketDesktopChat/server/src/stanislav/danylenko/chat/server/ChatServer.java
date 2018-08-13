package stanislav.danylenko.chat.server;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import stanislav.danylenko.chat.server.controller.MainWindowController;
import stanislav.danylenko.chat.server.logic.dbService.DBException;
import stanislav.danylenko.chat.server.logic.dbService.DBService;
import stanislav.danylenko.chat.server.logic.dbService.UserDataSet;
import stanislav.danylenko.network.TCPConnection;
import stanislav.danylenko.network.TCPConnectionListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.*;

import stanislav.danylenko.chat.server.logic.message.*;

public class ChatServer extends Application implements TCPConnectionListener {

    Gson gson = new Gson();
    private String timeStamp;
    private static ChatServer instance;
    private MainWindowController mainWindowController;
    private ServerSocket serverSocket;

    private final Map<TCPConnection, String> connections = new HashMap<>();

    private ChatServer(int port) {
        System.out.println("Server running...");
        try {
            serverSocket = new ServerSocket(8189);
            while (true) {
                try {
                    new TCPConnection(this, serverSocket.accept());
                } catch (IOException e) {
                    System.out.println("TCPConnection exception: " + e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public synchronized void onConnectionReady(TCPConnection tcpConnection) {
        connections.put(tcpConnection, null);
        System.out.println("Client connected: " + tcpConnection);
    }

    @Override
    public synchronized void onReceiveString(TCPConnection tcpConnection, String value) {
        AbstractMessage message = gson.fromJson(value, AbstractMessage.class);
        String operation = message.getOperation();
        switch (operation) {
            case "registration":
                RegistrationMessage rmessage = gson.fromJson(value, RegistrationMessage.class);
                try {
                    DBService.addUser(rmessage.getUser());
                    rmessage = setResultAndMessage(rmessage, true, "Success registration!");
                } catch (DBException e) {
                    setResultAndMessage(rmessage, false, e.getMessage());
                    e.printStackTrace();
                }
                tcpConnection.sendString(gson.toJson(rmessage));
                break;
            case "authorization":
                AuthorizationMessage amessage = gson.fromJson(value, AuthorizationMessage.class);
                UserDataSet user = DBService.getUserByLogin(amessage.getUser().getLogin());
                if (user != null && user.getPassword().equals(amessage.getUser().getPassword())) {
                    amessage.setUser(user);
                    amessage = setResultAndMessage(amessage, true, "Success!");
                    connections.put(tcpConnection, user.getLogin());
                    tcpConnection.sendString(gson.toJson(amessage));
                    timeStamp = new SimpleDateFormat("[dd.MM.yyyy HH:mm] ").format(Calendar.getInstance().getTime());
                    sendAllConnections(new TextMessage(timeStamp + user.getLogin() + " connected!"));
                } else {
                    amessage = setResultAndMessage(amessage, false, "Authorization error!");
                    tcpConnection.sendString(gson.toJson(amessage));
                }
                break;
            case "text":
                TextMessage tmessage = gson.fromJson(value, TextMessage.class);
                sendAllConnections(tmessage);
                break;
            case "logout":
                LogoutMessage lmessage = gson.fromJson(value, LogoutMessage.class);
                connections.put(tcpConnection, null);
                lmessage.setSuccess(true);
                tcpConnection.sendString(gson.toJson(lmessage));
                timeStamp = new SimpleDateFormat("[dd.MM.yyyy HH:mm] ").format(Calendar.getInstance().getTime());
                sendAllConnections(new TextMessage(timeStamp + lmessage.getUser().getLogin() + " disconnected!"));
                break;
        }
    }

    @Override
    public synchronized void onDisconnect(TCPConnection tcpConnection) {
        connections.remove(tcpConnection);
        System.out.println("Client connected: " + tcpConnection);
    }

    @Override
    public synchronized void onException(TCPConnection tcpConnection, Exception e) {
        System.out.println("TCPConnection exception: " + e);
    }

    private synchronized void sendAllConnections(TextMessage message) {
        System.out.println(message.getValue());
        Set<TCPConnection> users = connections.keySet();
        for (TCPConnection connection : users) {
            connection.sendString(gson.toJson(message));
        }
    }

    private <T extends AbstractMessage> T setResultAndMessage(T message, boolean isSuccess, String value) {
        message.setSuccess(isSuccess);
        message.setMessage(value);
        return message;
    }


    public static void main(String[] args) {
        /*instance = new ChatServer();*/
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/mainWindow.fxml"));
        Parent root = loader.load();
        mainWindowController = loader.getController();
        primaryStage.setTitle(MainWindowController.TITLE);
        primaryStage.setScene(new Scene(root,
                MainWindowController.MINIMUM_WINDOW_WIDTH,
                MainWindowController.MINIMUM_WINDOW_HEIGHT));
        primaryStage.setResizable(false);
        mainWindowController.setApp(this);
        primaryStage.show();
    }

    public void startServer(int port) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                instance = new ChatServer(port);
            }
        });
        thread.start();
    }

    public ChatServer() {
    }

    public void stopServer() {
        try {
            serverSocket.close();
            serverSocket = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
