package Service;

import java.util.LinkedList;

import BasicClasses.*;
import Dao.DBConnection;
import Presentation.*;
import org.apache.logging.log4j.*;
// main in service as presentation is assumed to be the webpage/website in a normal application
public class Main {

	private final Logger log = LogManager.getLogger(this.getClass());
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TextPresenter presenter = new TextPresenter();
		User loggedInUser = new User();
		Command input = new Command();
		UserHandler userHandler = new UserHandler();
		AccountHandler accountHandler = new AccountHandler();
		boolean exit = true;
		
		// exit only on admin close, assumed to be a website or ATM senario where the application is up 24/7
		while(exit)
		{
			// login process
			if(input.getOutput() == Command.command.none)
			{
				input = presenter.createOrLogin(loggedInUser);
				loggedInUser = input.getUser();
				if(input.getOutput() == Command.command.customerLogin)
				{
					loggedInUser = userHandler.get(loggedInUser.getUser(), loggedInUser.getPassword());
					if(loggedInUser.getErrorText() != "")
					{
						input.setOutput(Command.command.none);
						presenter.transactionstatus(loggedInUser.getErrorText());
					}
					else
					{
						presenter.setLoggedInUser(loggedInUser);
					}
				}
				else if(input.getOutput() == Command.command.customerLogin)
				{
					loggedInUser = userHandler.get(loggedInUser.getUser(), loggedInUser.getPassword());
					if(loggedInUser.getErrorText() != "")
					{
						input.setOutput(Command.command.none);
						presenter.transactionstatus(loggedInUser.getErrorText());
					}
					else
					{
						presenter.setLoggedInUser(loggedInUser);
					}
				}
				else if(input.getOutput() == Command.command.createUserAccount)
				{
					loggedInUser = userHandler.add(loggedInUser);
					presenter.setLoggedInUser(loggedInUser);
				}
				else if(input.getOutput() == Command.command.exit)
				{
					DBConnection.closeConnection();
					exit = false;
					
				}
				
			}
			// Customer menu and options
			
			else if(loggedInUser instanceof Customer)
			{
				//*****************************************************************
				// Accept Tranfer code here*********************************************************************
				//*****************************************************************
				
				input = presenter.customerInterface();
				switch (input.getOutput())
				{
				case createBankAccount :
					if(input.getReturnObject() instanceof Account)
						accountHandler.add((Account)input.getReturnObject());
					break;
				case getBankAccounts :
						loggedInUser.setAccounts(accountHandler.getAccounts(loggedInUser.getUserID()));
						presenter.setLoggedInUser(loggedInUser);
						presenter.displayAccounts(loggedInUser.getAccounts());
					break;
					// no longer used/deprecated
				case deposit :
					if(input.getReturnObject() instanceof Account)
						accountHandler.add((Account)input.getReturnObject());
					break;
					// no  longer used/deprecated
				case withdraw :
					if(input.getReturnObject() instanceof Account)
						accountHandler.add((Account)input.getReturnObject());
					break;
				case getStatements :
					presenter.displayTransactions(accountHandler.getTransactions(loggedInUser.getUserID()));
				break;
				case transferGetAccounts :
					loggedInUser.setAccounts(accountHandler.getAccounts(loggedInUser.getUserID()));
					presenter.setLoggedInUser(loggedInUser);
					input = presenter.transfer();
					if(input.getReturnObject() instanceof Transfer)
					{
						Transfer trans = (Transfer)input.getReturnObject();
						accountHandler.transfer(trans.getAmount(),trans.getTargetRoutingNumber(), trans.getTargetAccountNumber(), trans.getOriginID());
					}
					
				break;
				case logoff :
					input.setOutput(Command.command.none);
					loggedInUser = new User();
					presenter.setLoggedInUser(loggedInUser);
				
				break;
				default:
				
					break;
				}
			}
			// Employee menu and options
			else if(loggedInUser instanceof Employee)
			{
				input = presenter.EmployeeInterface();
				switch(input.getOutput())
				{
				case registerCustomer : 
					userHandler.setRegistration(presenter.customerRegistration(userHandler.RegisterCustomers()));
				break;
				case viewCustomers  :
					presenter.DisplayCustomers(userHandler.getCustomers());
				break;
				case logoff :
					input.setOutput(Command.command.none);
					loggedInUser = new User();
					presenter.setLoggedInUser(loggedInUser);
					break;
				}
				
				
				
			}
			//
			// why isn't a user automaticly a customer? only other distinction is employee which would need an admin process to be created/aka not in this app
			else if(loggedInUser.getAccountType() == User.AccountType.User)
			{
				
			}

		}
	}

}
