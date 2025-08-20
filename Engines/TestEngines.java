package Project;

public class TestEngines {
    public static void main(String[] args) {
        String plain = "PASSWORD";

        EncryptionEngine aes = new AESEngine();
        String encAes = aes.encrypt(plain);
        System.out.println("AES Enc: " + encAes);
        System.out.println("AES Dec: " + aes.decrypt(encAes));

        EncryptionEngine rsa = new RSAEngine();
        String encRsa = rsa.encrypt(plain);
        System.out.println("RSA Enc: " + encRsa);
        System.out.println("RSA Dec: " + rsa.decrypt(encRsa));

        EncryptionEngine playfair = new PlayfairEngine();
        String encPf = playfair.encrypt(plain);
        System.out.println("Playfair Enc: " + encPf);
        System.out.println("Playfair Dec: " + playfair.decrypt(encPf));
    }
}