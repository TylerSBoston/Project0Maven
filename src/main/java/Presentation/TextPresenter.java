package Presentation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import org.apache.logging.log4j.*;

import BasicClasses.*;


public class TextPresenter {
	private static Scanner scanner = new Scanner(System.in);
	private User loggedInUser;
	private Command output = new Command();
	private final Logger log = LogManager.getLogger(this.getClass());
	
	public Command createOrLogin(User user)
	{
		log.info("In login page");
		int returnValue = 0;
		if(!user.getErrorText().equals(""))
		{
			System.out.println("invalid User and/or Password");
		}
		while(true)
		{
			System.out.println(	"1.) to create account\n"
							+	"2.) for Customer Login\n"
							+ 	"3.) for Employee Login\n"
							+ 	"0.) Exit");
			returnValue = InputVerifier.verifyInt(scanner);
			if(returnValue == 2)
			{
				output.setUser(customerLogin());
				output.setOutput(Command.command.customerLogin);
				return output;
			}
			else if(returnValue == 1)
			{
				output.setUser(create());
				output.setOutput(Command.command.createUserAccount);
				return output;
			}
			else if(returnValue == 3)
			{
				output.setUser(employeeLogin());
				output.setOutput(Command.command.employeeLogin);
				return output;
			}
			else if(returnValue == 0)
			{
				System.out.println("Exiting");
				output.setOutput(Command.command.exit);
				return output;
			}
			else
			{
				System.out.println("enter a valid option");
			}
		}
	}
	
	
	
	private User customerLogin()
	{
		System.out.print("Enter your user name: ");
		String user = scanner.nextLine().trim();
		System.out.print("Enter password: ");
		String password = scanner.nextLine().trim();
		
		return new Customer(user,password); 
		
	}
	private User employeeLogin()
	{
		
		System.out.print("Enter your user name: ");
		String user = scanner.nextLine().trim();
		System.out.print("Enter password: ");
		String password = scanner.nextLine().trim();
		
		return new Employee(user,password); 
		
	}
	private User create()
	{
		log.info("In create user Page");
		System.out.println("Enter a user Name: ");
		String userName = scanner.nextLine().trim();
		String pass1 = "";
		String pass2 = "";
		do {
			if(pass1 != pass2)
			{
				System.out.println("Different passwords entered");
			}
			System.out.println("Enter a password");
			pass1 = scanner.nextLine();
			System.out.println("Reenter your password");
			pass2 = scanner.nextLine();
		}while(!pass1.equals(pass2));
		// this would normally have extra user info but just name for now
		System.out.println("Enter your firstName");
		String firstName = scanner.nextLine();
		System.out.println("Enter your LastName");
		String lastName = scanner.nextLine();
		System.out.println("Enter your Email");
		String email = scanner.nextLine();
		System.out.println("Enter your Phone Number");
		String phone = scanner.nextLine();		
		return new User(userName,pass1,firstName,lastName,email,phone);
	}
	public Command customerInterface()
	{
		log.info("In customer interface Page");
		int input = -1;
		//exits by return
		while(true)
		{
			System.out.println("Select Option: \n"
							+ "1.) Create bank Account \n"
							+ "2.) View bank Account(s) \n"
							+ "3.) get AccountStatement(s) \n"
							+ "4.) Transfer \n"
							+ "0.) Log out");
			input = InputVerifier.verifyInt(scanner);
	        switch (input) {
            case 1: 
            	output.setOutput(Command.command.createBankAccount);
            	output.setReturnObject(createBankAccount());
            	if(output.getReturnObject() != null)
            	{
            		return output;
            	}
                     break;
            case 2: 
            	output.setOutput(Command.command.getBankAccounts);
            	return output;
            case 3:  
            	output.setOutput(Command.command.getStatements);
            	return output;
            case 4:  
            	output.setOutput(Command.command.transferGetAccounts);
            	return output;
            case 0: 
            	output.setOutput(Command.command.logoff);
                return output;
            default:
            	System.out.println("Enter a valid Input");
            	break;
	        }
			
			
		}
	}
	
	private Account createBankAccount()
	{
		log.info("In create Bank Account");
		int input;
		String name = "";
		boolean pass = false;
		Account.Type accountType = Account.Type.checkings;
		BigDecimal depositAmount = new BigDecimal(0);
		Account newAccount;
		depositAmount.setScale(2,RoundingMode.CEILING);
		while(pass == false)
		{
			System.out.println("You are creating a new account");
			System.out.println("Select Account Type: \n"
							 + "1.) checkings\n"
							 + "2.) savings\n"
							 + "3.) student\n"
							 + "4.) investment\n"
							 + "0.) cancle");
			input = InputVerifier.verifyInt(scanner);
	        switch (input) {
	        case 1: 
	        	accountType = Account.Type.checkings;
	        	pass = true;
	            break;
	        case 2: 
	        	accountType = Account.Type.savings;
	        	pass = true;
	            break;
	        case 3:  
	        	accountType = Account.Type.student;
	        	pass = true;
	            break;
	        case 4:  
	        	accountType = Account.Type.investment;
	        	pass = true;
	        	break;
	        case 0: 
	        	
	        	return null;
	        default:
	        	System.out.println("Enter a valid Input");
	        	break;
	        }
		}
			
		// just assumes no on an incorrect Input
		System.out.println("Are you making an initial Deposit: Y/N");
		String makeDeposit = scanner.nextLine();
		if(makeDeposit.toUpperCase().trim().equals("Y"))
		{
			System.out.println("Enter deposit amount");
			depositAmount = BigDecimal.valueOf(scanner.nextDouble());
			scanner.nextLine();
		}
		
		System.out.println("Make an Account Name: Y/N");
		String makeName = scanner.nextLine();
		
		if(makeName.toUpperCase().trim().equals("Y"))
		{
			System.out.println("Enter Name");
			name = scanner.nextLine();
		}
		if(name != "") 
		{
			newAccount = new Account(loggedInUser.getUserID(),accountType,depositAmount, name);
		}
		else
		{
			newAccount = new Account(loggedInUser.getUserID(),accountType,depositAmount);
		}
		return newAccount;
		
		
		
	}
	
