//AccHolder.java
/**This is an instantiable Account Holder class.
  @author Damien Breen
  @version 1.0*/
 
 import java.io.*;
 
 public class AccHolder implements Serializable{
 	
 	//attributes
 	private String name;
 	private int age;
 	private String email;
 	private Account account;
 	
 	
 	/**no argument constructor
		*/
 	public AccHolder(){
 		this("UNKNOWN",0,"UNKNOWN","",0);
 		
 	}
 	
 	/**five argument constructor method
 	 @param name the full name of the account holder
 	 @param age the age of the account holder
 	 @param email the email of the account holder
 	 @param username the username for the account
 	 @param balance the balance of the account*/
 	 
 	public AccHolder(String name,int age, String email,String username,int balance){
 		
 		setName(name);
 		setAge(age);		
 		setEmail(email);
 		account = new Account(username,balance);
 		
 		
 	}
 	
 	/**acessor method to return account holder name
 	 *@return the name of the account holder*/
 	public String getName(){
		
		return name;
	}
	
	/**acessor method to return account holder age
 	 *@return the age of the account holder*/
	public int getAge(){
		
		return age;
	}
	
	/**acessor method to return account holder email
 	 *@return the email of the account holder*/
	public String getEmail(){
		
		return email;
	}

	/**acessor method to return account holder account
 	 *@return the account of the account holder*/	
	public Account getAccount(){
		
		return account;
	}
	
	/**mutator method to set the account holder name
	 *@param name the name of the account holder*/
	public void setName(String name){
		
		this.name = name;
	}

	/**mutator method to set the account holder age
	 *@param age the age of the account holder*/ 	
 	public void setAge(int age){
 	
 		
 		this.age = age;
	}

	/**mutator method to set the account holder email
	 *@param email the email of the account holder*/
	public void setEmail(String email){
		
		this.email = email;
	}

	/**mutator method to set the account holder account
	 *@param account the account of the account holder*/	
	public void setAccount(String username, int balance){
		
		account.setAccount(username,balance);
	}
	
	public void updateBalance(int winnings){
		account.updateBalance(winnings);
	}
	

	/**acessor method to return account username
 	 *@return the username of the account */		
	public String getUsername(){
		return account.getUsername();
	}
	

	/**acessor method to return account balance
 	 *@return the balance of the account */	
	public int getBalance(){
		return account.getBalance();
	}

	/**toString method to return the account holder details
	 *@return the name, age, email, username and balance of the account holder as a string*/
	public String toString(){
		
		return "Name: " + getName() + "\nAge:" + getAge() +
			   "\nEmail: " + getEmail() + account.toString();
		
	}
	
 }