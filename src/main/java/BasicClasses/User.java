package BasicClasses;

import java.util.*;

public class User {
	
	
	// will be removed once rest of skeleton is done, employee/customer classes added for future expansion.
	public enum AccountType{
		Customer,
		Employee,
		Admin,
		User
	}
	// to keep track of state of user, Might not be needed with command
	HashSet<Account> accounts = new HashSet<Account>();
	// password only used on creating accounts
	private String password = "";
	private String user = "";
	private String errorText = "";
	private int userID = 0;
	private String name = "";
	private AccountType accountType;
	
	// for new account if its an employee
	public User()
	{
		
	}
	
	public User(String user, String pass,  String  name, AccountType type)
	{
		this.user = user;
		password = pass;
		accountType = type;
		this.name = name;
	}
	// for new account
	public User(String user, String pass,  String  name)
	{
		this.user = user;
		password = pass;
		accountType = AccountType.Customer;
		this.name = name;
	}
	
	// for login
	public User(String user, String pass)
	{
		this.user = user;
		this.password = pass;
	}
	
	//DB pulled Account
	public User(String name, int ID, AccountType type)
	{
		this.name = name;
		userID = ID;
		accountType = type;
	}
	//for creating new accounts, this will be removed and done on the DB later
	public User(String user, String pass, String name, int ID, AccountType type)
	{
		this.user = user;
		password = pass;
		accountType = type;
		this.name = name;
		userID = ID;
	}

	public String getPassword() {
		return password;
	}
	// kept for recovery
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
	public HashSet<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(HashSet<Account> accounts) {
		this.accounts = accounts;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	

}
