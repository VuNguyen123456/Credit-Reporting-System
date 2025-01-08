/**
 * List inter.
 * @author Vu Nguyen
 * @param <T> value
 */
public interface ListInterface<T> {
    //Your code HERE.
    /**
    * Set an element of the list.
    * @param item to set
    * @param index to set
    * @return the new lists
    */
    public T set(T item, int index);
    /**
    * Get an element of list.
    * @param index to get
    * @return the value.
    */
    public T get(int index);
    /**
    * Add an element to list.
    * @param item to add
    * @param index to add
    */
    public void add(T item, int index); 
    /**
    * Remove item from list.
    * @param index to rm
    * @return rm item
    */
    public T remove(int index);
    /**
    * Check if it contain Item.
    * @param item to find
    * @return is it in or not
    */
    public boolean contains(T item);
    /**
    * Is the listEmpty.
    * @return boolean
    */
    public boolean isEmpty();
    /**
    * Get the size.
    * @return size
    */
    public int getSize();

}
