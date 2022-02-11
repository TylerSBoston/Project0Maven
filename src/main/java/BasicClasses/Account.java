package BasicClasses;

import java.math.*;
import java.util.*;

public class Account {

	BigDecimal balance = new BigDecimal(0);
	// strings for leading 0's
	String accountNumber = "";
	String routingNumber = "";
	String accountName = "";
	boolean approved;
	

	// DB ID
	int ID = 0;
	int UserID = 0;
	// To transfer error messages between layers
	String errorText = "";
	
	public enum Type{
		checkings,
		savings,
		student,
		investment
	}
	Type accountType;
	
	// user just created these accounts/ accounts awaiting approval
	public Account(int userID, Type type)
	{
		this.UserID = userID;
		accountType = type;
		accountName = type + " account";
		approved = false;
	}
	public Account(int userID, Type type, String name)
	{
		this.UserID = userID;
		accountType = type;
		accountName = name;
		approved = false;
	}
	public Account(int userID, Type type, BigDecimal deposit)
	{
		this.UserID = userID;
		balance = deposit;
		accountType = type;
		accountName = type + " account";
		approved = false;
	}
	public Account(int userID, Type type, BigDecimal deposit, String name)
	{
		this.UserID = userID;
		balance = deposit;
		accountType = type;
		accountName = name;
		approved = false;
	}
	// for existing accounts
	public Account(int ID, String accountNumber, String routingNumber, BigDecimal amount, Type type)
	{
		this.ID = ID;
		this.accountNumber = accountNumber;
		this.routingNumber = routingNumber;
		balance = amount;
		accountType = type;
		approved = true;
	}
	// for errors form DB/dao
	public Account(String error)
	{
		errorText = error;
	}


	public void deposit(BigDecimal money)
	{
		balance.add(money);
	}
	
	// check for validity done at service layer, custom exception to be added
	public BigDecimal withdraw(BigDecimal amount)
	{
		balance.subtract(amount);
		return amount;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}
	public String getRoutingNumber() {
		return routingNumber;
	}
	public int getID() {
		return ID;
	}
	public String getErrorText() {
		return errorText;
	}
	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
	public Type getAccountType() {
		return accountType;
	}
	public void setAccountType(Type accountType) {
		this.accountType = accountType;
	}
	
	
		
}
