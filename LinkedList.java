/**
 * LL class.
 * @author Vu Nguyen
 * @param <T> value
 */
public class LinkedList<T> implements ListInterface<T> {
    //Your code HERE.
    /**
     * Inner class.
     */
    protected  class Node{
        /**
         * item to store.
         */
        T item;
        /**
         * next node.
         */
        Node next;
        /**
         * constructor.
         */
        Node(){
            next = null;
        }
    }
    /**
     * size of list.
     */
    private int size;
    /**
     * Node of list.
     */
    private  Node head;

    
    /** 
     * set the item into the index.
     * @param item to set
     * @param index to set
     * @return T return
     */
    @Override
    public T set(T item, int index){
        Node curr = head;
        if(head == null || index < 0 || index >= size){
            return null;
        }
        else if (index == 0) {
            head.item = item;
        }
        else{
            for(int i = 0; i < index; i++){
                curr = curr.next;
            }
            curr.item = item;
        }
        return curr.item;
    } 

    
    
    /** 
     * Get the item at index.
     * @param index provided
     * @return T item
     */
    @Override
    public T get(int index){
        Node curr = head;
        if(head == null || index < 0 || index >= size){
            return null;
        }
        else if (index == 0) {
            return head.item;
        }
        else{
            for(int i = 0; i < index; i++){
                curr = curr.next;
            }
            return curr.item;
        }
    }

    
    
    /** 
     * Add item in the list.
     * @param item to add
     * @param index to add in
     */
    @Override
    public void add(T item, int index){
        Node curr = head;
        Node newNode = new Node();
        newNode.item = item;
        if(index > size || index < 0){
            throw new IndexOutOfBoundsException("Out of bound");
        }
        //if empty
        if(head == null || size == 0){
            head = newNode;
            size ++;
        }
        //if add at 0
        else if (index == 0) {
            newNode.next = head;
            head = newNode;
            size ++;
        }
        else{
            for(int i = 0; i < index - 1; i ++){
                curr = curr.next;
            }
            newNode.next = curr.next;
            curr.next = newNode;
            size ++;
        }
    }
    
    
    /** 
     * Remove the item at index.
     * @param index to rm
     * @return T rm item
     */
    @Override
    public T remove(int index){
        Node curr = head;
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException("Out of bound");
        }
        //if empty
        if(head == null || size == 0){
            return null;
        }
        //if rm at 0
        else if(index == 0){
            Node retNode = head;
            head = head.next;
            retNode.next = null;
            size--;
            return retNode.item;
        }
        else{
            for(int i = 0; i < index - 1; i ++){
                curr = curr.next;
            }
            Node retNode = curr.next;
            curr.next = curr.next.next;
            retNode.next = null;
            size--;
            return retNode.item;
        }
    }

    
    /** 
     * If the list contain item or not.
     * @param item to look for
     * @return boolean T/F
     */
    @Override
    public boolean contains(T item){
        Node curr = head;
        while(curr != null){
            if(curr.item.equals(item)){
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    
    /** 
     * Is the list empty.
     * @return boolean T/F
     */
    @Override
    public boolean isEmpty(){
        if(size == 0 || head == null){
            return true;
        }
        else{
            return false;
        }
    }

    
    /** 
     * Get the size of list.
     * @return int size
     */
    @Override
    public int getSize(){
        return size;
    }


    // private int getIndex(T value) {
    //     Node curr = head;
    //     int index = 0;

    //     while (curr != null) {
    //         if (curr.item.equals(value)) {
    //             return index;
    //         }
    //         curr = curr.next;
    //         index++;
    //     }
    //     return -1; 
    // }



}