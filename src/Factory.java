import java.util.NoSuchElementException;

public interface Factory {

    /**
     * Insters {@code product} at the begining of this factory line.
     *
     * @param product the product to add
     */
    void addFirst(Product product);

    /**
     * Inserts {@code product} to the end of this factory line.
     *
     * @param product the product to add
     */
    void addLast(Product product);

    /**
     * Removes and returns the first product from this factory line.
     *
     * @return the first product from this factory line
     * @throws NoSuchElementException if the line is empty
     */
    Product removeFirst() throws NoSuchElementException;

    /**
     * Removes and returns the first product from this factory line.
     *
     * @return the last product from this factory line
     * @throws NoSuchElementException if the line is empty
     */
    Product removeLast() throws NoSuchElementException;

    /**
     * Finds and returns the product with the specified {@code id}.
     *
     * @param id id of the product
     * @return the product with the specified id
     * @throws NoSuchElementException if the product does not exist
     */
    Product find(int id) throws NoSuchElementException;

    /**
     * Updates the product value with {@code id} in this factory line
     * with the given {@code newValue}.
     *
     * @param id    id of the product to update
     * @param value new value for the specified product
     * @return the previous product with {@code id}
     * @throws NoSuchElementException if the product does not exist
     */
    Product update(int id, Integer value) throws NoSuchElementException;

    /**
     * Returns the product at the specified position in this factory line.
     *
     * @param index index of the product to return
     * @return the product at the specified position in this factory line
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    Product get(int index) throws IndexOutOfBoundsException;

}
