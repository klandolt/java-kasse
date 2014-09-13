package juventus.cashbox.persistence;

import java.math.BigDecimal;


public class Product {
	private String strName = "";
	//private double doubPrice = 0.0;
	private BigDecimal bigdecPrice;
	
	
	public Product(String param_Name, double param_Price)
	{
		this.strName = param_Name;
		//this.doubPrice = param_Price;
		this.bigdecPrice = new BigDecimal(param_Price);
	}
	
	public String GetName()
	{
		return this.strName;
	}
	
	public BigDecimal GetPrice()
	{
		//return this.doubPrice;
		return this.bigdecPrice;
	}
	
}
