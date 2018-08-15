package stanislav.danylenko.chat.client.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import stanislav.danylenko.chat.client.logic.Main;
import stanislav.danylenko.network.TCPConnection;
import stanislav.danylenko.chat.client.logic.message.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationController implements Initializable {

    public static String TITLE = "Регистрация";
    public final static double MINIMUM_WINDOW_WIDTH = 337d;
    public final static double MINIMUM_WINDOW_HEIGHT = 435d;

    @FXML
    private Label labelMessage;

    @FXML
    private JFXPasswordField textFieldPAssword;

    @FXML
    private JFXTextField textFieldLogin;

    @FXML
    private JFXButton btnRegistration;

    @FXML
    private JFXPasswordField textFieldSecondPassword;

    @FXML
    private JFXTextField textFieldEmail;

    private Main application;
    ResourceBundle resourceBundle;

    public void setApp(Main application){
        this.application = application;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resourceBundle = resources;
        TITLE = resourceBundle.getString("register.headline");
    }

    @FXML
    void gotoStart(MouseEvent event) {
        cleanFields();
        Platform.runLater(() -> {
            try {
                application.gotoStart();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    @FXML
    public void registerUser(MouseEvent mouseEvent) {
        TCPConnection connection = application.getConnection();
        if (connection != null) {
            String login = textFieldLogin.getText();
            String password = textFieldPAssword.getText();
            String secondPassword = textFieldSecondPassword.getText();
            String email = textFieldEmail.getText();
            labelMessage.setText("");
            if ("".equals(login) || !password.equals(secondPassword)
                    || "".equals(password) || "".equals(email) || !isCorrectEmail(email)) {
                labelMessage.setText("Данные указаны не верно!");
            } else {
                AbstractMessage message = new RegistrationMessage(login, password, email);
                String gsonMessage = application.getGson().toJson(message);
                connection.sendString(gsonMessage);
            }
        }
    }

    @FXML
    void checkTypedText(KeyEvent event) {
        if (!textFieldPAssword.getText().equals(textFieldSecondPassword.getText())) {
            textFieldSecondPassword.setStyle("-fx-background-color: #e50000");
        } else {
            textFieldSecondPassword.setStyle("-fx-background-color: #fff");
        }
    }

    @FXML
    public void setLabelMessage(String message) {
        labelMessage.setText(message);
    }

    @FXML
    void checkSecondPassword(KeyEvent event) {
        checkTypedText(event);
    }

    @FXML
    void checkEmail(KeyEvent event) {
        if (!isCorrectEmail(textFieldEmail.getText())) {
            textFieldEmail.setStyle("-fx-background-color: #e50000");
        } else {
            textFieldEmail.setStyle("-fx-background-color: #fff");
        }
    }

    private boolean isCorrectEmail(String email) {
        Pattern regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        Matcher regMatcher   = regexPattern.matcher(email);
        if(regMatcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    private void cleanFields() {
        labelMessage.setText("");
        textFieldEmail.setText("");
        textFieldLogin.setText("");
        textFieldPAssword.setText("");
        textFieldSecondPassword.setText("");
        textFieldEmail.setStyle("-fx-background-color: #fff");
        textFieldSecondPassword.setStyle("-fx-background-color: #fff");
    }
}
