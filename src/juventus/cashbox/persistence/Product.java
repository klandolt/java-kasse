package juventus.cashbox.persistence;

import java.math.BigDecimal;


public class Product {
	private String strName = "";
	private BigDecimal bigdecPrice = BigDecimal.ZERO.setScale(2);
	
	
	public Product(String param_Name, double param_Price)
	{
		this.strName = param_Name;
		this.bigdecPrice = BigDecimal.valueOf(param_Price);
	}
	
	public String GetName()
	{
		return this.strName;
	}
	
	public BigDecimal GetPrice()
	{
		return this.bigdecPrice;
	}
	
}
