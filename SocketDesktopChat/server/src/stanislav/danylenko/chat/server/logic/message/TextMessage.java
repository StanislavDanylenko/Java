package stanislav.danylenko.chat.server.logic.message;

public class TextMessage extends AbstractMessage {
    private String value;

    public TextMessage(String value) {
        this.value = value;
        super.operation = "text";
    }

    public String getValue() {
        return value;
    }
}