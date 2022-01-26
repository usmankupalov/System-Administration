package System;
import java.util.*;
import java.util.Random;

public class Bank {
      private String name;
      // The name of the bank
      
      private ArrayList<User> users;
      // The list of user in bank
      
      private ArrayList <Account> accounts;
      //The list of account in bank
      
      /**
       * Create a new Bank object with empty lists of users and accounts 	
       * @param name  the name of the bank
       */
      
      public Bank(String name) {
    	  this.name = name;
    	  this.users = new ArrayList<User>();
    	  this.accounts = new ArrayList<Account>();
      }
      
      
      /**
       *  Generate a new universally unique ID for a user 
       * @return the ID
       */
      public String getNewUserID() {
    	  //initializing
    	  String id;
    	  Random rng = new Random();
    	  int lenght = 6;
    	  boolean nonUnique = false;
    	  
    	  // continue looping until we get a unique  ID
    	  do {
    		  //generate the number
    		  id = "";
    		  for(int c = 0; c < lenght; c++) {
    			  id += ((Integer)rng.nextInt(10)).toString();	
    		  }
    		  //check to make sure it is unique
    		  nonUnique = false;	
    		  for(User u: this.users) {
    			  if(id.compareTo(u.getID()) == 0){
    			  nonUnique = true;
    			  break;
    			  }
    		  }
    	  }  while(nonUnique);
    	  
    	  return id;
      }
      
      /**
       * Get New Account for ID user
       * @return  id
       */
      
      
      public String getNewAccountID() {
    	  String id;
    	  Random rng = new Random();
    	  int lenght = 10;
    	  boolean nonUnique = false;
    	  
    	  // continue looping until we get a unique  ID
    	  do {
    		  //generate the number
    		  id = "";
    		  for(int c = 0; c < lenght; c++) {
    			  id += ((Integer)rng.nextInt(10)).toString();	
    		  }
    		  //check to make sure it is unique
    		  nonUnique = false;	
    		  for(Account a: this.accounts) {
    			  if(id.compareTo(a.getID()) == 0){
    			  nonUnique = true;
    			  break;
    			  }
    		  }
    	  }  while(nonUnique);
    	  
    	  return id;
      }
      
      
      /**
       *  Add a account
       * @param anAcct     the account to add 
       */ 
      public void addAccount(Account anAcct) {
    	  this.accounts.add(anAcct);
      }
      
      
      /**
       * Create a new user for the bank
       * @param firstName        the user's first name
       * @param lastName         the user's last name
       * @param pin              the user's pin
       * @return                 the new user object
       */
      public User addUser(String firstName, String lastName, String pin) {
    	  
    	  // create a new User object and add it to our list
    	  User newUser = new User(firstName, lastName, pin, this);
    	  this.users.add(newUser);
    	  
    	  // create a savings account for the user and add  to User
    	  // account lists
    	  Account newAccount =new Account("Savings", newUser, this);
       	  newUser.setAccount(newAccount);
      	  this.accounts.add(newAccount);
      	  
      	  return newUser;
      }
      
      
      /**
       *  Get the User object associated with a particular userID and pin, if they are valid
       *
       * @param userID       the ID of the user to log in
       * @param pin          the pin of the user
       * @return             the User object, if the login is succesful, or null, 
       * if it s not
       */ 
      public User userLogin(String userID, String pin) {
    	  
    	  // search through list of user
    	  for(User u: this.users) {
    		  
    		  //check user ID is correct
    		  if(u.getID().compareTo(userID) == 0 && u.validate(pin)) {
    			  return u;
    		  }
    	  }
    	  // if we haven't found the user or have an incorrect pin
    	  return null;	
      }
      
      
      /**
       *  Get the name of the Bank
       * @return   the name of the Bank	
       */
      
      public String getName() {
    	  return this.name;
      }    
      
}
