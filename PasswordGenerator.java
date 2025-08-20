package Project;

import java.security.SecureRandom;

public class PasswordGenerator {
    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*()-_=+";
    private int passwordLength;

    PasswordGenerator(int passwordLength){
        this.passwordLength = passwordLength;
    }
    
    public String generateStrongPassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < passwordLength; i++) {
            int index = random.nextInt(CHAR_POOL.length()); //random.nextInt() -> between 0 to length of CHAR_POOL
            password.append(CHAR_POOL.charAt(index));
        }

        return password.toString();
    }
}
