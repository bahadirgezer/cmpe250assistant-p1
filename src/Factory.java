import java.util.NoSuchElementException;

public interface Factory {

    /**
     * Insters {@code holder} at the begining of the factory line.
     *
     * @param holder the holder to add
     */
    void addFirst(Holder holder);

    /**
     * Inserts {@code holder} to the end of the factory line.
     *
     * @param holder the holder to add
     */
    void addLast(Holder holder);

    /**
     * Removes and returns the first holder from the factory line.
     *
     * @return the first holder from the factory line or {}
     * @throws NoSuchElementException if the line is empty
     */
    Holder removeFirst() throws NoSuchElementException;

    /**
     * Removes and returns the first holder from the factory line.
     *
     * @return the first holder from the factory line
     * @throws NoSuchElementException if the line is empty
     */
    Holder removeLast() throws NoSuchElementException;

    /**
     * Finds and returns the product with the specified {@code id}.
     *
     * @param   id id of the product
     * @return  the product with the specified id
     * @throws  NoSuchElementException if the product does not exist
     */
    Product find(Integer id) throws NoSuchElementException;

    /**
     * Updates the product value with {@code id} in the factory line
     * with the given {@code newValue}.
     *
     * @param   id id of the product to update
     * @param   value new value for the specified product
     * @return  the previous product with {@code id}
     * @throws  NoSuchElementException if the product does not exist
     */
    Product update(Integer id, Integer value) throws NoSuchElementException;

}
