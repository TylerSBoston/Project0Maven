package BasicClasses;

public class Customer extends User {

	public Customer(String user, String password) {
		// TODO Auto-generated constructor stub
		super(user,password);
	}
	public Customer()
	{
		
	}
	
	private int active = 0;
	public int isActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	
	

}
