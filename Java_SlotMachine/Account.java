//Account.java
/**This is an instantiable account class.
  @author Damien Breen
  @version 1.0*/
import java.io.*;

public class Account  implements Serializable{
	
	//attributes
	private String username;
	private int balance;
	
	/**no argument constructor
		*/
	public Account(){
		
		this("",0);
	}
	
	
 	/**two argument constructor method
 	 @param username the username of the account 
 	 @param balance the balance of the account*/
	public Account(String username,int balance){
		
		setUsername(username);
		setBalance(balance);
		
	}
	
	
	/**mutator method to set the account username
	 *@param username the username of the account*/
	public void setUsername(String username){
		
		this.username = username;
	}
	
 	/**acessor method to return account username
 	 *@return the username of the account*/
	public String getUsername(){

		return username;
	}
	
	/**mutator method to set the account balance
	 *@param balance the balance of the account*/
	public void setBalance(int balance){
		
		this.balance = balance;
	}
	
 	/**acessor method to return account balance
 	 *@return the balance of the account*/
	public int getBalance(){
		
		return balance;
	}
	
	
	/**update method to update the account balance
	 *@param winnings the winnings of the account*/
	public void updateBalance(int winnings){
		this.balance += winnings;
	}
	

	
	/**mutator method to set the account username and balance
	 *@param username the username of the account
	  @param balance the balance of the account*/
	public void setAccount(String username, int balance){
		
		setUsername(username);
		setBalance(balance);
	
	}
	
	
	/**toString method to return the account details
	 *@return the username and balance of the account as a string*/
	public String toString(){
		
		return "\nUsername:" + getUsername() + "\nBalance: " + getBalance() + " coins.";
		
	}
	
	
	
}