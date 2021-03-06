package stanislav.danylenko.chat.client.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import stanislav.danylenko.chat.client.logic.Main;
import stanislav.danylenko.chat.client.logic.UserDataSet;
import stanislav.danylenko.chat.client.logic.message.AbstractMessage;
import stanislav.danylenko.chat.client.logic.message.AuthorizationMessage;
import stanislav.danylenko.encripting.EncryptionHandler;
import stanislav.danylenko.network.TCPConnection;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartController implements Initializable {

    public static String TITLE;
    private static final String PATH = "src/stanislav/danylenko/chat/client/view/icons/";
    public final static double MINIMUM_WINDOW_WIDTH = 337d;
    public final static double MINIMUM_WINDOW_HEIGHT = 347d;

    @FXML
    private ImageView btnSettings;

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
    private ResourceBundle resourceBundle;

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
                AuthorizationMessage amedssage = new AuthorizationMessage(login, password);

                UserDataSet userDataSet = new UserDataSet();
                EncryptionHandler encryptionHandler = application.getEncryptionHandler();
                encryptionHandler.generateKeys(userDataSet);
                application.setLoggedUser(userDataSet);

                amedssage.setPublicKey(userDataSet.getPublicKey());

                AbstractMessage message = amedssage;
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
        this.resourceBundle = resources;
        TITLE = resourceBundle.getString("start.headline");
    }

    public void cleanFields(){
        textFieldLogin.setText("");
        textFieldPassword.setText("");
        labelMessage.setText("");
    }

    public void setLabelMessage(String message) {
        labelMessage.setText(message);
    }

    @FXML
    void changeImageFrom(MouseEvent event) {
        btnSettings.setImage(loadImage(PATH + "buttonIcon.png"));
    }

    @FXML
    void changeImageTo(MouseEvent event) {
        btnSettings.setImage(loadImage(PATH + "buttonActiveIcon.png"));
    }

    @FXML
    void gotoSettings(MouseEvent event) {
        Platform.runLater(() -> {
            try {
                application.gotoSettings();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private Image loadImage(String path) {
        try {
            return new Image(new File(path).toURI().toURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
