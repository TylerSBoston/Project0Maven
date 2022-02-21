package Service;
import java.util.LinkedList;

import BasicClasses.*;
import Dao.*;
import org.apache.logging.log4j.*;
public class UserHandler implements MultiLayorInteractable<User> {

	private final Logger log = LogManager.getLogger(this.getClass());
	//creaters user, replaces temporary user with a DB valid user
	public User add(User RegisteredUser)
	{
		try{
			log.info("in add User");
			DBUserHandler.add(RegisteredUser);
			// for database
			//return DBUserHandler.GetUser(RegisteredUser.getUser(), RegisteredUser.getPassword());
			return get(RegisteredUser.getUser(),RegisteredUser.getPassword());
		}
		catch(Exception e) // returns previous user on DB fail
		{
			log.info("Failed to create user" + e.getMessage());
			RegisteredUser.setErrorText("Failed to create user");
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
		log.info("in get user service layer");
		return DBUserHandler.GetUser(user, password);
	}

	@Override
	public String errorText() {
		// TODO Auto-generated method stub
		return null;
	}
	public LinkedList<Customer> getCustomers()
	{
		log.info("in getCustomers service layer");
		return DBUserHandler.getCustomers();
	}
	public LinkedList<Customer> RegisterCustomers()
	{
		log.info("in RegisterCustomers  service layor");
		return DBUserHandler.RegisterCustomers();
	}

	public void setRegistration(LinkedList<Customer> customerRegistration) {
		// TODO Auto-generated method stub
		log.info("in setRegistratin service layor");
		DBUserHandler.setRegistration(customerRegistration);
		
	}
	
	
	
}
