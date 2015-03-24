/*
 * @(#)Component.java	0.0.1-SNAPSHOT
 *
 * Copyright 2009 Telefónica I+D.
 */
package com.softwaresano.examples.calculator.test.mock;

import org.jmock.Expectations;
import org.jmock.Mockery;

/**
 * <p>This class is a Mock implementation of the Component Interface. In this case this component  is
 * a simple Calculator.</p>
 *  
 * <p>Sample:</p>
 * <PRE>
 *    Component component = component = (com.softwaresano.examples.calculator.Component)com.softwaresano.examples.calculator.test.mock.Component.getInstance();
 *    System.out.println(component.add(2.0,2.0));
 * </PRE>
 *
 * @version    0.0.1-SNAPSHOT
 * @author     ${developerName}
 */

final public class Component {
    public static Object getInstance() {
	Mockery context = new Mockery();
	final com.softwaresano.examples.calculator.Component component = context
		.mock(com.softwaresano.examples.calculator.Component.class);
	// expectations
	context.checking(new Expectations() {
	    {
	    //Cuando se invoque el método add con los valores 2 y 2	
	    allowing(component).add((double) 2, (double) 2);
	    //la suma es:4
		will(returnValue((double) 4));
	    }
	});
	return component;
    }
}
