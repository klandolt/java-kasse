package juventus.cashbox.business;
import java.util.ArrayList;

import juventus.cashbox.persistence.Order;

public class CashBox {
	private ArrayList<Order> arrOrder;
	private double doubCashBoxMoney;
	
	public CashBox()
	{
		arrOrder = new ArrayList<Order>();
		this.doubCashBoxMoney = 100;
	}
	
	public CashBox(double param_doubCashBoxMoney)
	{
		arrOrder = new ArrayList<Order>();
		this.doubCashBoxMoney = param_doubCashBoxMoney;
	}
	
	
	public void NewOrder()
	{
		Order order = new Order();
		arrOrder.add(order);
	}
	
	public void SetCashBoxMoney(double param_Money)
	{
		this.doubCashBoxMoney = param_Money;
	}
	
	public void AddCashBoxMoney(double param_Money)
	{
		this.doubCashBoxMoney += param_Money;
	}
	
	public double GetCashBoxMoney()
	{
		return this.doubCashBoxMoney;
	}
}
