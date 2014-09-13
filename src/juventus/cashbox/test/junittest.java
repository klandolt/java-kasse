package juventus.cashbox.test;


import juventus.cashbox.*;
import juventus.cashbox.persistence.Order;
import juventus.cashbox.persistence.ProductManager;
import junit.framework.TestCase;


public class junittest extends TestCase{
	
	public void testbetrag(){			//Test Methoden müssen mit test beginnen!!!
		
		ProductManager testmanger = new ProductManager();
		
		Order testorder = new Order();
		
		testorder.OrderProduct(testmanger.GetProduct(0));
		testorder.OrderProduct(testmanger.GetProduct(0));
		testorder.OrderProduct(testmanger.GetProduct(0));
		testorder.OrderProduct(testmanger.GetProduct(0));
		testorder.OrderProduct(testmanger.GetProduct(0));
		
		System.out.println(testorder.GetTotal());
		
		assertEquals("Falscher Betrage zusammen Rechnung", "1,00", testorder.GetTotal());
	}
	
	

}
