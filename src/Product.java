
public class Product {

    private final int id;

    private Integer value;

    public Product(int id, Integer value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
