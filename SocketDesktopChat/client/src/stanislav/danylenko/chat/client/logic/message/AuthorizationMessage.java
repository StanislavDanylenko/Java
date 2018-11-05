package stanislav.danylenko.chat.client.logic.message;

import stanislav.danylenko.chat.client.logic.UserDataSet;

import java.security.PublicKey;

public class AuthorizationMessage extends AbstractMessage {
    private UserDataSet user;
    private byte[] key;

    public AuthorizationMessage(String login, String password) {
        user = new UserDataSet(login, password, null);
        super.operation = "authorization";
    }

    public UserDataSet getUser() {
        return user;
    }

    public void setUser(UserDataSet user) {
        this.user = user;
    }

    public void setPublicKey(PublicKey pk) {
        key = pk.getEncoded();
    }

    public byte[] getPublicKey() {
        return key;
    }

}
