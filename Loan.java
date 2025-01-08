/**
 * Loan class.
 * @author Vu Nguyen
 */
public class Loan {
    
    //Your code HERE.
    /**
     * Loan ID.
     */
    protected String loanID;
    /**
     * type of card.
     */
    protected String cardType;
    /**
     * Creditor.
     */
    protected String creditorID;
    /**
     * Period ID.
     */
    protected String periodID;
    /**
     * Limit.
     */
    protected double limit;
    /**
     * amount used.
     */
    protected double amountUsed;
    /**
     * Status of loan.
     */
    protected int status;
    /**
     * Contuctor.
     * @param loanID loan id
     * @param cardType card type
     * @param limit lim
     * @param amountUsed used amount
     * @param status status
     * @param creditorID creditor
     * @param periodID periodID
     */
    public Loan(String loanID, String cardType, double limit, double amountUsed, int status, String creditorID, String periodID) {
        //Your code HERE.
        //Make sure all argument are valid according to the Validation rules in the specification
        //Otherwise throw IllegalArgumentException
        this.loanID = loanID; //Validation in addLoan(Client)
        this.limit = limit;
        if(amountUsed == 0){
            throw new IllegalArgumentException("Illegal arg amountUsed");
        }
        this.amountUsed = amountUsed;
        if(status == 0){
            throw new IllegalArgumentException("Illegal arg amountUsed");
        }
        this.status = status;
        /*
         * Code: 
            1: Current account. 
            2: Account 30 days past the due date. 
            3: Account 60 days past the due date. 
            4: Account 90 days past the due date. 
            5: Unpaid balance reported as a loss 
            by credit grantor (charge-off). 
         */
        this.creditorID = creditorID;
        this.periodID = periodID;
    }
    
    
    /** 
     * Compare ssn and creditor ID.
     * @param otherObject to compare
     * @return boolean t/f
     */
    @Override
    public boolean equals(Object otherObject) {
        //Your code HERE.
        //Read the addLoan method description in Client class.
        if(otherObject instanceof Loan){
            Loan anotherObject = (Loan) otherObject;
            if(this.creditorID.equals(anotherObject.creditorID) && this.loanID.equals(anotherObject.loanID)){
                return  true;
            }
        }
        return false;
    }
}
