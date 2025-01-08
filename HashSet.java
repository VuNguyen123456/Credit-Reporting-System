/**
 * HS class.
 * @author Vu Nguyen
 * @param <V> generic
 */
public class HashSet<V> {
    //Each place in the hash is a linkedlist
    /**
     * hashTable.
     */
    private LinkedList<V>[] hashTable;
    /**
     * size of table.
     */
    private int size = 0;
    //private int length = 0;
    /**
     * con.
     * @param cap to ini
     */
    @SuppressWarnings("unchecked")
    public HashSet(int cap) {
        //Your code HERE.
        hashTable = new LinkedList[cap];
        for(int i =0; i < cap; i++){
            hashTable[i] = new LinkedList<>();
        }
        //length = cap;
    }
    //add
    /**
     * put in set.
     * @param value to put
     */
    @SuppressWarnings("unchecked")
    public void put(V value){
        //Time complexity: O(1)
        //Resizing the array if load factor is more than 1
        if(size > hashTable.length){
            LinkedList<V>[] temp = new LinkedList[hashTable.length * 2];
            //length = hashTable.length * 2;
            for(int i = 0; i < hashTable.length * 2; i++){
                temp[i] = new LinkedList<V>();
            }

            for(int i = 0; i < hashTable.length; i++){
                temp[i] = hashTable[i];
            }
            hashTable = temp;
        }
        size += 1;

        //Putting it inside
        int hashValue = Math.abs(value.hashCode()) % hashTable.length; // put it here
        if(hashTable[hashValue].isEmpty()){
            //Add if it's empty
            hashTable[hashValue].add(value, 0);
        }
        else{
            //Add if collision
            hashTable[hashValue].add(value, hashTable[hashValue].getSize());
        }
        // size += 1;

    }
    //seach, Get the actual value in hashtable provided a value with ssn and creditor
    /**
     * get the value in hashtable.
     * @param value to get
     * @return value
     */
    public V get(V value){
        int hashValue = Math.abs(value.hashCode()) % hashTable.length;
        try{
            for(int i = 0; i < size; i++){
                if(hashTable[hashValue].get(i).equals(value)){
                    return hashTable[hashValue].get(i);
                }
            }
        }catch(Exception e){
            return null;
        }
        return null;
    }
}