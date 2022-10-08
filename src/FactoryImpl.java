import java.util.NoSuchElementException;

public class FactoryImpl implements Factory {

    private Holder firstHolder;

    private Holder lastHolder;


    @Override
    public void addFirst(Holder holder) {

    }

    @Override
    public void addLast(Holder holder) {

    }

    @Override
    public Holder removeFirst() throws NoSuchElementException {
        return null;
    }

    @Override
    public Holder removeLast() throws NoSuchElementException {
        return null;
    }

    @Override
    public Product find(Integer id) throws NoSuchElementException {
        return null;
    }

    @Override
    public Product update(Integer id, Integer value) throws NoSuchElementException {
        return null;
    }
}
