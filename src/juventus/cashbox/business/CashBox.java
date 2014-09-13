package juventus.cashbox.business;
import java.math.BigDecimal;
import java.util.ArrayList;

import juventus.cashbox.persistence.Order;

public class CashBox {
	private ArrayList<Order> arrOrder;
	private BigDecimal bigdecCashBoxMoney;
	
	public CashBox()
	{
		arrOrder = new ArrayList<Order>();
		
		
		this.bigdecCashBoxMoney = BigDecimal.valueOf(100.00).setScale(2);
		
	}
	
	public CashBox(double param_doubCashBoxMoney)
	{
		arrOrder = new ArrayList<Order>();
		
		this.bigdecCashBoxMoney = BigDecimal.valueOf(param_doubCashBoxMoney);
	}
	
	
	public void NewOrder()
	{
		Order order = new Order();
		arrOrder.add(order);
	}
	
	public void SetCashBoxMoney(double param_Money)
	{
				
		this.bigdecCashBoxMoney = BigDecimal.valueOf(param_Money);
	}
	
	public void AddCashBoxMoney(double param_Money)
	{
				
		this.bigdecCashBoxMoney = BigDecimal.valueOf(bigdecCashBoxMoney.doubleValue() + param_Money);
	}
	
	public BigDecimal GetCashBoxMoney()
	{
		
		return this.bigdecCashBoxMoney;
	}
}
