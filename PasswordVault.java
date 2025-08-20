package Project;
import java.util.ArrayList;

public class PasswordVault {
    private String vaultId;
    private ArrayList<PasswordEntry> entries;
    private EncryptionEngine encryptionEngine;
    private PasswordGenerator passwordGenerator;

    public PasswordVault(String vaultId, EncryptionEngine encryptionEngine) {
        this.vaultId = vaultId;
        this.entries = new ArrayList<>();
        this.encryptionEngine = encryptionEngine;
        this.passwordGenerator = new PasswordGenerator(12);
    }

    public String getVaultId() {
        return vaultId;
    }

    public void addEntry(PasswordEntry entry) {
        String encryptedPassword = encryptionEngine.encrypt(entry.getPassword());
        entry.setPassword(encryptedPassword);
        entries.add(entry);
        System.out.println("Entry added: " + entry.getSite());
    }

    public void removeEntry(String id) {
        entries.removeIf(e -> e.getId().equals(id));
        System.out.println("Entry with id " + id + " removed.");
    }

    private PasswordEntry retrieveEntry(String id, boolean showEncryptedPassword) {
        for (PasswordEntry entry : entries) {
            if (entry.getId().equals(id)) {
                String password = showEncryptedPassword ? entry.getPassword() : encryptionEngine.decrypt(entry.getPassword());
                // entry.setPassword(password);
                return new PasswordEntry(entry.getId(), entry.getSite(), password);
            }
        }
        return null;
    }

    public String generateNewPassword() {
        return passwordGenerator.generateStrongPassword();
    }

    // public void viewEntries() {
    //     if (entries.isEmpty()) {
    //         System.out.println("No password entries found in your vault.");
    //         return;
    //     }
    
    //     System.out.println("\n--- Stored Passwords ---");
    
    //     for (PasswordEntry entry : entries) {
    //         PasswordEntry decryptedEntry = retrieveEntry(entry.getId());
    //         if (decryptedEntry != null) {
    //             System.out.println("ID: " + decryptedEntry.getId());
    //             System.out.println("URL: " + decryptedEntry.getSite());
    //             System.out.println("Password: " + decryptedEntry.getPassword());
    //             System.out.println("------------------------");
    //         }
    //     }
    // }

    public void viewEntries() {
        if (entries.isEmpty()) {
            System.out.println("No password entries found in your vault.");
            return;
        }
    
        System.out.println("\n--- Stored Passwords ---");
    
        for (PasswordEntry entry : entries) {
            PasswordEntry decryptedEntry = retrieveEntry(entry.getId(), false);
            PasswordEntry encryptedEntry = retrieveEntry(entry.getId(), true);
            if (decryptedEntry != null) {
                System.out.println("ID: " + decryptedEntry.getId());
                System.out.println("URL: " + decryptedEntry.getSite());
                System.out.println("Password: " + decryptedEntry.getPassword());
                System.out.println("Encrypted Password: " + encryptedEntry.getPassword());
                System.out.println("------------------------");
            }
        }
    }
    
}
