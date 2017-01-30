// Driver.java

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*; 
import java.util.*; 

public class Driver extends JFrame implements ActionListener{
    
     JMenu fileMenu,customersMenu;
	 //Linked list custs which holds all AccHolders
     LinkedList<AccHolder> custs = new LinkedList<AccHolder>(); 
	 JLabel slot1, slot2, slot3, result;
     ImageIcon image1, image2, image3;
     JButton playGame;
	 JTextField stakeField, usernameField ;
	 //Array of strings which holds paths to images
	 String[] images = {"aceHearts.png","aceDiamond.png","aceSpade.png","aceClubs.png"};
	 int j;
     Container c;
     
    // driver 
    public static void main( String[] args ) {
        Driver  frame = new Driver();
        frame.setVisible(true);
    }
    
    // constructor
    public Driver ( ) {
        
        super("Slot Machine");
        try{
        	
        //The system always loads accounts created when the program is ran
        	open();
        }
        
        //System catches any errors when file is opened     
        catch (Exception p){
        	errorMessage("There was an error reloading accounts");
           }
        
        
        setSize(750,600);
        setLocation(100,100);
        c = getContentPane();
      
        c.setBackground(Color.white);
        
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        
        //Methods to create and add menu items
        createFileMenu();
        createCustomersMenu();
        
        //Adding JMenuBar to the jframe
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(customersMenu);
        
        //Adding the header image to the jframe
        ImageIcon headerImage = new ImageIcon("header.jpg");
        JLabel header = new JLabel();
        header.setIcon(headerImage);
        header.setBounds(0,0,750,60);
        c.add(header);
        
        //method which adds jlabels and jtextfields to the jframe
        addInputArea();
        
        
        //Method which creates the slot area on the GUI
        addSlots();
       
       	
        playGame = new JButton("Play");
		c.add(playGame);
		playGame.setBounds(300,450,100,30);
		playGame.addActionListener(this);
        
        result = new JLabel();
        result.setBounds(300,560,30,30);
        c.add(result);
     }     
    
	  //This code was taken from notes and modified
      public void save() throws IOException {
      	ObjectOutputStream outStream;
      	outStream = new ObjectOutputStream(new FileOutputStream ("customers.dat"));
      	outStream.writeObject(custs);
      	outStream.close();
      }
      
      //This code was taken from notes and modified
      public void open() throws Exception{
      	
      	
      	  ObjectInputStream inStream;
      	  inStream = new ObjectInputStream(new FileInputStream ("customers.dat"));
          custs  = (LinkedList<AccHolder>) inStream.readObject();
      	  inStream.close(); 
     
      	
      
      } // end open()
      
     
      public void addCustomer(){
      	AccHolder cust = new AccHolder();
      	
      	//Using AccHolder setter method setName()
      	cust.setName(JOptionPane.showInputDialog("Please enter your name"));
	
		int age=-1;
		
		//keep asking for age until a number greater than zero is entered
		do {
    
    	try {
		        age = Integer.parseInt(JOptionPane.showInputDialog("Enter your age"));
		     
   		}
   		//All invalid inputs are caught and error message is displayed	
   		catch (NumberFormatException e) {
        
        		errorMessage("Error, not a number. Please try again.");
    	}
		}while(age<0);
		
		//Using AccHolder setter method setAge()
		cust.setAge(age);
		
		//Using AccHolder setter method setEmail()
		cust.setEmail(JOptionPane.showInputDialog("Please enter your email address"));
		
		
		String userName = JOptionPane.showInputDialog("Please enter your desired username");
		
		
		int balance = -1;
		//keep asking for a balance until a number greater than zero is entered
		do{
		
			try{
			balance = Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of coins you wish to purchase"));
			
			}
		//All invalid inputs are caught and error message is displayed	
		catch (NumberFormatException r){
			errorMessage("Error, not a number. Please try again");
		} 
				
		}while(balance<0);
		//Using setAccount() method from accHolder class 
		//this method creates an instance of the Account class for each new customer
		cust.setAccount(userName,balance);
      	
      	//the created cust is added to the custs LinkedList
      	custs.add(cust);
      
      	//When the details are entered they must be saved to the customers.dat file
      	try{
      	 	save();
      	 	ImageIcon icon = new ImageIcon("");
      	 	JOptionPane.showMessageDialog(null,"Congraulations!! Your account has been created.","Success",JOptionPane.PLAIN_MESSAGE, icon);
      	 }
      	 //any errors are caught and error message is displayed
      	 catch (IOException f){
      	 	
      	 	errorMessage("There was an error creating your account please try again");
      	 	
      	 }
      }
      
