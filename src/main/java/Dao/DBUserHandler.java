package Dao;
import BasicClasses.*;

// this is what i want to do first but requires DB and logging stuff started...

public class DBUserHandler {
	
	private static String ConnectionString = "Temp Value";
	
	public static void add(User newUser)
	{
		User dbNewUser = new User(newUser.getUser(),newUser.getPassword(),newUser.getName(),(int)Math.random()*1000000, newUser.getAccountType());
		// waiting for DB stuff
	}

	
	public static void update(User item) {
		// TODO Auto-generated method stub
		
	}

	
	public static void deactivate(int ID) {
		// TODO Auto-generated method stub
		
	}

	
	public static User getUser(int ID) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String errorText() {
		// TODO Auto-generated method stub
		return null;
	}
	
	// get user for logging in
	public static User GetUser(String user, String password)
	{
		// DB call info
		return null;
	}
	
	

}
