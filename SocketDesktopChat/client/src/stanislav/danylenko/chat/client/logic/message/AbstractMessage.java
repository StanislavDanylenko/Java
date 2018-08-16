package stanislav.danylenko.chat.client.logic.message;

public class AbstractMessage {
    protected String operation = "operation";
    private boolean isSuccess;
    private int messageCode;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public int getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(int messageCode) {
        this.messageCode = messageCode;
    }

    public String getOperation() {
        return operation;
    }
}
