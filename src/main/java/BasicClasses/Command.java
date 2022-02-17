package BasicClasses;



public class Command {

	//return type from the presentation layer, meant to only go 1 way. 
	public enum command{
		getAccount,
		logoff,
		login,
		getBankAccounts,
		transferMoney,
		createBankAccount,
		approveBankAccount,
		none,
		createUserAccount,
		createCustomerAccount,
		deposit,
		withdraw,
		transfer,
		getStatements,
		registerCustomer,
		viewAllAccounts,
		getlogs,
		viewCustomers,
		employeeLogin,
		customerLogin,
		exit,
		transferGetAccounts
	}
	int returnID = 0;
	// the logged in user
	User user;
	// usually an account but can return other stuff
	Object returnObject;
	command output = command.none;
	String account;
	String routing;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getRouting() {
		return routing;
	}
	public void setRouting(String routing) {
		this.routing = routing;
	}
	
	public int getReturnID() {
		return returnID;
	}
	public void setReturnID(int returnID) {
		this.returnID = returnID;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Object getReturnObject() {
		return returnObject;
	}
	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}
	public command getOutput() {
		return output;
	}
	public void setOutput(command output) {
		this.output = output;
	}
	
	
	
	
	
}
