package stanislav.danylenko.chat.client.logic.message;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ErrorHalper {
    private static Map<Integer, String> errorMessages;

    public static void loadDictionary(ResourceBundle resourceBundle) {
        Map<Integer, String> newErrorMessages = new HashMap<>();
        newErrorMessages.put(200, "OK");
        newErrorMessages.put(201, resourceBundle.getString("register.errorMessage.successRegister"));
        newErrorMessages.put(202, resourceBundle.getString("start.successAuthorization"));
        newErrorMessages.put(203, resourceBundle.getString("chat.messageSent"));
        newErrorMessages.put(204, resourceBundle.getString("start.errorMessage.connectionReset"));
        newErrorMessages.put(400, resourceBundle.getString("start.errorMessage.cannotConnect"));
        newErrorMessages.put(401, resourceBundle.getString("register.errorMessage.secondUser"));
        newErrorMessages.put(402, resourceBundle.getString("start.errorMessage.wrongData"));
        newErrorMessages.put(403, resourceBundle.getString("register.errorMessage.wrongRegistration"));
        newErrorMessages.put(404, resourceBundle.getString("register.errorMessage.wrongData"));
        newErrorMessages.put(405, resourceBundle.getString("message.serverError"));
        newErrorMessages.put(406, resourceBundle.getString("start.errorMessage.wrongData"));
        newErrorMessages.put(407, resourceBundle.getString("register.errorMessage.wrongData"));
        newErrorMessages.put(408, resourceBundle.getString("register.errorMessage.wrongData"));
        newErrorMessages.put(409, resourceBundle.getString("message.connectionReady"));
        newErrorMessages.put(410, resourceBundle.getString("message.clientDisconnected"));
        newErrorMessages.put(411, resourceBundle.getString("message.connectionException"));
        newErrorMessages.put(412, resourceBundle.getString("message.connectionLost"));
        newErrorMessages.put(500, resourceBundle.getString("message.serverError"));
        errorMessages = newErrorMessages;
    }

    public static String getMessage(int errorCode) {
        return errorMessages.get(errorCode);
    }
}
