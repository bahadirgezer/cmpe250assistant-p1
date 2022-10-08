
public class Holder {

    private Holder nextHolder;

    private Holder previousHolder;

    private final Product product;

    public Holder(Holder nextHolder, Holder previousHolder, Product product) {
        this.nextHolder = nextHolder;
        this.previousHolder = previousHolder;
        this.product = product;
    }

    public Holder getNextHolder() {
        return nextHolder;
    }

    public void setNextHolder(Holder nextHolder) {
        this.nextHolder = nextHolder;
    }

    public Holder getPreviousHolder() {
        return previousHolder;
    }

    public void setPreviousHolder(Holder previousHolder) {
        this.previousHolder = previousHolder;
    }

    public Product getProduct() {
        return product;
    }

}
