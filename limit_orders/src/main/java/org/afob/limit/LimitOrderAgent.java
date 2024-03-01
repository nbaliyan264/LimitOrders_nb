package org.afob.limit;

import org.afob.execution.ExecutionClient;
import org.afob.execution.ExecutionClient.ExecutionException;
import org.afob.prices.PriceListener;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class LimitOrderAgent implements PriceListener {

	ExecutionClient execution;
    Map<String, Order> mapOrder = new HashMap<>();
	
    public LimitOrderAgent(final ExecutionClient ec) {
    	execution = ec;
    }
 
    @Override
    public void priceTick(String productId, BigDecimal price) {
    	Order order = mapOrder.get(productId);
    	
    	if(order.getFlag().equals("buy")) {
    		if(order.getLimit() <= price.doubleValue()) {
    			try {
					execution.buy(order.getProductId(), order.getAmount());
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
        	}
    	} else {
    		if(order.getLimit() >= price.doubleValue()) {
    			try {
					execution.sell(order.getProductId(), order.getAmount());
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
        	}
    	}
    }
    
    public void addOrder(String flag, String productId, int amount, int limit) throws ExecutionException {
    	Order order = new Order();
    	
    	order.setAmount(amount);
    	order.setProductId(productId);
    	order.setFlag(flag);
    	order.setLimit(limit);   	    	
    	
    	mapOrder.put(productId, order);
	
    }
 
}
