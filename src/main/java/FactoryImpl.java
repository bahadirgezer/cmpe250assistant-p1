import java.util.HashSet;
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
        checkProductIndex(index);
        return node(index).getProduct();
    }

    @Override
    public Product removeIndex(int index) throws IndexOutOfBoundsException {
        checkProductIndex(index);
        return unlink(node(index));
    }

    @Override
    public Product removeProduct(int value) {
        for (Holder x = first; x != null; x = x.getNextHolder()) {
            if (x.getProduct().getValue() == value) {
                unlink(x);
                return x.getProduct();
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int filterDuplicates() {
        HashSet<Integer> values = new HashSet<>();
        int removed = 0;
        for (Holder x = first; x != null; x = x.getNextHolder()) {
            if (values.contains(x.getProduct().getValue())) {
                unlink(x);
                removed++;
            } else {
                values.add(x.getProduct().getValue());
            }
        }
        return removed;
    }

    @Override
    public void reverse() {
        final Holder f = first;
        Holder temp;

        for (Holder x = first; x != null; x = x.getNextHolder()) {
            temp = x.getPreviousHolder();
            x.setPreviousHolder(x.getNextHolder());
            x.setNextHolder(temp);
        }

        first = last;
        last = f;
    }

    @Override
    public void add(int index, Product product) throws IndexOutOfBoundsException {
        checkProductIndex(index);

        if (index == size)
            linkLast(product);
        else
            linkBefore(product, node(index));
    }

    private Product unlink(Holder holder) {
        final Product product = holder.getProduct();
        final Holder previousHolder = holder.getPreviousHolder();
        final Holder nextHolder = holder.getNextHolder();

        if (previousHolder == null)
            first = nextHolder;
        else {
            previousHolder.setNextHolder(nextHolder);
            holder.setPreviousHolder(null);
        }

        if (nextHolder == null) {
            last = previousHolder;
        } else {
            nextHolder.setPreviousHolder(previousHolder);
            holder.setNextHolder(null);
        }

        holder.setProduct(null);
        size--;
        return product;
    }

    private void linkLast(Product product) {
        final Holder l = last;
        final Holder newHolder = new Holder(l, product, null);
        last = newHolder;
        if (l == null)
            first = newHolder;
        else
            l.setNextHolder(newHolder);
        size++;
    }

    private void linkBefore(Product product, Holder successor) {
        final Holder predecessor = successor.getPreviousHolder();
        final Holder newHolder = new Holder(predecessor, product, successor);
        successor.setPreviousHolder(newHolder);
        if (predecessor == null)
            first = newHolder;
        else
            predecessor.setNextHolder(newHolder);
        size++;
    }

    private Holder node(int index) {
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
        return x;
    }

    private void checkProductIndex(int index) {
        if (!(index >= 0 && index < size))
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
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

    public Holder getFirst() {
        return first;
    }

    public Holder getLast() {
        return last;
    }

    public Integer getSize() {
        return size;
    }

}
