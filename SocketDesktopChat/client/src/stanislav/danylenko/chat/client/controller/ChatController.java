package stanislav.danylenko.chat.client.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.util.Callback;
import stanislav.danylenko.chat.client.logic.Main;
import stanislav.danylenko.chat.client.logic.UserDataSet;
import stanislav.danylenko.chat.client.logic.message.AbstractMessage;
import stanislav.danylenko.chat.client.logic.message.AuthorizationMessage;
import stanislav.danylenko.chat.client.logic.message.LogoutMessage;
import stanislav.danylenko.chat.client.logic.message.TextMessage;
import stanislav.danylenko.network.TCPConnection;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ChatController implements Initializable {

    public final static String TITLE = "Чат";
    public final static double MINIMUM_WINDOW_WIDTH = 500d;
    public final static double MINIMUM_WINDOW_HEIGHT = 500d;

    private ObservableList<String> messages = FXCollections.observableArrayList(new LinkedList<>());
    private String timeStamp;

    @FXML
    private ListView<String> listViewMessages;

    @FXML
    private JFXButton btnSend;

    @FXML
    private JFXTextField textFieldMessage;

    @FXML
    private Label labelMessage;

    @FXML
    private JFXButton btnProfile;

    @FXML
    private JFXButton btnExit;

    private Main application;

    public void addMessage(String message) {
        messages.add(message);
    }

    public void setApp(Main application){
        this.application = application;
    }

    public ListView<String> getListViewMessages() {
        return listViewMessages;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messages.addListener((ListChangeListener<String>) c -> listViewMessages.setItems(messages));
    }

    @FXML
    void gotoLogin(ActionEvent event) {
        UserDataSet user = application.getLoggedUser();
        AbstractMessage aMessage = new LogoutMessage(user.getLogin(), user.getPassword());
        String gsonMessage = application.getGson().toJson(aMessage);
        TCPConnection connection = application.getConnection();
        if (connection != null) {
            connection.sendString(gsonMessage);
        }
    }

    @FXML
    void sendMessage(ActionEvent event) {
        TCPConnection connection = application.getConnection();
        if (connection != null) {
            String message = textFieldMessage.getText();
            if (!"".equals(message)) {
                timeStamp = new SimpleDateFormat("[dd.MM.yyyy HH:mm] ").format(Calendar.getInstance().getTime());
                message = timeStamp + application.getLoggedUser().getLogin() + ": " + message;
                AbstractMessage aMessage = new TextMessage(message);
                String gsonMessage = application.getGson().toJson(aMessage);
                connection.sendString(gsonMessage);
                textFieldMessage.setText("");
            }
        }
    }
}
