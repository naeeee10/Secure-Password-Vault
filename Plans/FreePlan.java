package Project;

public class FreePlan extends SubscriptionPlan {
    public FreePlan() {
        super(0.0);  // Free plan
    }

    @Override
    public EncryptionEngine getEncryptionEngine() {
        return new PlayfairEngine();
    }

    @Override
    public String getPlanName() {
        return "Free";
    }
}