      //Displays details of a selected customers account
      public void display(){
      
        
      	getAcc();
      	
		//if the account exists
      	if(j<custs.size())
      	{
      	
      	  	JOptionPane.showMessageDialog(null,custs.get(j).toString());
      		
      	}
      	//if no account can be found with the entered details	
      	else
      	{
      		errorMessage("No Account with the given email");    	 
      	}
      	}
      
      
      public void actionPerformed (ActionEvent e) {
      	if (e.getActionCommand() .equals ("Quit")){
      	 JOptionPane.showMessageDialog(null,"Shutting down the system");
      	 System.exit(0);
      	}
      	else if (e.getActionCommand() .equals ("Add")){
      	   addCustomer(); 
      	}
      	else if (e.getActionCommand() .equals ("Profile")){
           
           	display();
           
      	}
      	
      	else if (e.getActionCommand() .equals ("Remove")){
      		
      		getAcc();
      		
      		remove(j);
      
      	}// else if
      	
      	else if (e.getActionCommand() .equals ("Open")){
      	 
      	 
      	 
      	 
      	}
      
      	else if (e.getActionCommand().equals("Play")){
      	
      	play();	
      	
      	}
      	
      	else if(e.getActionCommand().equals("Add Funds")){
      		
      	getAcc();	

      	if(j<custs.size())
      	{
      		int funds = Integer.parseInt(JOptionPane.showInputDialog("How many coins would you like to purchase"));
      		
      		custs.get(j).updateBalance(funds);
      		
      	}	
      	else
      	{
      		errorMessage("No Account with that username.Please try again"); 	 
      	}
      		
      		
      	}
      	
      	else
      	  JOptionPane.showMessageDialog(null,"I have no idea what you clicked");
      } // actionPerformed
      
      
        private void createFileMenu(){
         // create the menu
      	fileMenu = new JMenu("File");
        
      	JMenuItem item;
      
   
      	item = new JMenuItem("Remove Account");
      	item.addActionListener(this);
      	fileMenu.add(item);
      	
      	// create the 'quit' option
      	fileMenu.addSeparator();
      	item = new JMenuItem("Quit");
      	item.addActionListener(this);
      	fileMenu.add(item);
      }
      
      private void createCustomersMenu(){
      	// create the menu
      	customersMenu = new JMenu("Customers");
      	// declaring a menu item which can be reused
      	JMenuItem item;
      	
      	//Creating and adding of 'Add' menu item which allows new customers to register
      	item = new JMenuItem("Add");
      	item.addActionListener(this);
      	customersMenu.add(item);
      	
      	//Creating and adding of 'Profile' menu item which allows existing customers to view their profile
      	item = new JMenuItem("Profile");
      	item.addActionListener(this);
      	customersMenu.add(item);
      	
      	//Creating and adding of 'Add Funds' menu item which allows existing customers to top up their balance
      	item = new JMenuItem("Add Funds");
      	item.addActionListener(this);
      	customersMenu.add(item);
      }
      
      private int getAcc(){
      	
      	String username = JOptionPane.showInputDialog("Enter your username");
      	
      	//The username entered is checked to see if any customers have that username
        //The loop continues until the username is found or until there are no customers left to check
        //If the username was found j will have a value less than the total number of customers
        //j will give the LinkedList index for the customer with the entered username
        while ( j < custs.size() && !custs.get(j).getUsername().equals(username)) {
            j++;
        }
        
        //Returning the index of the desired customers
        return j; 

      }
      
