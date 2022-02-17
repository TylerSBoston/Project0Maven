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
	LinkedList<Account> accounts = new LinkedList<Account>();
	// password only used on creating accounts
	private String password = "";
	private String user = "";
	private String errorText = "";
	private int userID = 0;
	private String firstName = "";
	private String lastName = "";
	private String email = "";
	private String phone = "";
	private AccountType accountType;
	
	// for new account if its an employee
	public User()
	{
		
	}
	
	public User(String user, String pass,  String  firstName, String lastName,String email, String phone, AccountType type)
	{
		this.user = user;
		password = pass;
		accountType = type;
		nameInfo(firstName,lastName,email,phone);
		
	}
	// for new account
	public User(String user, String pass,  String  firstName, String lastName,String email, String phone)
	{
		this.user = user;
		password = pass;
		accountType = AccountType.Customer;
		nameInfo(firstName,lastName,email,phone);
	}
	
	// for login
	public User(String user, String pass)
	{
		this.user = user;
		this.password = pass;
	}
	
	//DB pulled Account
	public User(String  firstName, String lastName,String email, String phone, int ID, AccountType type)
	{
		nameInfo(firstName,lastName,email,phone);
		userID = ID;
		accountType = type;
	}
	//for creating new accounts, this will be removed and done on the DB later
	public User(String user, String pass, String  firstName, String lastName,String email, String phone, int ID, AccountType type)
	{
		this.user = user;
		password = pass;
		accountType = type;
		nameInfo(firstName,lastName,email,phone);
		userID = ID;
	}
	
	private void nameInfo(String  firstName, String lastName,String email, String phone)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
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
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
	public LinkedList<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(LinkedList<Account> accounts) {
		this.accounts = accounts;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	

}
