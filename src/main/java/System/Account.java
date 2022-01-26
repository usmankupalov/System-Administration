package System;
import java.util.*;
public class Account {
    private String name;
    // The name of the account
    
    private String id;
    // The account ID number
    
    private User holder;
    // 	The User object that owns this account 
    
    private ArrayList <Transaction> transactions;
    // The list of Transaction for this account
    
    
    /**
     * Create a new account
     * @param name     the name of the account
     * @param id       the User object that holds this account
     * @param theBank  the bank that issues the account 
      */
    public Account(String name, User holder, Bank theBank) {
    	
    	// set the account name and holder 
    	this.name = name;
    	this.holder = holder;
    	
    	//get new account ID
    	this.id = theBank.getNewAccountID();
    	
    	// init transactions 
    	this.transactions = new ArrayList <Transaction>();
    		
    }
    
    /**
     *  Get the account ID 
     * @return the ID
     */
    public String getID() {
    	return this.id;
    }
    
    /**
     * Get summary line for the account
     * @return     the string summary
     */
    
    public String getSummaryLine() {
    	
    	// get the account's balance
    	double balance = this.getBalance();
    	
    	// format the summary line, depending on the whether the balance is negative
    	if(balance >= 0) {
    		return String .format("%s : $%.02f : %s", this.id, balance,
    				this.name);
    	} else {
    		return String .format("%s : $(%.02f) : %s", this.id, balance,
    				this.name);
    	}
 	
    }
    
    /**
     *  Get the balance of this account by adding the amounts of the transactions	
     * @return   the balance value
     */
    
    public double getBalance() {
    	double balance = 0;
    	for(Transaction t: this.transactions) {
    		balance += t.getAmount();
    	}
    	return balance;
    }
    
    /**
     *  Print the transaction history of the account
     */
    
    public void printTransHistory() {
    	System.out.printf("\nTransaction history for account %s\n", this.id);
    	for(int t = this.transactions.size()-1; t >= 0; t--) {
    		System.out.println(this.transactions.get(t).getSummaryLine());
    	}
    	System.out.println();
    }
    
    /**
     * Add a new transaction in this account
     * @param amount         the amount transacted 
     * @param memo           the transaction memo
     */
    public void addTransaction(double amount, String memo) {
    	
    	// create a new transaction object and add it to our list
    	Transaction newTrans = new Transaction(amount, memo, this);
    	this.transactions.add(newTrans);
    }
   
}
