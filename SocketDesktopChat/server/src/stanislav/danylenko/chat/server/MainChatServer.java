package stanislav.danylenko.chat.server;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import stanislav.danylenko.chat.server.controller.MainWindowController;

public class MainChatServer extends Application {

    private MainWindowController mainWindowController;
    ChatServer serverThread;

    public static void main(String[] args) {
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
        serverThread = new ChatServer(port);
        serverThread.start();
    }

    public void stopServer() {
        serverThread.interrupt();
        serverThread.stopServer();
        serverThread = null;
        mainWindowController.stopControlsConfiguratrion();
    }

}
