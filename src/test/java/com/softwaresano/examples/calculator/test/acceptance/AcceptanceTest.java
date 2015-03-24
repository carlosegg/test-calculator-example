package com.softwaresano.examples.calculator.test.acceptance;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import com.softwaresano.examples.calculator.Component;

@RunWith(ConcordionRunner.class)
public class AcceptanceTest {
    
    public String add(int a, int b) {
    	final Component component = new com.softwaresano.examples.calculator.impl.Component();
    	int result = 0;
    	try{
    		result = (int)component.add(a, b);
    	} catch (IllegalArgumentException illegalException){
    		return "ERROR: Los dos sumandos han de ser positivos";
    	}
    	return String.format("%d + %d = %d",a,b,result);
    }
}
