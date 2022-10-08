import java.util.NoSuchElementException;

public class FactoryImpl implements Factory {

    private Holder first;

    private Holder last;

    private Integer size;

    public FactoryImpl() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public void addFirst(Product product) {
        final Holder f = first;
        final Holder newHolder = new Holder(null, product, f);
        first = newHolder;
        if (f == null)
            last = newHolder;
        else
            f.setPreviousHolder(newHolder);
        size++;
    }

    @Override
    public void addLast(Product product) {
        final Holder l = last;
        final Holder newHolder = new Holder(l, product, null);
        last = newHolder;
        if (l == null)
            first = newHolder;
        else
            l.setNextHolder(newHolder);
        size++;
    }

    @Override
    public Product removeFirst() throws NoSuchElementException {
        final Holder f = first;
        if (f == null)
            throw new NoSuchElementException();
        final Product product = f.getProduct();
        final Holder next = f.getNextHolder();
        f.setProduct(null);
        f.setNextHolder(null);
        first = next;
        if (next == null)
            last = null;
        else
            next.setPreviousHolder(null);
        size--;
        return product;
    }

    @Override
    public Product removeLast() throws NoSuchElementException {
        final Holder l = last;
        if (l == null)
            throw new NoSuchElementException();
        final Product product = l.getProduct();
        final Holder prev = l.getPreviousHolder();
        l.setProduct(null);
        l.setPreviousHolder(null);
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.setNextHolder(null);
        size--;
        return product;
    }

    @Override
    public Product find(int id) throws NoSuchElementException {
        for (Holder x = first; x != null; x = x.getNextHolder()) {
            if (x.getProduct() == null)
                continue;
            if (x.getProduct().getId() == id)
                return x.getProduct();
        }
        throw new NoSuchElementException();
    }

    @Override
    public Product update(int id, Integer value) throws NoSuchElementException {
        for (Holder x = first; x != null; x = x.getNextHolder()) {
            if (x.getProduct() == null)
                continue;
            if (x.getProduct().getId() == id) {
                final Product p = new Product(
                        x.getProduct().getId(),
                        x.getProduct().getValue());
                x.getProduct().setValue(value);
                return p;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public Product get(int index) throws IndexOutOfBoundsException {
        if (!(index >= 0 && index < size))
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);

        Holder x;
        if (index < (size >> 1)) {
            x = first;
            for (int i = 0; i < index; i++)
                x = x.getNextHolder();
        } else {
            x = last;
            for (int i = size - 1; i > index; i--)
                x = x.getPreviousHolder();
        }
        return x.getProduct();
    }

    public String toString() {
        if (size == 0)
            return "{}";

        StringBuilder sb = new StringBuilder("{");
        for (Holder x = first; x != null; x = x.getNextHolder()) {
            sb.append(x).append(",");
        }
        sb.delete(sb.length()-1, sb.length());
        sb.append("}");
        return sb.toString();
    }

}