      private void addSlots(){
      
      	//Creating and adding the first slot to the JFrame
      	slot1 = new JLabel();
        slot1.setIcon(image1);
        slot1.setBounds(20,170,250,250);
        c.add(slot1);
       
		//Creating and adding the second slot to the JFrame
        slot2 = new JLabel();
        slot2.setIcon(image2);
        slot2.setBounds(270,170,250,250);
        c.add(slot2);
          
        //Creating and adding the third slot to the JFrame
        slot3 = new JLabel();
        slot3.setIcon(image3);
        slot3.setBounds(520,170,250,250);
        c.add(slot3);
      }
      
      public void addInputArea(){
      	
      	//Creating and adding JLabel to the jframe
      	JLabel usernameLabel = new JLabel("Please enter your username:");
		c.add(usernameLabel);
		usernameLabel.setBounds(30,70,400,30);
		
		//Creating JTextField for the user to enter their username
		usernameField = new JTextField(15);
		c.add(usernameField);
		usernameField.setBounds(235,75,150,20);
		
		//Creating and adding JLabel to the jframe
		JLabel stakeLabel = new JLabel("Please enter your stake");
		c.add(stakeLabel);
		stakeLabel.setBounds(30,110,400,30);
		
		//Creating JTextField for the user to enter the number of coins they wish to gamble
		stakeField = new JTextField(6);
		c.add(stakeField);
		stakeField.setBounds(235,110,50,20);
      }
      
      public void errorMessage(String message){
      	
      	JOptionPane.showMessageDialog(null,message,"Error",JOptionPane.ERROR_MESSAGE);
      }
      
      public void play(){
      	
      	//Get the username entered by the player
		String username = usernameField.getText();
		
      	while ( j < custs.size() && !custs.get(j).getUsername().equals(username)) {
            j++;
        }
      	
      	
		//if the password was found
      	if(j<custs.size())
      	{
    		
    		// the number of coins the user wish to gamble is taken from the textbox and converted to an int
			
			int stake=0;
			do{
			
			try{
			
			 stake = Integer.parseInt(stakeField.getText());
			}
			
			catch (NumberFormatException n){
				errorMessage("You must enter a number greater than zero");
				return;
				
			}
			}while (stake<=0);
			
			
			// checks to make sure the user j has enough coins to place the stake they entered, error message appears if insufficent funds available
			if(custs.get(j).getBalance()<stake){
				errorMessage("You dont have enough coins to place that stake.\n Please go and add funds to your account");
				
				usernameField.setText("");
				stakeField.setText("");
				
			}
			else{
			
			//3 random numbers generated between 0-3	
			int one = (int)(Math.random() * 4);
			int two = (int)(Math.random() * 4);
			int three = (int)(Math.random() * 4);
			
			/*the numbers generated are passed into the images array to get the path to the image,which
			is then used to create  a new ImageIcon*/	
			image1= new ImageIcon(images[one]);	
			image2= new ImageIcon(images[two]);	
			image3= new ImageIcon(images[three]);	
						
			
			//the images created are used as icon images in the jlabels created in the constructor
			slot1.setIcon(image1);
			slot2.setIcon(image2);
			slot3.setIcon(image3);
			
			
			
			//check to see if the 3 numbers generated are equal
			if(one == two & two == three){
				
				result.setText("");
				
				//the stake entered is multiplyed by the reward to get the winnings
				int winnings = stake*3;
				
				JOptionPane.showMessageDialog(null,"Congratualations you won " + winnings);
				
				//the customer j ,has there balance updated to include their winnings
				custs.get(j).updateBalance(winnings);
				
				
				
				try{//the system saves to keep track of the new balance
					save();
				}
				catch (IOException w){
					errorMessage("Unfortunatly, there was an error in the system.");
				}
			}
			
			//The numbers did not match
			else{
				
				result.setText("You have lost");
				
				//the stake amount is reduced from the customers account
				custs.get(j).updateBalance(-stake);
				
				try{//the new balance is saved
					save();
				}
				catch (IOException w){
					errorMessage("Unfortunatly, there was an error in the system");
				}
				}
      	
      		
			}
      	}
      	//the password entered was not found in the system	
      	else
      	{
      		errorMessage("No Account with that username.Please try again."); 
      				 
      	}
      }
       
}