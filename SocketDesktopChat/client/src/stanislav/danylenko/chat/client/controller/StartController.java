package stanislav.danylenko.chat.client.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import stanislav.danylenko.chat.client.logic.Main;
import stanislav.danylenko.chat.client.logic.message.AbstractMessage;
import stanislav.danylenko.chat.client.logic.message.AuthorizationMessage;
import stanislav.danylenko.network.TCPConnection;

import java.net.URL;
import java.util.ResourceBundle;

public class StartController implements Initializable {

    public final static String TITLE = "Вход";
    public final static double MINIMUM_WINDOW_WIDTH = 337d;
    public final static double MINIMUM_WINDOW_HEIGHT = 347d;

    @FXML
    private Hyperlink linkRegistration;

    @FXML
    private Label labelMessage;

    @FXML
    private JFXPasswordField textFieldPassword;

    @FXML
    private JFXTextField textFieldLogin;

    @FXML
    private JFXButton btnEnter;

    private Main application;

    @FXML
    void gotoRegistration(MouseEvent event) {
        cleanFields();
        Platform.runLater(() -> {
            try {
                application.gotoRegistration();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    @FXML
    void gotoChat(MouseEvent event) {
        TCPConnection connection = application.getConnection();
        if (connection != null) {
            labelMessage.setText("");
            String login = textFieldLogin.getText().trim();
            String password = textFieldPassword.getText().trim();
            if ("".equals(login) || "".equals(password)) {
                labelMessage.setText("Ошибка, поля заполнены не правильно!");
            } else {
                AbstractMessage message = new AuthorizationMessage(login, password);
                String gsonMessage = application.getGson().toJson(message);
                connection.sendString(gsonMessage);
            }
        }
    }


    public void setApp(Main application){
        this.application = application;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void cleanFields(){
        textFieldLogin.setText("");
        textFieldPassword.setText("");
        labelMessage.setText("");
    }

    public void setLabelMessage(String message) {
        labelMessage.setText(message);
    }
}
