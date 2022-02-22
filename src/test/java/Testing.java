import org.junit.jupiter.api.*;
import Service.AccountHandler;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
import org.junit.jupiter.*;

public class Testing {

	// only tests 1 method, others require input or interaction with the DB
	AccountHandler handle = new AccountHandler();
	@Test
	public void negativeReturn()
	{
		BigDecimal negative = BigDecimal.ZERO;
		BigDecimal existing = new BigDecimal(22);
		boolean result = handle.ValidateTransfer(negative, existing);
		assertEquals(false,result);
	}
	@Test
	public void ToMuchReturn()
	{
		BigDecimal input = new BigDecimal(1000);
		BigDecimal existing = new BigDecimal(22);
		boolean result = handle.ValidateTransfer(input, existing);
		assertEquals(false,result);
	}
	@Test
	public void equalReturn()
	{
		BigDecimal input = new BigDecimal(1000);
		BigDecimal existing = new BigDecimal(1000);
		boolean result = handle.ValidateTransfer(input, existing);
		assertEquals(true,result);
	}
	@Test
	public void empty()
	{
		BigDecimal input = new BigDecimal(0);
		BigDecimal existing = new BigDecimal(0);
		boolean result = handle.ValidateTransfer(input, existing);
		assertEquals(false,result);
	}
	
}
