package stanislav.danylenko.chat.server;

import com.google.gson.Gson;
import stanislav.danylenko.chat.server.logic.dbService.DBException;
import stanislav.danylenko.chat.server.logic.dbService.DBService;
import stanislav.danylenko.chat.server.logic.dbService.UserDataSet;
import stanislav.danylenko.chat.server.logic.message.*;
import stanislav.danylenko.encripting.EncryptionHandler;
import stanislav.danylenko.network.TCPConnection;
import stanislav.danylenko.network.TCPConnectionListener;

import java.io.IOException;
import java.net.ServerSocket;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.*;

public class ChatServer extends Thread implements TCPConnectionListener {

    private Gson gson = new Gson();
    private String timeStamp;
    private ServerSocket serverSocket;
    private final Map<TCPConnection, UserDataSet> connections = new HashMap<>();
    private final int port;
    private EncryptionHandler encodingHelper = new EncryptionHandler();

    public ChatServer(int port) {
        this.port = port;
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
                    rmessage = setResultAndMessage(rmessage, true, MessageCode.REGISTERED);
                } catch (DBException e) {
                    setResultAndMessage(rmessage, false, Integer.parseInt(e.getMessage()));
                    e.printStackTrace();
                }
                tcpConnection.sendString(gson.toJson(rmessage));
                break;
            case "authorization":
                AuthorizationMessage amessage = gson.fromJson(value, AuthorizationMessage.class);

                UserDataSet user = DBService.getUserByLogin(amessage.getUser().getLogin());
                if (user != null && user.getPassword().equals(amessage.getUser().getPassword())) {

                    PublicKey publicKey = encodingHelper.getPublicKeyFromBytes(amessage.getPublicKey());
                    user.setReceivedPublicKey(publicKey);
                    encodingHelper.generateKeys(user);
                    encodingHelper.generateCommonSecretKey(user);

                    amessage.setUser(user);
                    amessage.setPublicKey(user.getPublicKey());

                    amessage = setResultAndMessage(amessage, true, MessageCode.AUTHORIZIED);
                    connections.put(tcpConnection, user);
                    tcpConnection.sendString(gson.toJson(amessage));
                    timeStamp = new SimpleDateFormat("[dd.MM.yyyy HH:mm] ").format(Calendar.getInstance().getTime());
                    sendAllConnections(new TextMessage((timeStamp + user.getLogin() + " connected!").getBytes()));
                } else {
                    amessage = setResultAndMessage(amessage, false, MessageCode.AUTHORIZATION_ERROR);
                    tcpConnection.sendString(gson.toJson(amessage));
                }
                break;
            case "text":
                TextMessage tmessage = gson.fromJson(value, TextMessage.class);
                byte[] text = tmessage.getValue();
                byte[] decryptedText = encodingHelper.decryptMessage(text, connections.get(tcpConnection));
//                byte[] encryptedText = encodingHelper.encryptMessage(new String(decryptedText), connections.get(tcpConnection));
                sendAllConnections(new TextMessage(decryptedText));
                break;
            case "logout":
                LogoutMessage lmessage = gson.fromJson(value, LogoutMessage.class);
                connections.remove(tcpConnection);
                lmessage.setSuccess(true);
                tcpConnection.sendString(gson.toJson(lmessage));
                timeStamp = new SimpleDateFormat("[dd.MM.yyyy HH:mm] ").format(Calendar.getInstance().getTime());
                sendAllConnections(new TextMessage((timeStamp + lmessage.getUser().getLogin() + " disconnected!").getBytes()));
                break;
        }
    }

    @Override
    public synchronized void onDisconnect(TCPConnection tcpConnection) {
        connections.remove(tcpConnection);
        System.out.println("Client disconnected: " + tcpConnection);
    }

    @Override
    public synchronized void onException(TCPConnection tcpConnection, Exception e) {
        System.out.println("TCPConnection exception: " + e);
    }

    private synchronized void sendAllConnections(TextMessage message) {
        System.out.println("DECRYPTED:");
        System.out.println(Arrays.toString(message.getValue()));
        System.out.println(new String(message.getValue()));

        Set<TCPConnection> users = connections.keySet();
        String str = new String(message.getValue());
        for (TCPConnection connection : users) {

            System.out.println("ENCRYPTED:");
            byte[] text = encodingHelper.encryptMessage(str, connections.get(connection));
            System.out.println(Arrays.toString(text));
            System.out.println(new String(text));
            message.setValue(text);
            connection.sendString(gson.toJson(message));
        }
    }

    private <T extends AbstractMessage> T setResultAndMessage(T message, boolean isSuccess, int value) {
        message.setSuccess(isSuccess);
        message.setMessageCode(value);
        return message;
    }

    @Override
    public void run() {
        System.out.println("Server running...");
        try {
            serverSocket = new ServerSocket(port);
            while (!serverSocket.isClosed()) {
                try {
                    new TCPConnection(this, serverSocket.accept());
                } catch (IOException e) {
                    System.out.println("TCPConnection exception: " + e);
                    System.out.println("Server stopped");
                    return;
                }
            }
        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println(e);
            return;
        }
    }

    public void stopServer() {
        try {
            serverSocket.close();
            int size = connections.size();
            List<TCPConnection> connectionSet = new ArrayList<>(connections.keySet());
            for (int i = 0; i < size; i++) {
                connectionSet.get(i).disconnect();
                connectionSet.set(i, null);
            }
            connections.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
        serverSocket = null;
    }
}
