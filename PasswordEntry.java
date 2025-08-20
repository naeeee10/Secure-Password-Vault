package Project;

public class PasswordEntry {
    private String id;
    private String site; // Represents the website or application name.
    // Stores the password: when stored in the vault it is encrypted.
    private String password;
    
    public PasswordEntry(String id, String site, String password) {
        this.id = id;
        this.site = site;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getSite() {
        return site;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
