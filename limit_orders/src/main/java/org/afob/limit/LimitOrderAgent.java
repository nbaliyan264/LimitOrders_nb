package org.afob.limit;

import org.afob.execution.ExecutionClient;
import org.afob.execution.ExecutionClient.ExecutionException;
import org.afob.prices.PriceListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LimitOrderAgent implements PriceListener {

	String prodId;
	BigDecimal currentPrice;
	ExecutionClient execution;
	
    public LimitOrderAgent(final ExecutionClient ec) {
    	execution = ec;
    }
 
    @Override
    public void priceTick(String productId, BigDecimal price) {
    	prodId = productId;
    	currentPrice = price;
    }
    
	List<Order> orders = new ArrayList<>();
	
    public List<Order> addOrder(String flag, String productId, int amount, int limit) throws ExecutionException {
    	Order order = new Order();
    	
    	order.setAmount(amount);
    	order.setProductId(productId);
    	order.setFlag(flag);
    	order.setLimit(limit);
    	
    	orders.add(order);
    	
		return orders;	
    }
    
    public void executeOrder(Order order) throws ExecutionException {    	
    	if(order.getFlag().equals("buy")) {
    		if(order.getLimit() <= currentPrice.doubleValue()) {
    			execution.buy(order.getProductId(), order.getAmount());
        	}
    	} else {
    		if(order.getLimit() >= currentPrice.doubleValue()) {
    			execution.sell(order.getProductId(), order.getAmount());
        	}
    	}
    }

}
