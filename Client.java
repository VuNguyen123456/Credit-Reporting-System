/**
 * Client class.
 * @author Vu Nguyen
 */
public class Client extends Person {

    //Your code HERE.
    /**
     * job title.
     */
    protected  String jobTitle;
    /**
     * employer.
     */
    protected String employer;
    /**
     * creditor.
     */
    protected String creditorID;
    /**
     * period ID.
     */
    protected String periodID;
    /**
     * loans client have.
     */
    protected ListInterface<Loan> loans = new LinkedList<>();

    /**
     * Constructors.
     * @param ssn ssn
     * @param firstName name
     * @param lastName name
     * @param job job title
     * @param employer exmploy
     * @param creditorID creditor id
     * @param period periodID
     */
    public Client(String ssn, String firstName, String lastName, String job, String employer, String creditorID, String period) {
        //Your code HERE.
        //You must reuse code from the parent class
        //Make sure all argument are valid according to the Validation rules in the specification
        //Otherwise throw IllegalArgumentException
        super(ssn, firstName, lastName);
        if(job != null){
            if(job.length() < 4){
                throw new IllegalArgumentException("Illegal arg job");
            }
            else{
                jobTitle = job;
            }
        }
        if(jobTitle != null){
            if(employer.length() < 4){
                throw new IllegalArgumentException("Illegal arg job");
            }
            else{
                this.employer = employer;
            }
        }
        this.creditorID = creditorID;
        this.periodID = period; //weird might not be right bwo........


    }
    /**
     * Constructor to use get.
     * @param ssn to ini
     * @param creditorID to ini
     */
    protected Client(String ssn, String creditorID) {
        //Your code HERE.
        //No validation rules apply here.
        super(ssn);
        this.creditorID = creditorID;
    }

    
    /** 
     * Check ssn and creditor if they are equal.
     * @param otherObject to compare
     * @return boolean
     */
    @Override
    public boolean equals(Object otherObject) {
        //Your code HERE.
        //Return false if : the 2 instances do not correspond to clients with same ssn and creditor_id;
        if(otherObject instanceof Client){
            //Casting
            Client anotherObject = (Client) otherObject;
            if(this.ssn.equals(anotherObject.ssn) && this.creditorID.equals(anotherObject.creditorID)){
                return true;
            }
        }
        return false;
    }
    /**
     * add the loan in the list.
     * @param loan to add
     */
    public void addLoan(Loan loan) {
        //Your code HERE.
        //Add this loan to the list of loans.
        //Do not allow duplicate. (2 loan instances are considered the same if they have same loanID and from same creditorID)
        //Check that client creditor id is the same as loan creditor id when adding a new loan to the list of loans.
        if(loans == null){
            //System.out.println("loan is null???");
            return;
        }
        for(int i = 0; i < loans.getSize(); i++){
            if(loans.get(i).creditorID.equals(loan.creditorID) && loans.get(i).loanID.equals(loan.loanID)){
                //Add only when creditorID and loanID are diff
                return;
            }
        }
        loans.add(loan, 0);
    }
    /**
     * add another name in list.
     * @param firstName name
     * @param lastName name
     * @param periodID period
     * @return t/f
     */
    public boolean otherName(String firstName, String lastName, String periodID) {
        //Your code HERE.
        //You must reuse code from the parent class.
        //if the name provided as argument is different from the actual name, add this provided new name to the list otherNames. 
        //otherName
        this.periodID = periodID;
        return super.otherName(firstName, lastName);
    }
    
