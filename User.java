package Project;
import java.util.UUID;

public class User {
    private String username;
    private String password;
    private SubscriptionPlan subscriptionPlan;
    private PasswordVault vault;

    public User(String username, String password, SubscriptionPlan subscriptionPlan) {
        this.username = username;
        this.password = password;
        this.subscriptionPlan = subscriptionPlan;
        this.vault = new PasswordVault(UUID.randomUUID().toString(), subscriptionPlan.getEncryptionEngine());
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SubscriptionPlan getSubscriptionPlan(){
        return this.subscriptionPlan;
    }
    
    public void setSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        this.subscriptionPlan = subscriptionPlan;
    }

    public PasswordVault getVault() {
        return this.vault;
    }

}