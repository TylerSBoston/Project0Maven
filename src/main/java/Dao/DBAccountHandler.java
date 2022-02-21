package Dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.HashSet;
import java.util.LinkedList;

import org.postgresql.util.PSQLException;

import BasicClasses.*;
import org.apache.logging.log4j.*;
public class DBAccountHandler {
	private final static Logger log = LogManager.getLogger(DBAccountHandler.class);
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void add(Account newAccount)
	{
		log.info(" in add Account Dao");
		
		/// REplace with prepared statement later***************************************
		try {
			
			String query =  "call \"createAccount\"(?,?,?,?);";
			
			PreparedStatement st = DBConnection.getConnection().prepareStatement(query);
			st.setInt(1, newAccount.getUserID());
			st.setInt(2, newAccount.getAccountType().ordinal());
			st.setBigDecimal(3, newAccount.getBalance());
			st.setString(4,newAccount.getAccountName());
			
			
			
			st.execute();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.warn("Failed to create account " + e.getMessage());
		} 

		// waiting for DB stuff
	}

	
	public static void update(Account updateAccount) {
		// TODO Auto-generated method stub
		
	}

	
	public static void deactivate(int ID) {
		// TODO Auto-generated method stub
		
	}

	
	public static Account getUser(int ID) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public static String errorText() {
		// TODO Auto-generated method stub
		return null;
	}
	
	// needs user ID
	public static LinkedList<Account> getAccounts(int userID)
	{
		log.info(" in getAccounts Service Layer");
		LinkedList<Account> accounts = new LinkedList<Account>();
		
		String query =  "select \"balance\",\"accountName\",\"routingNumber\",\"accountNumber\",\"accountTypeID\",\"ID\" "
					+ "	 from \"Accounts\" "
					+ "	 where \"Accounts\".\"customerID\" = "+ userID + ";";
		try
		{
			Statement st =  DBConnection.getConnection().createStatement();

			ResultSet results = null;			
			try {
				results = st.executeQuery(query);
			}
			catch(PSQLException e){
				
			}
			if(results != null)
			{
				while(results.next())
				{
					Account account = new Account(userID,results.getString(4),results.getString(3),results.getBigDecimal(1),results.getString(2),results.getInt(5),results.getInt(6));
					accounts.add(account);
				}

			}
			return accounts;
		}
		catch(Exception e) 
		{
			log.warn("Failed to Get accounts " + e.getMessage());
		}
		return accounts;
	}
	
	public static void Transfer(BigDecimal amount, String routing, String account, int origin)
	{
		log.info(" in Transfer Dao");
		try {
			String query = "call \"addtransaction\" (?,?,?,?);";
			
			PreparedStatement st = DBConnection.getConnection().prepareStatement(query);
			st.setBigDecimal(1, amount);
			st.setInt(2, origin);
			st.setString(3, routing);
			st.setString(4, account);
			st.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.warn("Transfer Failed" + e.getMessage());
		}	
	}
	public static BigDecimal getBalance(int ID)
	{
		log.info(" in getBalance Dao");
		try {
			String query = "call \"getBalance\" (?,?);";
			CallableStatement st =  DBConnection.getConnection().prepareCall(query);
			st.setBigDecimal(1, null);
			st.setInt(2, ID);
			
			ResultSet results = st.executeQuery();
			BigDecimal returnValue;
			if(results.next())
			{
				returnValue = results.getBigDecimal(1);
				returnValue.setScale(2);
				return returnValue;
			}
			else
			{
				return BigDecimal.ZERO;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.warn("Failed to get balance " + e.getMessage());
		}
		return BigDecimal.ZERO;
	}
	
	public static LinkedList<TransactionView> getTransfers(int ID)
	{
		log.info(" in getTransfers Dao");
		LinkedList<TransactionView> transactions = new LinkedList<TransactionView>();
		String query = "";
		try {
			
			if(ID > 0)
			{
				query = "Select sending, sending_user, recieving,recieving_user, amount, date from view_transactions where sender_id = " + ID + "or recieving_id = " + ID + ";";
			}
			// get all if employee requests transactions
			else
			{
				query = "Select sending, sending_user, recieving,recieving_user, amount, date from view_transactions;";
			}
			
			
			Statement st =  DBConnection.getConnection().createStatement();
			ResultSet results = st.executeQuery(query);
			
			while(results.next())
			{
				transactions.add(new TransactionView(results.getString(2),results.getString(4),results.getString(1),results.getString(3),results.getString(6),results.getBigDecimal(5)));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.warn("Failed to get transfers " + e.getMessage());
		}

		
		return transactions;
	}
	
	
	
}
