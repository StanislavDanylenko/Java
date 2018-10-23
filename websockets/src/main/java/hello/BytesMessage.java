package hello;

import java.util.List;

public class BytesMessage {

    private int[] message;

    public BytesMessage() {
    }

    public BytesMessage(int[] message) {
        this.message = message;
    }

    public int[] getMessage() {
        return message;
    }

    public void setMessage(int[] message) {
        this.message = message;
    }

}
