package stanislav.danylenko.encripting;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;


public class EncryptionHandler {

    public void generateKeys(BaseEntity entity) {

        try {
            final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DH");
            keyPairGenerator.initialize(1024);

            final KeyPair keyPair = keyPairGenerator.generateKeyPair();

            entity.setPrivateKey(keyPair.getPrivate());
            entity.setPublicKey(keyPair.getPublic());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateCommonSecretKey(BaseEntity entity) {

        try {
            final KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
            keyAgreement.init(entity.getPrivateKey());
            keyAgreement.doPhase(entity.getReceivedPublicKey(), true);

            entity.setSecretKey(shortenSecretKey(keyAgreement.generateSecret()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public PublicKey getPublicKeyFromBytes(byte[] key) {
        PublicKey publicKey = null;
        try {
            KeyFactory kf = KeyFactory.getInstance("DH");
            publicKey = kf.generatePublic(new X509EncodedKeySpec(key));
            int a = 6;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    public byte[] encryptMessage(final String message, final BaseEntity entity) {

        byte[] encryptedMessage = null;

        try {

            // You can use Blowfish or another symmetric algorithm but you must adjust the key size.
            final SecretKeySpec keySpec = new SecretKeySpec(entity.getSecretKey(), "DES");
            final Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            encryptedMessage = cipher.doFinal(message.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedMessage;
    }

    public byte[] decryptMessage(final byte[] message, final BaseEntity entity) {

        byte[] result = null;

        try {

            // You can use Blowfish or another symmetric algorithm but you must adjust the key size.
            final SecretKeySpec keySpec = new SecretKeySpec(entity.getSecretKey(), "DES");
            final Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

            cipher.init(Cipher.DECRYPT_MODE, keySpec);

            result = cipher.doFinal(message);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private byte[] shortenSecretKey(final byte[] longKey) {

        try {

            // Use 8 bytes (64 bits) for DES, 6 bytes (48 bits) for Blowfish
            /*final byte[] shortenedKey = new byte[8];

            System.arraycopy(longKey, 0, shortenedKey, 0, shortenedKey.length);

            return shortenedKey;*/

            // Below lines can be more secure
            final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            final DESKeySpec desSpec = new DESKeySpec(longKey);

            return keyFactory.generateSecret(desSpec).getEncoded();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
