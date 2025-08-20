package Project;

public class PremiumPlan extends SubscriptionPlan {
    public PremiumPlan() {
        super(9.99);  // Example price
    }

    @Override
    public EncryptionEngine getEncryptionEngine() {
        return new RSAEngine();
    }

    @Override
    public String getPlanName() {
        return "Ultra";
    }
}


