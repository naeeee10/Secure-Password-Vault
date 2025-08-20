package Project;

public abstract class SubscriptionPlan {
    protected double price;

    public SubscriptionPlan(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public abstract EncryptionEngine getEncryptionEngine();
    public abstract String getPlanName();
}
