/**
 * Person class.
 * @author Vu Nguyen
 */
public class Person {

    //Your code HERE.
    // protected ssn: String ;
    // protected firstName: String ;
    // protected lastName: String ;
    // protected otherNames: ListInterface<String> ;
    /**
     * ssn.
     */
    protected String ssn;
    /**
     * First name.
     */
    protected String firstName;
    /**
     * Last name.
     */
    protected String lastName;
    /**
     * other's name.
     */
    protected ListInterface<String> otherNames = new LinkedList<>();

    /**
     * Constructor.
     * @param ssn to initialize
     */
    protected Person(String ssn) {
        //Your code HERE.
        //No validation rules apply here.
        this.ssn = ssn;
    }
    /**
     * Contructor 2.
     * @param ssn to ini
     * @param firstName to ini
     * @param lastName ini
     */
    public Person(String ssn, String firstName, String lastName) {
        //Your code HERE.
        //Make sure all argument are valid according to the Validation rules in the specification
        //Otherwise throw IllegalArgumentException
        if(ssn == null || ssn.length() != 11 || ssn.charAt(3) != '-' || ssn.charAt(6) != '-'  || !Character.isDigit(ssn.charAt(0)) || !Character.isDigit(ssn.charAt(1)) || !Character.isDigit(ssn.charAt(2))|| !Character.isDigit(ssn.charAt(4))|| !Character.isDigit(ssn.charAt(5))|| !Character.isDigit(ssn.charAt(7))|| !Character.isDigit(ssn.charAt(8))|| !Character.isDigit(ssn.charAt(9)) || !Character.isDigit(ssn.charAt(10))){
            throw new IllegalArgumentException("Illegal arg ssn");
        }
        if(firstName == null || firstName.length() < 2){
            throw new IllegalArgumentException("Illegal arg lastname");
        }
        if(lastName == null || lastName.length() < 2){
            throw new IllegalArgumentException("Illegal arg firstname");
        }
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    /**
     * Method to add others name if they already have 1.
     * @param firstName to change
     * @param lastName to change
     * @return t/f
     */
    public boolean otherName(String firstName, String lastName) {
        //Your code HERE.
        //if the name provided as argument is different from the actual name, add this provided new name to the list otherNames. 
        
        if(firstName == null || firstName.length() < 2){
            throw new IllegalArgumentException("Illegal arg lastname");
        }
        if(lastName == null || lastName.length() < 2){
            throw new IllegalArgumentException("Illegal arg firstname");
        }

        //Compare and adding other Name in
        if(!this.firstName.equals(firstName) || !this.lastName.equals(lastName)){
            String newName = firstName + " " + lastName;
            //need to ensure that the new name is new new
            if(otherNames.contains(newName)){
                return false;
            }
            otherNames.add(newName, 0);
            return true;
        }
        else{
            return false;
        }
    }

    // public boolean updateName(String firstName, String lastName){
    //     if(firstName == null || firstName.length() < 2){
    //         return false;
    //     }
    //     if(lastName == null || lastName.length() < 2){
    //         return false;
    //     }
    //     this.firstName = firstName;
    //     this.lastName = lastName;
    //     return true;
    // }


    /**
     * Comparing 2 object.
     * @param  anotherObject to compare
     * @return t/f
     */
    @Override
    public boolean equals(Object anotherObject){
        if(this == anotherObject){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * get fist name.
     * @return fname
     */
    protected String getFname(){
        return firstName;
    }
    /**
     * get last name.
     * @return lname
     */
    protected  String getLname(){
        return lastName;
    }

    /**
     * Print out person info.
     * @return the string of info
     */
    public String toString() {
        //Your code HERE.
        //See the output example in the project description.
        String printThis = "\nFirst Name\t: "+this.firstName+"\n";
        printThis += "Last Name\t: "+this.lastName+"\n";
        printThis += "SSN\t\t: "+this.ssn+"\n";
        printThis += "Other Names\t: ";
        for(int i = 0; i < otherNames.getSize(); i++){
            printThis += otherNames.get(i);
            if(i != otherNames.getSize()-1){
                printThis += ", ";
            }
        }
        return printThis;
    }
    /**
     * main.
     * @param args gument
     */
    public static void main(String[] args) {

        // //Add more tests

        // Person p = new Person("577-27-4193", "Adrien", "Feldmann");
        // p.otherName("Adrian", "Feldmann");
        // p.otherName("Adrian", "Feldman");
        // System.out.println(p);


        // Valid Person creation and adding other names
        Person p = new Person("577-27-4193", "Adrien", "Feldmann");
        p.otherName("Adrian", "Feldmann");
        p.otherName("Adrian", "Feldman");
        System.out.println(p);

        // Test case: Adding the same name (should not be added again)
        p.otherName("Adrian", "Feldmann");
        System.out.println("\nAfter attempting to add duplicate name:");
        System.out.println(p);

        // Invalid SSN (should throw IllegalArgumentException)
        try {
            Person invalidPerson = new Person("577274193", "John", "Doe");  // Invalid format (no dashes)
        } catch (IllegalArgumentException e) {
            System.out.println("\nCaught exception for invalid SSN format: " + e.getMessage());
        }

        // Invalid first name and last name (should throw IllegalArgumentException)
        try {
            Person invalidNamePerson = new Person("577-27-4193", "A", "Doe");  // Invalid first name length
        } catch (IllegalArgumentException e) {
            System.out.println("\nCaught exception for invalid first name length: " + e.getMessage());
        }

        // Test with only first name change
        Person p2 = new Person("123-45-6789", "John", "Doe");
        p2.otherName("Johnny", "Doe");
        System.out.println("\nAfter adding a different first name:");
        System.out.println(p2);
    }

}
