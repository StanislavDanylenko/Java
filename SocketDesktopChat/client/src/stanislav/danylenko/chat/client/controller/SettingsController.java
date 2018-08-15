package stanislav.danylenko.chat.client.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import stanislav.danylenko.chat.client.logic.Main;
import stanislav.danylenko.chat.client.logic.config.Configuration;
import stanislav.danylenko.chat.client.logic.config.ConfigurationHelper;
import stanislav.danylenko.chat.client.logic.config.Language;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SettingsController implements Initializable {

    public static String TITLE = "Настройка";
    public final static double MINIMUM_WINDOW_WIDTH = 447d;
    public final static double MINIMUM_WINDOW_HEIGHT = 258d;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnOK;

    @FXML
    private JFXTextField textFieldIP1;

    @FXML
    private JFXTextField textFieldIP2;

    @FXML
    private JFXTextField textFieldIP3;

    @FXML
    private JFXTextField textFieldIP4;

    @FXML
    private JFXTextField textFieldPort;

    @FXML
    private JFXComboBox<Language> cmbBoxLang;

    Main application;
    Configuration configurationNew = new Configuration();
    Configuration configurationOld = new Configuration();
    ResourceBundle resourceBundle;

    public void setApp(Main application){
        this.application = application;
        configurationOld = application.getConfiguration();
        setValuesFromConfiguration(configurationOld);
    }

    @FXML
    void checkIP1(KeyEvent event) {
        if (validateIP(textFieldIP1.getText())) {
            textFieldIP2.setStyle("-fx-background-color: #fff");
            int ip = Integer.parseInt(textFieldIP1.getText());
            if (ip > 99) {
                textFieldIP2.requestFocus();
            }
            configurationNew.setIp1(ip);
        } else {
            textFieldIP2.setStyle("-fx-background-color: #e50000");
        }
    }

    @FXML
    void checkIP2(KeyEvent event) {
        if (validateIP(textFieldIP2.getText())) {
            textFieldIP3.setStyle("-fx-background-color: #fff");
            int ip = Integer.parseInt(textFieldIP2.getText());
            if (ip > 99) {
                textFieldIP3.requestFocus();
            }
            configurationNew.setIp2(ip);
        } else {
            textFieldIP3.setStyle("-fx-background-color: #e50000");
        }
    }

    @FXML
    void checkIP3(KeyEvent event) {
        if (validateIP(textFieldIP3.getText())) {
            textFieldIP3.setStyle("-fx-background-color: #fff");
            int ip = Integer.parseInt(textFieldIP3.getText());
            if (ip > 99) {
                textFieldIP4.requestFocus();
            }
            configurationNew.setIp3(ip);
        } else {
            textFieldIP3.setStyle("-fx-background-color: #e50000");
        }
    }

    @FXML
    void checkIP4(KeyEvent event) {
        if (validateIP(textFieldIP4.getText())) {
            textFieldIP4.setStyle("-fx-background-color: #fff");
            int ip = Integer.parseInt(textFieldIP4.getText());
            if (ip > 99) {
                textFieldPort.requestFocus();
            }
            configurationNew.setIp4(ip);
        } else {
            textFieldIP4.setStyle("-fx-background-color: #e50000");
        }
    }

    @FXML
    void checkPort(KeyEvent event) {
        if (validatePort(textFieldPort.getText())) {
            textFieldPort.setStyle("-fx-background-color: #fff");
            configurationNew.setPort(Integer.parseInt(textFieldPort.getText()));
        } else {
            textFieldPort.setStyle("-fx-background-color: #e50000");
        }
    }

    private boolean validatePort(String portValue) {
        Pattern regexPattern = Pattern.compile("[0-9]{1,5}");
        Matcher regMatcher   = regexPattern.matcher(portValue);
        if(regMatcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validateIP(String portValue) {
        Pattern regexPattern = Pattern.compile("[0-9]{1,3}");
        Matcher regMatcher   = regexPattern.matcher(portValue);
        if(regMatcher.matches()) {
            int value = Integer.parseInt(regMatcher.group());
            if (value > 0 && value < 255) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @FXML
    void showLanguages(MouseEvent event) {
        for (Language lang : Language.values()) {
            if (lang.toString().equals(cmbBoxLang.getValue())) {
                configurationNew.setLanguage(lang);
                return;
            }
        }
        configurationNew.setLanguage(Language.ENGLISH);
    }

    @FXML
    void cancelSettings(ActionEvent event) {
        closeWindow();
    }

    @FXML
    void acceptSettings(ActionEvent event) {
        setValuesToConfiguration(configurationNew);
        if (!configurationNew.getFullIP().equals(configurationOld.getFullIP())
                || configurationNew.getPort() != configurationOld.getPort()) {
            application.setNeedRestoreServer(false);
            application.reconnectToServer(configurationNew.getFullIP(), configurationNew.getPort());
        }
        if (configurationOld.getLanguage() != configurationNew.getLanguage()) {
            application.reloadApplicationView(configurationNew.getLanguage().getLocale());
        }
        application.setConfiguration(configurationNew);
        ConfigurationHelper.setConfiguration(configurationNew);
        closeWindow();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resourceBundle = resources;
        textFieldIP1.requestFocus();
        for (Language lang : Language.values()) {
            cmbBoxLang.getItems().addAll(lang);
        }
        TITLE = resourceBundle.getString("settings.headline");
    }

    private void closeWindow() {
        Stage stage = (Stage) btnOK.getScene().getWindow();
        stage.close();
    }

    private void setValuesFromConfiguration(Configuration config) {
        textFieldIP1.setText("" + config.getIp1());
        textFieldIP2.setText("" + config.getIp2());
        textFieldIP3.setText("" + config.getIp3());
        textFieldIP4.setText("" + config.getIp4());
        textFieldPort.setText("" + config.getPort());
        cmbBoxLang.setValue(configurationOld.getLanguage());
    }

    private void setValuesToConfiguration(Configuration configuration) {
        configuration.setIp1(Integer.parseInt(textFieldIP1.getText()));
        configuration.setIp2(Integer.parseInt(textFieldIP2.getText()));
        configuration.setIp3(Integer.parseInt(textFieldIP3.getText()));
        configuration.setIp4(Integer.parseInt(textFieldIP4.getText()));
        configuration.setPort(Integer.parseInt(textFieldPort.getText()));
        configuration.setLanguage(cmbBoxLang.getValue());
    }
}
