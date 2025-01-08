import java.io.*;
import com.opencsv.*;
/**
 * CRS class.
 * @author Vu Nguyen
 */
public class CRS {
    /**
     * hash set.
     */
    private static HashSet<Client> hashSet = new HashSet<>(100);
    /**
     * list of creditor.
     */
    private static LinkedList<String> listOfCreditors = new LinkedList<>();

    //add creditors stack contents to the linkedlist listOfCreditors
    /**
     * Add creditor to list.
     * @param creditors to add
     */
    public static void setListOfCreditors(StackInterface<String> creditors) {
        if(creditors == null){
            return;
        }
        while(creditors.isEmpty() == false){
            listOfCreditors.add(creditors.pop(), 0);
        }
    }

    //return array of files (all files in the folder folderName)
    /**
     * get list of file in folder.
     * @param folderName name of folder
     * @return file
     */
    public static File[] getListOfFiles(String folderName) {
        File folder = new File(folderName);
        if(folder.isDirectory() == false){
            return new File[0];
        }
        File[] files = folder.listFiles();
        if(files == null){
            return new File[0];
        }  
        return files;
    }

    //load only data from the creditors added to the listOfCreditors to the hashSet
    //if a line contains invalid data (as per the validation rules provided, skip this line, do not stop the loading process)
    //the method will add only valid data to the hashset
    //return true if some data from the file were added to the dataset, false otherwise
    /**
     * load data into hashset.
     * @param file name
     * @return t/f
     */
    public static boolean loadData(File file) {
        //Your code HERE.
        int check = 0;
        String fileName = file.getName();
        String creditorID = fileName.substring(0, 4);
        //Checking if the creditor is in there or not?
        for(int i = 0; i < listOfCreditors.getSize(); i++){
            if(creditorID.equals(listOfCreditors.get(i))){
                check = 1;
            }
        }
        if(check == 0){
            return false;
        }
        //CSV reader to read from provided file
        String periodId = fileName.substring(5, 11);
        try (CSVReader reader = new CSVReader(new FileReader(file))){
            String[] toSplit;
            reader.readNext(); //disregard the header of file
            while((toSplit = reader.readNext()) != null){
                String ssn = toSplit[0];
                String fname = toSplit[1];
                String lname = toSplit[2];
                String jobTitle = toSplit[3];
                String employer = toSplit[4];
                String loanId = toSplit[5];
                String cardType = toSplit[6];
                double limit = Double.parseDouble(toSplit[7]);
                double amountDue = Double.parseDouble(toSplit[8]);
                int status = Integer.parseInt(toSplit[9]);
                //Initialize Client + Loan?
                Client client = new Client(ssn,fname,lname,jobTitle,employer,creditorID, periodId);
                Loan loan = new Loan(loanId, cardType, limit, amountDue, status, creditorID, periodId);
                //If same ssn and creditor don't add to hashtable but instead update existing client
                if(hashSet.get(client) != null){
                    hashSet.get(client).otherName(client.getFname(), client.getLname());
                    hashSet.get(client).addLoan(loan);   
                }
                else{
                    //Add to table
                    client.addLoan(loan);
                    hashSet.put(client);
                }
            }
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
        //return true;
    }

    //Merging client with same ssn regardless of ssn to print them 
    /**
     * merging the client to print.
     * @param ssn to find
     * @return client
     */
    private static Client mergning(String ssn){
        Client whole = new Client(ssn, listOfCreditors.get(listOfCreditors.getSize()-1));
        whole = hashSet.get(whole);
        //Check each ssn and creditor combination
        for(int i = listOfCreditors.getSize()-1; i >= 0; i--){
            String creditor = listOfCreditors.get(i);
            Client temp = new Client(ssn, creditor);
            //Adding others name to proxy client
            for(int j = 0; j < hashSet.get(temp).otherNames.getSize(); j++){
                String[] wholeName = hashSet.get(temp).otherNames.get(j).split(" ", 2);
                whole.otherName(wholeName[0], wholeName[1]);
            }
            whole.otherName(hashSet.get(temp).getFname(), hashSet.get(temp).getLname());
            //Adding loans to proxy client
            for (int j = hashSet.get(temp).loans.getSize() - 1; j >= 0; j--) {
                whole.addLoan(hashSet.get(temp).loans.get(j));
            }
        }
        return whole;   
    }
    /**
     * make report for that person.
     * @param ssn to find
     * @return string
     */
    public static String createReport(String ssn) {
        //Your code HERE.
        String result = "FULL Credit Report";
        result += mergning(ssn).toString();
        return result;
    }
    /**
     * main.
     * @param args gument
     */
    public static void main(String[] args) {

        //ONLY FOR TESTING PURPOSE...
        //Refer to the project description for expected ouput of createReport().

        //Provide the list of creditors to consider
        Stack<String> creditors = new Stack<>();
        creditors.push("C090");
        creditors.push("C099");

        //Move the creditors code from the stack to the list of creditors using the method setListOfCreditors
        setListOfCreditors(creditors);

        //Load data from the CSV files to the HashSet
        //1- Get all the files (ignoring the list of creditors)        
        File[] files = getListOfFiles("dataset");
        System.out.println("Files found in the dataset folder:" + files.length);
        int countFilesLoaded = 0;

        //2-Read files content (loadData() should return false if the file creditor ID is not in listOfCreditors)   
        for(int i=0; i<files.length; i++)
            if(loadData(files[i])) {
                countFilesLoaded++;
            }
        System.out.println("Files loaded into the hashset:" + countFilesLoaded);

        System.out.println("");

        //3-create report for a client        
        System.out.println(createReport("577-27-4193"));
    }
}
