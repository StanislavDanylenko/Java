package stanislav.danylenko.chat.client.logic;

import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.stage.Modality;
import stanislav.danylenko.chat.client.controller.ChatController;
import stanislav.danylenko.chat.client.controller.SettingsController;
import stanislav.danylenko.chat.client.controller.StartController;
import stanislav.danylenko.chat.client.controller.RegistrationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import stanislav.danylenko.chat.client.logic.config.Configuration;
import stanislav.danylenko.chat.client.logic.config.ConfigurationHelper;
import stanislav.danylenko.chat.client.logic.message.*;
import stanislav.danylenko.network.TCPConnection;
import stanislav.danylenko.network.TCPConnectionListener;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application implements TCPConnectionListener {

    private UserDataSet loggedUser;
    private final String VIEW_PATH = "../view/";
    private Configuration configuration;
    private boolean needRestoreServer = true;

    private Stage mainStage;

    private Scene startWindow;
    private Scene registrationWindow;
    private Scene chatWindow;
    private Scene settingsWindow;

    private AnchorPane startWindowParent;
    private AnchorPane registrationWindowParent;
    private AnchorPane chatWindowParent;
    private AnchorPane settingsWindowParent;

    private StartController startController;
    private RegistrationController registrationController;
    private ChatController chatController;
    private SettingsController settingsController;

    private static final String IP_ADR = "127.0.0.1";
    private static final int PORT = 8189;

    private TCPConnection connection;
    private Gson gson = new Gson();
    private ResourceBundle resourceBundle;



    public UserDataSet getLoggedUser() {
        return loggedUser;
    }

    public Gson getGson() {
        return gson;
    }

    public void setLoggedUser(UserDataSet loggedUser) {
        this.loggedUser = loggedUser;
    }

    public TCPConnection getConnection() {
        return connection;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public boolean isNeedRestoreServer() {
        return needRestoreServer;
    }

    public void setNeedRestoreServer(boolean needRestoreServer) {
        this.needRestoreServer = needRestoreServer;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainStage = primaryStage;
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("stanislav/danylenko/chat/client/view/icons/mainIcon.png"));

        configuration = ConfigurationHelper.getConfiguration();
        loadLanguageResources(configuration.getLanguage().getLocale());

        loadResources();

        gotoStart();
        primaryStage.show();

        connectToServer(this, configuration.getFullIP(), configuration.getPort());
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void gotoRegistration() {
        try {
            mainStage.setScene(registrationWindow);
            registrationController.setApp(this);
            customScene(RegistrationController.TITLE, false);

        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoChat() {
        try {
            mainStage.setScene(chatWindow);
            chatController.setApp(this);
            customScene(ChatController.TITLE, true);
            chatController.getListViewMessages().getItems().clear();
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoStart() {
        try {
            mainStage.setScene(startWindow);
            startController.setApp(this);
            customScene(StartController.TITLE, false);

        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoSettings() {
        try {
            settingsController.setApp(this);
            Stage stage = new Stage();
            stage.setScene(settingsWindow);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(startWindow.getWindow());
            customScene(stage, SettingsController.TITLE, false);
            stage.show();
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadResource(String fxml) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(VIEW_PATH + fxml));
        loader.setResources(resourceBundle);
        try {
            switch (fxml) {
                case "startWindow.fxml":
                    startWindowParent = loader.load();
                    startController = loader.getController();
                    startWindow = new Scene(startWindowParent, StartController.MINIMUM_WINDOW_WIDTH, StartController.MINIMUM_WINDOW_HEIGHT);
                    break;
                case "registrationWindow.fxml":
                    registrationWindowParent = loader.load();
                    registrationController = loader.getController();
                    registrationWindow = new Scene(registrationWindowParent, RegistrationController.MINIMUM_WINDOW_WIDTH, RegistrationController.MINIMUM_WINDOW_HEIGHT);
                    break;
                case "chatWindow.fxml":
                    chatWindowParent = loader.load();
                    chatController = loader.getController();
                    chatWindow = new Scene(chatWindowParent, ChatController.MINIMUM_WINDOW_WIDTH, ChatController.MINIMUM_WINDOW_HEIGHT);
                    mainStage.setMinHeight(chatWindowParent.minHeight(-1));
                    mainStage.setMinWidth(chatWindowParent.minWidth(-1));
                    break;
                case "settingsWindow.fxml":
                    settingsWindowParent = loader.load();
                    settingsController = loader.getController();
                    settingsWindow = new Scene(settingsWindowParent, SettingsController.MINIMUM_WINDOW_WIDTH, SettingsController.MINIMUM_WINDOW_HEIGHT);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadResources() {
        loadResource("startWindow.fxml");
        loadResource("registrationWindow.fxml");
        loadResource("chatWindow.fxml");
        loadResource("settingsWindow.fxml");
    }

    private void customScene(String title, boolean resizable) {
        Stage stage = (Stage)mainStage.getScene().getWindow();
        stage.setTitle(title);
        stage.setResizable(resizable);
    }

    private void customScene(Stage stage, String title, boolean resizable) {
        stage.setTitle(title);
        stage.setResizable(resizable);
    }

    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        printMessage("Connection ready...");
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) throws IOException {
        System.out.println(value);
        AbstractMessage message = gson.fromJson(value, AbstractMessage.class);
       if (message == null) {
            throw new IOException();
        }
        String operation = message.getOperation();
        switch (operation) {
            case "registration":
                RegistrationMessage rmessage = gson.fromJson(value, RegistrationMessage.class);
                Platform.runLater(() -> {
                    try {
                        registrationController.setLabelMessage(rmessage.getMessage());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
                break;
            case "authorization":
                AuthorizationMessage amessage = gson.fromJson(value, AuthorizationMessage.class);
                if (amessage.isSuccess()) {
                    startController.cleanFields();
                    loggedUser = amessage.getUser();
                    Platform.runLater(() -> {
                        try {
                            gotoChat();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });
                } else {
                    Platform.runLater(() -> {
                        try {
                            startController.setLabelMessage(amessage.getMessage());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });
                }
                break;
            case "text":
                TextMessage tmessage = gson.fromJson(value, TextMessage.class);
                Platform.runLater(() -> {
                    try {
                        chatController.addMessage(tmessage.getValue());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
                break;
            case "logout":
                LogoutMessage  lmessage = gson.fromJson(value, LogoutMessage.class);
                if (lmessage.isSuccess()) {
                    loggedUser = null;
                }
                Platform.runLater(() -> {
                    try {
                        gotoStart();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
        }
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        System.out.println("Client disconnected" + tcpConnection);
        Platform.runLater(() -> {
            try {
                startController.setLabelMessage("Потеряно соединение с сервером!");
                connection = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (!isNeedRestoreServer()) {
                setNeedRestoreServer(true);
                return;
            }
            restoreConnection(); // изменить в случае специального отсоединения
        });
    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {
        printMessage("Connection exception: " + e);
    }

    private synchronized void printMessage(String msg) {
        if (loggedUser != null) {
            chatController.addMessage(msg);
        } else {
            printSystemInfo(msg);
        }
    }

    public void printSystemInfo(String msg) {
        System.out.println(msg);
    }

    public void restoreConnection() {
        Thread restoreThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (connection == null) {
                    try {
                        Thread.sleep(1000);
                        connection = new TCPConnection(Main.this, IP_ADR, PORT);
                        Thread.sleep(1000);
                        Platform.runLater(() -> {
                            try {
                                startController.setLabelMessage("Соединение восстановлено!");
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        });
                        return;
                    } catch (InterruptedException | IOException e) {
                        //e.printStackTrace();
                        System.out.println("Сервер недоступен!");
                    }
                }
            }
        });
        restoreThread.start();
    }

    private void connectToServer(TCPConnectionListener listener, String ip, int port) {
        try {
            connection = new TCPConnection(listener, ip, port);
        } catch (IOException e) {
            startController.setLabelMessage("Невозможно подключиться к серверу!");
        }
    }

    public void reconnectToServer(String ip, int port) {
        if (connection != null) {
            connection.disconnect();
        }
        try {
            connection = new TCPConnection(this, ip, port);
        } catch (IOException e) {
            startController.setLabelMessage("Невозможно подключиться к серверу!");
        }
    }

    private void loadLanguageResources(String language) {
        Locale.setDefault(new Locale(language));
        resourceBundle = ResourceBundle.getBundle("stanislav/danylenko/chat/client/logic/config/bundles/Locale");
    }

    public void reloadApplicationView(String language) {
        loadLanguageResources(language);
        loadResources();
        gotoStart();
    }

}
