package Dao;
import java.sql.*;
import java.util.LinkedList;

import org.postgresql.util.*;

import BasicClasses.*;
import org.apache.logging.log4j.*;
// this is what i want to do first but requires DB and logging stuff started...

public class DBUserHandler {
	private final static Logger log = LogManager.getLogger(DBUserHandler.class);

	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void add(User newUser)
	{
		// waiting for DB stuff
		log.info("in add User Dao Layer");
		try {
			
			String query = "call \"AddCustomer\"(?,?,?,?,?,?);";
			
			PreparedStatement st = DBConnection.getConnection().prepareStatement(query);
			st.setString(1, newUser.getFirstName());
			st.setString(2, newUser.getLastName());
			st.setString(3, newUser.getEmail());
			st.setString(4, newUser.getPhone());
			st.setString(5, newUser.getUser());
			st.setString(6, newUser.getPassword());
			
			
			st.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.warn("Dao layer, Failed to add user" + e.getMessage());
		} 
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
	public static User GetUser(String user, String passWord) throws Exception
	{
		log.info("in GetUser Dao Layer");
		Customer login = new Customer();	
	
		String query = "call \"CustomerLogin\"(?,?,?,?);";
		CallableStatement st =  DBConnection.getConnection().prepareCall(query);
		
		st.setString(1, user);
		st.setString(2, passWord);
		st.setInt(3, 0);
		st.setInt(4, 0);
		ResultSet results = null;

		results = st.executeQuery();

		if(results != null)
		{
			results.next();
			login.setUser(results.getString(1));
			login.setUserID(results.getInt("customer_id"));
			login.setActive(results.getInt("status"));
		}
		else
		{
			login.setErrorText("Invalid Username password combination");
		}

		return login;
	}
	
	public static User GetEmployee(String user, String passWord) throws Exception
	{
		log.info("in GetUser Dao Layer");
		Employee login = new Employee();	

		String query = "call \"EmployeeLogin\"(?,?,?);";
		CallableStatement st =  DBConnection.getConnection().prepareCall(query);
		
		st.setString(1, user);
		st.setString(2, passWord);
		st.setInt(3, 0);
		ResultSet results = null;

		results = st.executeQuery();

		if(results != null)
		{
			results.next();
			login.setUser(results.getString(1));
			login.setUserID(results.getInt("employee_id"));
		}
		else
		{
			login.setErrorText("Invalid Username password combination");
		}

		return login;
	}
	
	public static LinkedList<Customer> RegisterCustomers() throws Exception
	{
		log.info("in Regester Customers Dao Layer");
		LinkedList<Customer> customers = new LinkedList<Customer>();
		
		// gets a list of unregistered customers to register
		String query = "select * from \"Customers\" inner join \"Users\" on \"Customers\".\"userID\" = \"Users\".\"ID\" where \"Customers\".active = 0 ;";

		Statement st = DBConnection.getConnection().createStatement();
		ResultSet results = st.executeQuery(query);
		while(results.next())
		{
			Customer c = new Customer();
			c.setUserID(results.getInt(1));
			c.setFirstName(results.getString(5));
			c.setLastName(results.getString(6));
			c.setEmail(results.getString(7));
			c.setPhone(results.getString(8));
			c.setActive(0);
			customers.add(c);
		}

		
		
		return customers;
	}
	public static LinkedList<Customer> getCustomers() throws Exception
	{
		log.info("in getCustomers Dao Layer");
		LinkedList<Customer> customers = new LinkedList<Customer>();
		
		String query = "select * from \"Customers\" inner join \"Users\" on \"Customers\".\"userID\" = \"Users\".\"ID\" where \"Customers\".active = 1";

		Statement st = DBConnection.getConnection().createStatement();
		ResultSet results = st.executeQuery(query);
		while(results.next())
		{
			Customer c = new Customer();
			c.setUserID(results.getInt(1));
			c.setFirstName(results.getString(5));
			c.setLastName(results.getString(6));
			c.setEmail(results.getString(7));
			c.setPhone(results.getString(8));
			c.setActive(0);
			customers.add(c);
			
		}
	
		return customers;
	}


	public static void setRegistration(LinkedList<Customer> customerRegistration) throws Exception {
		// TODO Auto-generated method stub
		log.info("in Customer Registration Dao Layer");
		String query = "call set_registration(?,?);";
		PreparedStatement st = DBConnection.getConnection().prepareStatement(query);
		for(Customer c : customerRegistration)
		{
			st.setInt(1, c.getUserID());
			st.setInt(2, c.isActive());
			st.execute();
		}

	}
	
	

}