    /** 
     * String the client info.
     * @return String
     */
    @Override
    public String toString() {
        //Your code HERE.
        //You must reuse code from the parent class.
        //See the output example in the project description.
        double total = 0.0;
        int totalCount= 0;
        double current = 0.0;
        int currentCount = 0;
        double notCurrent= 0.0;
        int notCurrentCount = 0;
        int chargeOffCount = 0;
        //Adding up the loans and divide them into groups
        if(loans != null){
            for(int i = 0; i < loans.getSize(); i++){
                if(loans.get(i).status != 5){
                    //Total amount
                    total += loans.get(i).amountUsed;
                }
                totalCount += 1;
                if(loans.get(i).status == 1){
                    //Current amount
                    current += loans.get(i).amountUsed;
                    currentCount += 1;
                }
                else if(loans.get(i).status == 2 || loans.get(i).status == 3 || loans.get(i).status == 4){
                    //Not current
                    notCurrent += loans.get(i).amountUsed;
                    notCurrentCount += 1;
                }
                else{
                    //ChargeOFF
                    chargeOffCount += 1;
                }
            }
        }
        String result = super.toString();
        result += "\n";
        result += "Loans\t\t: " +totalCount+ " -- Total Used Amount: " +total+ "\n";
        result += " - Current\t: "+currentCount+" -- Used Amount: "+current+"\n";
        result += " - Not Current\t: "+notCurrentCount+" -- Used Amount: "+notCurrent+"\n";
        result += " - ChargeOff\t: "+chargeOffCount+" \n\n";
        result += "\t - Submitted by:" +creditorID+ "\n";
        result += "\t - Last Update:" +periodID;
        return result;
    }
    
    /** 
     * Make hashCode.
     * @return int
     */
    @Override
    public int hashCode() {
        //Your code HERE.s
        //2 client instances with same ssn should have the same hashcode.
        int code = ssn.hashCode();
        return  code;
    }

    // protected String getFname(){
    //     return firstName;
    // }

    // protected  String getLname(){
    //     return lastName;
    // }
    /**
     * main.
     * @param args gument
     */
    public static void main(String[] args) {
        //Add more tests...
        // Client c = new Client("577-27-4193", "Adrien", "Feldmann", "Sales Associate", "Jenkins Inc", "C090", "202408");
        // System.out.println(c);
        // Test 1: Create a Client and print their information
        Client c1 = new Client("577-27-4193", "Adrien", "Feldmann", "Sales Associate", "Jenkins Inc", "C090", "202408");
        System.out.println("Test 1: Client Information");
        System.out.println(c1);
        
        // Test 2: Create another Client and test equality
        Client c2 = new Client("577-27-4193", "Adrian", "Feldmann", "Sales Manager", "Jenkins Corp", "C090", "202409");
        System.out.println("\nTest 2: Are the two clients equal?");
        System.out.println("Expected: true (same SSN and creditorID)");
        System.out.println(c1.equals(c2)); // Should return true because they share the same SSN and creditorID

        // Test 3: Different SSN should not be equal
        Client c3 = new Client("123-45-6789", "John", "Doe", "Sales", "Company X", "C091", "202410");
        System.out.println("\nTest 3: Are the clients with different SSN equal?");
        System.out.println("Expected: false (different SSN)");
        System.out.println(c1.equals(c3)); // Should return false

        // Test 4: Add a loan and print details
        Loan loan1 = new Loan("L001", "Credit Card", 5000.00, 1000.00, 1, "C090", "202408");
        c1.addLoan(loan1);
        System.out.println("\nTest 4: After adding a loan to Client");
        System.out.println(c1);

        // Test 5: Add another loan and check for duplicates
        Loan loan2 = new Loan("L002", "Mortgage", 200000.00, 50000.00, 5, "C090", "202408");
        Loan duplicateLoan = new Loan("L001", "Credit Card", 5000.00, 1000.00, 1, "C090", "202408"); // Duplicate loan
        c1.addLoan(loan2);
        c1.addLoan(duplicateLoan); // Should not add this because it's a duplicate
        System.out.println("\nTest 5: After attempting to add a duplicate loan");
        System.out.println(c1);

        // Test 6: Add a new name for the client
        System.out.println("\nTest 6: Adding a new name to Client");
        boolean nameChanged = c1.otherName("Adrien", "Feldman", "202408");
        System.out.println("Name changed: " + nameChanged); // Should be true because the name is slightly different
        System.out.println(c1);

        // Test 7: Test invalid input (short job title)
        try {
            Client invalidClient = new Client("123-45-6789", "John", "Doe", "IT", "Company Y", "C092", "202410");
        } catch (IllegalArgumentException e) {
            System.out.println("\nTest 7: Invalid client creation due to short job title");
            System.out.println(e.getMessage());
        }
    }
}
