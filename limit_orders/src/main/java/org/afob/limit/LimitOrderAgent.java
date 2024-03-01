package org.afob.limit;

import org.afob.execution.ExecutionClient;
import org.afob.execution.ExecutionClient.ExecutionException;
import org.afob.prices.PriceListener;

import java.math.BigDecimal;

public class LimitOrderAgent implements PriceListener {

    public LimitOrderAgent(final ExecutionClient ec) {
    }


    @Override
    public void priceTick(String productId, BigDecimal price) {

    }
    
    public void addOrder(String flag, String productId, int amount, int limit, ExecutionClient ec) throws ExecutionException {
    	if(flag.equals("buy")) {
    		if(limit <= ProductStock.getCurrentStockPrice(productId)) {
    			ec.buy(productId, amount);
        	}
    	} else {
    		if(limit >= ProductStock.getCurrentStockPrice(productId)) {
    			ec.sell(productId, amount);
        	}
    	}	
    }

}
