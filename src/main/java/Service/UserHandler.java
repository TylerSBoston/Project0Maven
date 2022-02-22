package Service;
import java.sql.SQLException;
import java.util.LinkedList;

import BasicClasses.*;
import Dao.*;
import Presentation.TextPresenter;

import org.apache.logging.log4j.*;
public class UserHandler implements MultiLayorInteractable<User> {

	private final Logger log = LogManager.getLogger(this.getClass());
	//creaters user, replaces temporary user with a DB valid user
	public User add(User RegisteredUser)
	{
		try{
			log.info("in add User");
			DBUserHandler.add(RegisteredUser);
			return get(RegisteredUser.getUser(),RegisteredUser.getPassword());
		}
		catch(Exception e) // returns previous user on DB fail
		{
			log.info("Failed to create user" + e.getMessage());
			RegisteredUser.setErrorText("Failed to create user");
			return null;
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
		try {
			return DBUserHandler.GetUser(user, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.warn("Failed to get Customer" + e.getMessage());
		}
		return null;
	}
	public User getEmployee(String user, String password)
	{
		log.info("in get Employee service layer");
		try {
			return DBUserHandler.GetEmployee(user, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.warn("Failed to get Employee" + e.getMessage());
		}
		return null;
	}

	@Override
	public String errorText() {
		// TODO Auto-generated method stub
		return null;
	}
	public LinkedList<Customer> getCustomers()
	{
		try {
			log.info("in getCustomers service layer");
			return DBUserHandler.getCustomers();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			TextPresenter.serviceMessage("Failed to get Customers");
			log.warn("Get Customers failed: " + e.getMessage());
			return null;
		}
	}
	public LinkedList<Customer> RegisterCustomers()
	{
		log.info("in RegisterCustomers  service layor");
		try {
			return DBUserHandler.RegisterCustomers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.warn("Failed to regester Custermers " + e.getMessage());
		}
		return null;
	}

	public void setRegistration(LinkedList<Customer> customerRegistration) {
		// TODO Auto-generated method stub
		log.info("in setRegistratin service layor");
		try {
			DBUserHandler.setRegistration(customerRegistration);
		} catch (Exception e) {
			TextPresenter.serviceMessage("Falled to update user Registration");
			log.warn("failed to set user registration " + e.getMessage());
		}
		
	}
	
	
	
}
