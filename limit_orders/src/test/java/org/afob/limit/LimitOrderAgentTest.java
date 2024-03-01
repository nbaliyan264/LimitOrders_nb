package org.afob.limit;

import java.math.BigDecimal;

import org.afob.execution.ExecutionClient;
import org.afob.execution.ExecutionClient.ExecutionException;
import org.junit.Test;

public class LimitOrderAgentTest {
	
    @Test
    public void addTestsHere() throws ExecutionException {
    	ExecutionClient execution = new ExecutionClient();
    	LimitOrderAgent agent = new LimitOrderAgent(execution);
    	
    	agent.addOrder("buy", "1234", 1000, 100);
    	agent.priceTick("1234", BigDecimal.valueOf(1000));
    }
}