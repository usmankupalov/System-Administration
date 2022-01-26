package System;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;


	
public class User {
     private String firstName;
     // The first name of user
     
     private String lastName;
     // The last name of user
     
     private String id;
     // The ID of user
     
     private byte pinHash[]; 
     // Pin number of user
     
     private ArrayList <Account> accounts;
     // The list number of user
     
     /*
      * Create a new user
      * @param firstName the user's first name
      * @param lastName  the user's last name
      * @param pin       the user's account pin number
      * @param theBank   the Bank object that the user  is a customer 
      * */
     
     public User(String firstName, String lastName, String pin, Bank theBank){ 
    	 
    	  // set user's first and last name  
    	 this.firstName = firstName;
    	 this.lastName = lastName;
    	 
    	 // store the pin's MD5 hash, rather the original value, for security reasons
    	 try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes());
		} catch (NoSuchAlgorithmException e) {
			System.err.println("error, caught NoSuchAlgorithmException ");
			e.printStackTrace();
			System.exit(1);
		}
    	 // get a new unique universal ID for the user
    	 this.id = theBank.getNewUserID();
    	 
    	 // create empty list of accounts
    	 this.accounts = new ArrayList<Account>();
    	 
    	 //print log message
    	 System.out.printf("New user %s, %s with ID %s created \n", lastName, firstName, this.id);
     }
     
     
     /**
      *  Add an account for the user
      * @param anAcct     the account to add
      */
     public void setAccount(Account anAcct) {
    	 this.accounts.add(anAcct);
     }
     
     
     /**
      *   Return the user's ID
      * @return the ID 
      */
     public String getID() {
    	 return this.id;
     }
     
     
     /**
      *   Check whether a given pin matches the true User pin 
      * @param aPin       the pin to check
      * @return           whether the pin is valid or not
      */
     public boolean validate(String aPin) {
    	 
    	 try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return 	MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
		} catch (NoSuchAlgorithmException e) {
			System.err.println("error, caught NoSuchAlgorithmException ");
			e.printStackTrace();
			System.exit(1);
		}
    	 return false;
     }
     
     /**
      * Return   the user's first name
      * @return  the first name
      */
     
     public String getFirstName() {
    	 return this.firstName;
     }
     
     /**
      * Print summaries for the accounts of this user
      */
     
     public void printAccountsSummary() {
    	 System.out.printf("\n\n%s's accounts summary\n", this.firstName);
    	 for (int a =0; a < this.accounts.size(); a++) {
    		 System.out.printf("  %d) %s\n", a+1, 
    				 this.accounts.get(a).getSummaryLine());
    	 }
    	 System.out.println();
     }
     
     /**
      *  Get the number of accounts of the user 
      * @return    the number of accounts
      */
     
     public int numAccounts() {
    	 return this.accounts.size();
     }
     
     /**
      * Print transaction history for a particular account
      * @param acctIdx    the index of the account to use
      */
     public void printAcctTransHistory(int acctIdx) {
    	 this.accounts.get(acctIdx).printTransHistory();
     }
     
     /**
      * Get the balance of a particular account
      * @param acctIdx     the index of the account to use
      * @return            the balance of the account
      */
     public double getAcctBalance(int acctIdx) {
    	 return this.accounts.get(acctIdx).getBalance();
     }
     
     /**
      * Get the Id of a particular account
      * @param acctIdx     the index of the account to use
      * @return            the Id of the account
      */
     public String getAcctId(int acctIdx) {
    	 return this.accounts.get(acctIdx).getID();
     }
     
     /**
      * Add a transaction to a particular account
      * @param acctIdx       the index of the account
      * @param amount        the amount of the transaction
      * @param memo          the memo of the transaction
      */
     public void addAcctTransaction(int acctIdx, double amount, String memo) {
    	 this.accounts.get(acctIdx).addTransaction(amount, memo); 
     }
}























