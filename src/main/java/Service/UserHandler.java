package Service;
import java.util.LinkedList;

import BasicClasses.*;
import Dao.*;

public class UserHandler implements MultiLayorInteractable<User> {

	
	//creaters user, replaces temporary user with a DB valid user
	public User add(User RegisteredUser)
	{
		try{
		
			DBUserHandler.add(RegisteredUser);
			// for database
			//return DBUserHandler.GetUser(RegisteredUser.getUser(), RegisteredUser.getPassword());
			return get(RegisteredUser.getUser(),RegisteredUser.getPassword());
		}
		catch(Exception e) // returns previous user on DB fail
		{
			return RegisteredUser;
		}
	}

	@Override
	public void update(User item) {
		DBUserHandler.update(item);
	}
	

	@Override
	public void deactivate(int ID) {
		DBUserHandler.deactivate(ID);
	}

	@Override
	public User get(int ID) {
		// TODO Auto-generated method stub
		return null;
	}
	public User get(String user, String password)
	{
		return DBUserHandler.GetUser(user, password);
	}

	@Override
	public String errorText() {
		// TODO Auto-generated method stub
		return null;
	}
	public LinkedList<Customer> getCustomers()
	{
		return DBUserHandler.getCustomers();
	}
	public LinkedList<Customer> RegisterCustomers()
	{
		return DBUserHandler.RegesterCustomers();
	}

	public void setRegistration(LinkedList<Customer> customerRegistration) {
		// TODO Auto-generated method stub
		DBUserHandler.setRegistration(customerRegistration);
		
	}
	
	
	
}
