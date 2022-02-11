package Dao;

import java.math.BigDecimal;
import java.util.HashSet;

import BasicClasses.Account;

public class DBAccountHandler {
	private static String ConnectionString = "Temp Value";
	
	public static void add(Account newAccount)
	{
		
		// waiting for DB stuff
	}

	
	public static void update(Account updateAccount) {
		// TODO Auto-generated method stub
		
	}

	
	public static void deactivate(int ID) {
		// TODO Auto-generated method stub
		
	}

	
	public static Account getUser(int ID) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public static String errorText() {
		// TODO Auto-generated method stub
		return null;
	}
	
	// needs user ID
	public static HashSet<Account> getAccounts(int UserID)
	{
		HashSet<Account> accounts = new HashSet<Account>();
		
		
		return accounts;
	}
	
	public static String[] getAccountStatements(int ID)
	{
		return null;
	}
	
	public static void Transfer(BigDecimal amount, String routing, String account, int origin)
	{
		//DB pending
	}
	
}
