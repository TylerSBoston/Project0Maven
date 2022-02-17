package Service;

import java.math.BigDecimal;
import java.rmi.AccessException;
import java.util.HashSet;
import java.util.LinkedList;

import BasicClasses.*;
import Dao.*;
import Presentation.TextPresenter;

public class AccountHandler implements MultiLayorInteractable<Account>{

	
	
	// adds to be enabled
	@Override
	public Account add(Account item) {
		// TODO Auto-generated method stub
		
		if(item.getBalance().intValue() >= 0)
		{
			DBAccountHandler.add(item);
		}
		else {
			TextPresenter.transactionstatus("Negative Balance Entered");
		}
		return item;
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
		return DBAccountHandler.getAccounts(UserID);
	}
	
	public String[] getAccountStatements(int ID)
	{
		return DBAccountHandler.getAccountStatements(ID);
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
			if(amount.compareTo(BigDecimal.ZERO) == 1 && amount.compareTo(DBAccountHandler.getBalance(originalID)) < 1)
				DBAccountHandler.Transfer(amount,routing,account,originalID);
			else
				TextPresenter.transactionstatus("Invalid amount");
		}
		catch(Exception e)
		{
			TextPresenter.transactionstatus(false);
			e.printStackTrace();
		}
		
		
	}
	public LinkedList<TransactionView> getTransactions(int ID)
	{
		return DBAccountHandler.getTransfers(ID);
	}

}
