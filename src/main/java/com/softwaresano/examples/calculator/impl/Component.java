/**
 * @(#)Component.java 0.0.1-SNAPSHOT
 *
 * Copyright 2009 Telefónica I+D.
 */
package com.softwaresano.examples.calculator.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import static java.text.MessageFormat.format;

/**
 * <p>This class implements a Component Interface. In this case this
 * component  is a simple Calculator.</p>
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
public final class Component implements com.softwaresano.examples.calculator.Component {

    /**
     *  The logger. A new logger <code>com.softwaresano.examples.calculator.Component</code> is
     *  created to add the logs of this Component
     */
    private static final  Log LOG = LogFactory.getLog(Component.class);

    /**
     * This method calculates the added of two numbers.
     * @param a first operand
     * @param b second operand
     * @return The added of  <TT>a</TT> and <TT>b</TT>
     * @throws IllegalArgumentException
     */
    @Override
    public double add(final double a, final double b) throws IllegalArgumentException {
    	if ( (a<0) || (b<0)){
    		throw new IllegalArgumentException("a y b no pueden ser números negativos");
    	}
        if (LOG.isDebugEnabled()) {
            LOG.debug(format("Calculando la suma de [{0}] y [{1}]",
                             new Object[]{a, b}));
        }
        final double result = a + b;
        if (LOG.isDebugEnabled()) {
            LOG.debug(format("Resultado = {0}", new Object[]{result}));
        }
        return result;
    }
}
