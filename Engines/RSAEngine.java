package Project;

import java.security.*;
import javax.crypto.Cipher;
import java.util.Base64;

public class RSAEngine extends EncryptionEngine {
    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    public RSAEngine() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            KeyPair pair = keyGen.generateKeyPair();
            this.publicKey = pair.getPublic();
            this.privateKey = pair.getPrivate();
        } catch (Exception e) {
            throw new RuntimeException("Error generating RSA keys", e);
        }
    }

    @Override
    public String encrypt(String data) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] enc = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(enc);
        } catch (Exception e) {
            throw new RuntimeException("RSA Encryption failed", e);
        }
    }

    @Override
    public String decrypt(String data) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] dec = cipher.doFinal(Base64.getDecoder().decode(data));
            return new String(dec);
        } catch (Exception e) {
            throw new RuntimeException("RSA Decryption failed", e);
        }
    }
}