package Dao;
import java.sql.*;
import java.util.LinkedList;

import org.postgresql.util.*;

import BasicClasses.*;

// this is what i want to do first but requires DB and logging stuff started...

public class DBUserHandler {
	

	
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
		
		try {
			
			
			Statement st = DBConnection.getConnection().createStatement();
			String query = "call \"AddCustomer\"('"+ newUser.getFirstName() + "','"
			+ newUser.getLastName() + "','"
			+ newUser.getEmail() + "','"
			+ newUser.getPhone() + "','" 
			+ newUser.getUser() + "','"
			+ newUser.getPassword() + "' ); ";
			
			st.execute(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public static User GetUser(String user, String passWord)
	{
		Customer login = new Customer();	
		try {	
			String query = "call \"CustomerLogin\"(?,?,?,?);";
			CallableStatement st =  DBConnection.getConnection().prepareCall(query);
			
			st.setString(1, user);
			st.setString(2, passWord);
			st.setInt(3, 0);
			st.setInt(4, 0);
			ResultSet results = null;
			try {
				results = st.executeQuery();
			}
			catch(PSQLException e){
				
			}
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return login;
	}
	
	public static LinkedList<Customer> RegesterCustomers()
	{
		LinkedList<Customer> customers = new LinkedList<Customer>();
		
		String query = "select * from \"Customers\" inner join \"Users\" on \"Customers\".\"userID\" = \"Users\".\"ID\" where \"Customers\".active = 0 ;";
		try {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return customers;
	}
	public static LinkedList<Customer> getCustomers()
	{
		LinkedList<Customer> customers = new LinkedList<Customer>();
		
		String query = "select * from \"Customers\" inner join \"Users\" on \"Customers\".\"userID\" = \"Users\".\"ID\"";
		try {
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
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return customers;
	}


	public static void setRegistration(LinkedList<Customer> customerRegistration) {
		// TODO Auto-generated method stub
		String query = "call set_registration(?,?);";
		try {
			PreparedStatement st = DBConnection.getConnection().prepareStatement(query);
			for(Customer c : customerRegistration)
			{
				st.setInt(1, c.getUserID());
				st.setInt(2, c.isActive());
				st.execute();
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
