package stanislav.danylenko.chat.server.logic.message;

public class TextMessage extends AbstractMessage {
    private byte[] value;

    public TextMessage(byte[] value) {
        this.value = value;
        super.operation = "text";
    }

    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] text) {
        value = text;
    }
}
