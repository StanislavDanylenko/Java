package stanislav.danylenko.chat.server.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import stanislav.danylenko.chat.server.ChatServer;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainWindowController implements Initializable {

    public final static String TITLE = "YourChat:Server";
    public final static double MINIMUM_WINDOW_WIDTH = 219d;
    public final static double MINIMUM_WINDOW_HEIGHT = 202d;

    @FXML
    private JFXButton btnStart;

    @FXML
    private JFXButton btnStop;

    @FXML
    private JFXTextField textFieldPort;

    @FXML
    private Label labelState;

    private ChatServer application;
    private Integer port;

    public void setApp(ChatServer application){
        this.application = application;
    }

    @FXML
    void StopServer(ActionEvent event) {
        application.stopServer();
        textFieldPort.setDisable(false);
        btnStart.setDisable(false);
        btnStop.setDisable(true);
    }

    @FXML
    void checkPortField(KeyEvent event) {
        if (!validatePort(textFieldPort.getText())) {
            btnStart.setDisable(true);
        } else {
            btnStart.setDisable(false);
        }
    }

    private boolean validatePort(String portValue) {
        Pattern regexPattern = Pattern.compile("[0-9]{1,5}");
        Matcher regMatcher   = regexPattern.matcher(portValue);
        if(regMatcher.matches()) {
            port = Integer.parseInt(portValue);
            return true;
        } else {
            return false;
        }
    }

    @FXML
    void startServer(ActionEvent event) {
        if (port != null) {
           application.startServer(port);
        }
        textFieldPort.setDisable(true);
        btnStart.setDisable(true);
        btnStop.setDisable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textFieldPort.requestFocus();
        btnStart.setDisable(true);
        btnStop.setDisable(true);
    }
}
