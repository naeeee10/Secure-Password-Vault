package Project;

public class UltraPlan extends SubscriptionPlan {
    public UltraPlan() {
        super(4.99);  // Example price
    }

    @Override
    public EncryptionEngine getEncryptionEngine() {
        return new AESEngine();
    }

    @Override
    public String getPlanName() {
        return "Ultra";
    }
}