	public Command transfer()
	{
		log.info("In transfer");
		Command com = new Command();
		com.setUser(loggedInUser);
		int accountNumber = 0;
		if(loggedInUser.getAccounts().size() >= 2)
		{
			System.out.println("select account to transfer from");
			for(int spot = 0; spot<loggedInUser.getAccounts().size(); spot++)
			{
				System.out.println((spot+1) + ".)" + loggedInUser.getAccounts().get(spot).getAccountName());
			}
			System.out.println();
			accountNumber = InputVerifier.verifyInt(scanner) - 1;
			scanner.nextLine();
		}
		System.out.println("Transferee Routing Number: ");
		String routingNumb = scanner.nextLine().trim();
		System.out.println("Transferee Account Number: ");
		String accountNumb = scanner.nextLine().trim();
		System.out.println("transfer Amount: ");
		double input = scanner.nextDouble();
		scanner.nextLine();
		BigDecimal transfer = new BigDecimal(input);
		transfer.setScale(2,RoundingMode.CEILING);
		Transfer out = new Transfer(transfer,routingNumb,accountNumb,loggedInUser.getAccounts().get(accountNumber).getID());
		com.setReturnObject(out);
		com.setOutput(Command.command.transfer);
		System.out.println("");
		return com;
	}
	public Command EmployeeInterface()
	{
		int input = -1;

		log.info("In employee interface Page");
		//Currently Customer
		while(true)
		{
			System.out.println("Select Option: \n"
							+ "1.) Register Customr \n"
							+ "2.) View Customer's \n"
							+ "0.) Log out");
			input = InputVerifier.verifyInt(scanner);
	        switch (input) {
            case 1: 
            	output.setOutput(Command.command.registerCustomer);
            	return output;
            case 2: 
            	output.setOutput(Command.command.viewCustomers);
            	return output;
            case 0: 
            	output.setOutput(Command.command.logoff);
                return output;
            default:
            	System.out.println("Enter a valid Input");
            	break;
	        }
			
			
		}
	}
	
	
	
	
	public User getLoggedInUser() {
		return loggedInUser;
	}
	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
		output.setUser(loggedInUser);
	}
	
	public static void transactionstatus(boolean pass)
	{
		if(pass == true)
			System.out.println("Transaction Success");
		else
			System.out.println("Transaction Failed");
	}
	public static void transactionstatus(String errorMessage)
	{
		System.out.println(errorMessage);
	}



	public void displayAccounts(LinkedList<Account> accounts) 
	{
		log.info("In display accounts Page");
		System.out.println();
		if(accounts.size() == 0)
		{
			System.out.println("You do not have any accounts");
		}
		for(Account a : accounts)
		{
			System.out.println("******************************************");
			System.out.println("Account: " + a.getAccountName());
			System.out.println("Balance: " + a.getBalance().toString());
			System.out.println("Routing number: " + a.getRoutingNumber());
			System.out.println("Routing number: " + a.getAccountNumber());
			System.out.println("Account type: " + a.getAccountType());
			System.out.println("******************************************");	
		}
		
		
		
		// TODO Auto-generated method stub
		
	}



	public void displayTransactions(LinkedList<TransactionView> transactions) {
		// TODO Auto-generated method stub
		log.info("In display transactions Page");
		System.out.println();
		System.out.println("******************************************");
		if(transactions.size() == 0)
		{
			System.out.println("You do not have any Trajsactions");
		}
		for(TransactionView t : transactions)
		{	
			System.out.println("From:  "+t.getSenderName() + " , " + t.getSenderAccountName() + "    To: "+ t.getRecieverName() +" , "+ t.getRecieverAccountName() + "        " + t.getAmount().toString()+  "     " +t.getDate());
		}
		System.out.println("******************************************");
		
	}



	public LinkedList<Customer> customerRegistration(LinkedList<Customer> registerCustomers) {
		// TODO Auto-generated method stub
		log.info("In customer registration Page");
		System.out.println("Listing unregistered Users");
		for(Customer c : registerCustomers)
		{
			System.out.println();
			System.out.println("Name: " +c.getFirstName() + " " + c.getLastName());
			System.out.println("Email: " + c.getEmail());
			System.out.println("Phone: " + c.getPhone());
			System.out.println("\nApprove? Y/N");
			boolean pass = false;
			String nextLine;
			while(pass == false)
			{
				nextLine = scanner.nextLine();
				if(nextLine.equals("Y"))
				{
					c.setActive(1);
					pass = true;
				}
				else if(nextLine.equals("N"))
				{
					c.setActive(-1);
					pass = true;
				}
				else
				{
					System.out.println("Enter a valid input");
				}
			}
		}
		return registerCustomers;
	}



	public void DisplayCustomers(LinkedList<Customer> customers) {
		// TODO Auto-generated method stub
		log.info("In display Page");
		System.out.println("**********************************");
		for(Customer c : customers)
		{
			System.out.println();
			System.out.println("Name: " +c.getFirstName() + " " + c.getLastName());
			System.out.println("Email: " + c.getEmail());
			System.out.println("Phone: " + c.getPhone());
			System.out.println("**********************************");

		}
		
		
		
		
	}
	

}
