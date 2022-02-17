package BasicClasses;

import java.math.BigDecimal;

// only gets transactions to be viewed
public class TransactionView {
	String senderName;
	String recieverName;
	BigDecimal amount;
	String date;
	String senderAccountName;
	String recieverAccountName;
	

	public TransactionView(String sName,String rName, String sAName,String rAName, String date, BigDecimal money)
	{
		senderName = sName;
		recieverName = rName;
		senderAccountName = sAName;
		recieverAccountName = rAName;
		this.date = date;
		amount = money;
	}
	
	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getRecieverName() {
		return recieverName;
	}

	public void setRecieverName(String recieverName) {
		this.recieverName = recieverName;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSenderAccountName() {
		return senderAccountName;
	}

	public void setSenderAccountName(String senderAccountName) {
		this.senderAccountName = senderAccountName;
	}

	public String getRecieverAccountName() {
		return recieverAccountName;
	}

	public void setRecieverAccountName(String recieverAccountName) {
		this.recieverAccountName = recieverAccountName;
	}

	
}
