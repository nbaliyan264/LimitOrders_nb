package org.afob.limit;

public class ProductStock {
	String productId;
	static Double currentStockprice;
	
	public static Double getCurrentStockPrice(String productId) {
		return currentStockprice;
	}
}
