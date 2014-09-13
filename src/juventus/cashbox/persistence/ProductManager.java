package juventus.cashbox.persistence;
import java.util.ArrayList;


public class ProductManager {
	
	private ArrayList<Product> arrProducts;
	
	public ProductManager()
	{
		arrProducts = new ArrayList<Product>();
		Product product01 = new Product("Kaugummi", 0.20);
		Product product02 = new Product("Zigaretten", 8.50);
		Product product03 = new Product("RedBull", 3.90);
		Product product04 = new Product("Wasser", 0.90);
		Product product05 = new Product("Sandwich", 5.10);
		
		AddProduct(product01);
		AddProduct(product02);
		AddProduct(product03);
		AddProduct(product04);
		AddProduct(product05);
	}
	
	public Product GetProduct(int param_Id)
	{
		if(arrProducts.size() > param_Id)
		{
			return this.arrProducts.get(param_Id);
		}
		else
		{
			return null;
		}
	}
	
	public int GetListSize()
	{
		return this.arrProducts.size();
	}
	
	public void AddProduct(Product param_Product)
	{
		arrProducts.add(param_Product);
	}
}
