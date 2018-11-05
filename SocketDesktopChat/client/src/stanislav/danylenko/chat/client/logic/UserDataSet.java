package stanislav.danylenko.chat.client.logic;

import stanislav.danylenko.encripting.BaseEntity;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Objects;

public class UserDataSet implements BaseEntity {

    private long id;
    private String login;
    private String password;
    private String email;

    private transient PrivateKey privateKey;
    private transient PublicKey publicKey;
    private transient PublicKey receivedPublicKey;
    private byte[] secretKey;

    public UserDataSet(long id, String login, String password, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public UserDataSet(String login, String password, String email) {
        this.id = -1;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public UserDataSet() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void copyUser(UserDataSet user) {
        id = user.getId();
        password = user.getPassword();
        login = user.getLogin();
        email = user.getEmail();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDataSet that = (UserDataSet) o;
        return id == that.id &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email);
    }

    @Override
    public String toString() { // потом испаравить
        return "UserDataSet{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    @Override
    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    public PublicKey getPublicKey() {
        return publicKey;
    }

    @Override
    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    public PublicKey getReceivedPublicKey() {
        return receivedPublicKey;
    }

    @Override
    public void setReceivedPublicKey(PublicKey publicKey) {
        receivedPublicKey = publicKey;
    }

    @Override
    public byte[] getSecretKey() {
        return secretKey;
    }

    @Override
    public void setSecretKey(byte[] bytes) {
        secretKey = bytes;
    }
}
