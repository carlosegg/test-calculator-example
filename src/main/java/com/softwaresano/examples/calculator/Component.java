/**
 * @(#)Component.java  0.0.1-SNAPSHOT
 *
 * Copyright 2009 Telefónica I+D.
 */
package com.softwaresano.examples.calculator;

/**
 * <p>This Component interface represents a Calculator.</p>
 *
 * <p>Sample:</p>
 * <PRE>
 *    Component component = new com.softwaresano.examples.calculator.Component();
 *    System.out.println(component.add(5.0,3.0));
 * </PRE>
 *
 * @version    0.0.1-SNAPSHOT
 * @author     ${developerName}
 */
public interface Component {

    /**
     * This method calculates the added of two numbers.
     * @param a first operand
     * @param b second operand
     * @return The added of  <TT>a</TT> and <TT>b</TT>
     * @throws IllegalArgumentException
     */
     double add(double a, double b) throws IllegalArgumentException;
}

