package com.dreamdom.tutorials.shoppingcart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import android.content.res.Resources;

public class ShoppingCartHelper {
	
	public static final String PRODUCT_INDEX = "PRODUCT_INDEX";
	
	private static List<Product> catalog;
	private static Map<Product, ShoppingCartEntry> cartMap = new HashMap<Product, ShoppingCartEntry>();
	
	public static List<Product> getCatalog(Resources res){
		if(catalog == null) {
			catalog = new Vector<Product>();
			catalog.add(new Product("Dead or Alive", res
					.getDrawable(R.drawable.deadoralive),
					"Dead or Alive by Tom Clancy with Grant Blackwood", 29.99));
			catalog.add(new Product("Switch", res
					.getDrawable(R.drawable.switchbook),
					"Switch by Chip Heath and Dan Heath", 24.99));
			catalog.add(new Product("Watchmen", res
					.getDrawable(R.drawable.watchmen),
					"Watchmen by Alan Moore and Dave Gibbons", 14.99));
		}
		
		return catalog;
	}
	
	public static void setQuantity(Product product, int quantity) {
		// Get the current cart entry
		ShoppingCartEntry curEntry = cartMap.get(product);
		
		// If the quantity is zero or less, remove the products
		if(quantity <= 0) {
			if(curEntry != null)
				removeProduct(product);
			return;
		}
		
		// If a current cart entry doesn't exist, create one
		if(curEntry == null) {
			curEntry = new ShoppingCartEntry(product, quantity);
			cartMap.put(product, curEntry);
			return;
		}
		
		// Update the quantity
		curEntry.setQuantity(quantity);
	}
	
	public static int getProductQuantity(Product product) {
		// Get the current cart entry
		ShoppingCartEntry curEntry = cartMap.get(product);
		
		if(curEntry != null)
			return curEntry.getQuantity();
		
		return 0;
	}
	
	public static void removeProduct(Product product) {
		cartMap.remove(product);
	}
	
	public static List<Product> getCartList() {
		List<Product> cartList = new Vector<Product>(cartMap.keySet().size());
		for(Product p : cartMap.keySet()) {
			cartList.add(p);
		}
		
		return cartList;
	}
	

}
