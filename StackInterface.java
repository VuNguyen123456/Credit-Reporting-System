/**
 * stack inter.
 * @author Vu Nguyen
 * @param <T> value
 */
public interface StackInterface<T> {
    //Your code HERE.
    /**
     * add to the stack.
     * @param item to add
     */
    public void push(T item);
    /**
     * remove from stack.
     * @return poped
     */
    public T pop();
    /**
     * Too return the value on top withuot rm it.
     * @return peeked
     */
    public T peek();
    /**
     * Check if Stack empty.
     * @return t/f
     */
    public boolean isEmpty();

}
