/**
 * Stack class.
 * @author Vu Nguyen
 * @param <T> value
 */
public class Stack<T> implements StackInterface<T> {
    //Your code HERE. (Time complexity: O(1) for all operations)
    /**
     * the stack.
     */
    private LinkedList<T> stack = new LinkedList<>();

        
    /** 
     * Add item on top.
     * @param item item do add
     */
    @Override
    public void push(T item){
        stack.add(item, 0);
    }

        
    /** 
     * remove the top.
     * @return T removed
     */
    @Override
    public T pop(){
        return stack.remove(0); 
    } 

        
    /** 
     * Look at the top.
     * @return T the item tha was peeked
     */
    @Override
    public T peek(){
        return stack.get(0);
    }

        
    /** 
     * Is the stack empty.
     * @return boolean t/f
     */
    @Override
    public boolean isEmpty(){
        return stack.isEmpty();
    }


}
