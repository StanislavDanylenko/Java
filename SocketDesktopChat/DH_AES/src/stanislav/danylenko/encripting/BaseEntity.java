package stanislav.danylenko.encripting;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface BaseEntity {

    PrivateKey getPrivateKey();
    void setPrivateKey(PrivateKey key);

    PublicKey getPublicKey();
    void setPublicKey(PublicKey key);

    PublicKey  getReceivedPublicKey();
    void  setReceivedPublicKey(PublicKey key);

    byte[] getSecretKey();
    void setSecretKey(byte[] key);

}
