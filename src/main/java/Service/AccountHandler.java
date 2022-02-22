package Service;

import java.math.BigDecimal;
import java.rmi.AccessException;
import java.util.HashSet;
import java.util.LinkedList;

import BasicClasses.*;
import Dao.*;
import Presentation.TextPresenter;
import org.apache.logging.log4j.*;
public class AccountHandler implements MultiLayorInteractable<Account>{

	
	private final Logger log = LogManager.getLogger(this.getClass());
	// adds to be enabled
	@Override
	public Account add(Account item) {
		// TODO Auto-generated method stub
		
		if(item.getBalance().intValue() >= 0)
		{
			try {
				DBAccountHandler.add(item);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.warn("Failed to add Account" + e.getMessage());
			}
		}
		else {
			TextPresenter.serviceMessage("Negative Balance Entered");
		}
		return null;
	}
	
	// enables account
	public void Enable(Account item) {
		
		
	}

	@Override
	public void update(Account item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deactivate(int ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account get(int ID) {
		// TODO Auto-generated method stub
		
		
		
		return null; 
	}
	
	public LinkedList<Account> getAccounts(int UserID) 
	{	

		try {
			return DBAccountHandler.getAccounts(UserID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.warn("Failed in getAccounts" + e.getMessage());
		}
		return new LinkedList<Account>();
	}
	
	@Override
	public String errorText() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void transfer(BigDecimal amount, String routing, String account,int originalID)
	{
		try
		{	
			if(ValidateTransfer(amount,DBAccountHandler.getBalance(originalID)))
				DBAccountHandler.Transfer(amount,routing,account,originalID);
			else
				TextPresenter.serviceMessage("Invalid amount");
		}
		catch(Exception e)
		{
			TextPresenter.transactionstatus(false);
			log.warn("Transfer Failed " + e.getMessage());
		}
		
		
	}
	
	// this is here so I have something that can be unit tested that isn't dependent on user input in the test method or on the DB
	public boolean ValidateTransfer(BigDecimal amount, BigDecimal currentAmount)
	{
		if(amount.compareTo(BigDecimal.ZERO) == 1 &&  amount.compareTo(currentAmount) < 1 )
		{
			return true;
		}
		return false;
	}
	
	public LinkedList<TransactionView> getTransactions(int ID)
	{
		try {
			return DBAccountHandler.getTransfers(ID);
		} catch (Exception e) {
			log.warn("getTransaction Failed " + e.getMessage());
		}
		return new LinkedList<TransactionView>();
	}

}
