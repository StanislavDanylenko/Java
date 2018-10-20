package hello;

public class BytesMessage {

    private byte[] des_message;

    public BytesMessage() {
    }

    public BytesMessage(byte[] des_message) {
        this.des_message = des_message;
    }

    public byte[] getMessage() {
        return des_message;
    }

    public void setMessage(byte[] des_message) {
        this.des_message = des_message;
    }

}
