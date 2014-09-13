package juventus.cashbox.persistence;
import java.math.BigDecimal;
import java.util.ArrayList;


public class Order {
	private ArrayList<Product> arrOrder;
	//private double doubTotal;
	private BigDecimal bigdecTotal;
	
	
	public Order()
	{
		this.arrOrder = new ArrayList<Product>();
		//this.doubTotal = 0;
		this.bigdecTotal = new BigDecimal(0.0);
		
	}
	
	public void OrderProduct(Product param_Product)
	{
		this.arrOrder.add(param_Product);
	}
	
	public ArrayList<Product> GetOrder()
	{
		return this.arrOrder;
	}
	
	public Product GetOrderedProduct(int param_Id)
	{
		return this.arrOrder.get(param_Id);
	}
	
	public String GetTotal()
	{
		//this.doubTotal = 0;
		this.bigdecTotal.setScale(0);
		
		for(int i=0; this.arrOrder.size()>i ; i++)
		{
			//this.doubTotal += arrOrder.get(i).GetPrice();
			this.bigdecTotal.add(arrOrder.get(i).GetPrice());
		}
		
		
		return	String.format("%.2f", this.bigdecTotal);
	}
	
	public int GetProductCount(String param_Name)
	{
		int count = 0;
		for(int i=0; this.arrOrder.size()>i ; i++)
		{
			if(arrOrder.get(i).GetName().contains(param_Name))
			{
				count ++;
			}
		}
		return count;
	}
	
	public void CleanOrder()
	{
		this.arrOrder.clear();
	}
	
	public void RemoveProduct(String param_Name)
	{
		for(int i=0; this.arrOrder.size()>i ; i++)
		{
			if(this.arrOrder.get(i).GetName() == param_Name)
			{
				this.arrOrder.remove(i);
				break;
			}
		}
		
	}

}
