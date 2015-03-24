/**
 * @(#)Startup.java 0.0.1-SNAPSHOT
 *
 * Copyright 2009 Telefónica I+D.
 */
package com.softwaresano.examples.calculator.main;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import static java.text.MessageFormat.format;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.softwaresano.examples.calculator.Component;

/**
 * <p>This class launch a sample of use of the
 * com.softwaresano.examples.calculator.Component.
 * This class parses the command line and executes the appropiate method.</p>
 * <p>
 *    Sample: To run (it invokes <code>start</code> method) the
 * Component waiting 5000ms.
 * </p>
 * <PRE>
 *    java -jar test-calculator.jar --debug 5000 start
 *   or
 *    java -cp ... com.softwaresano.examples.calculator.main.Bootstrap --debug 5000 start
 * </PRE>
 * @version    0.0.1-SNAPSHOT
 * @author     ${developerName}
 */
public final class Bootstrap {

    /**
     * The logger. A new logger
     * <code>com.softwaresano.examples.calculator.Component</code> interface is created
     * to add the logs of this Component
     */
    private static final Log LOG = LogFactory.getLog(Bootstrap.class);
    /**
     * A new logger
     * <code>com.softwaresano.examples.calculator.Component</code>  is created
     * to print messages of this Component. This logger replaces
     * <code>System.out.println</code>
     */
    private static final Log CONSOLE = LogFactory.getLog("com.softwaresano.examples.calculator.console");
    /** The start method.
     */
    private static final String START_METHOD = "start";
    /** The stop method.
     */
    private static final String STOP_METHOD = "stop";
    /** The method to be executed. By default is <code>START_METHOD</code>.
     */
    private String method = START_METHOD;
    /**
     * The initial arguments.
     */
    private String[] args = null;

    /**
     * <p> Constructor. </p>
     * @param aArgs - an Array of String with the initial arguments
     */
    public Bootstrap(final String[] aArgs) {
        if (null != aArgs) {
            args = aArgs.clone();
        }
    }

    /**
     * <p>
     * This method tests if exists debug option
     * </p>
     */
    private boolean isDebug() {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--debug")) {
                return true;
            }
        }
        return false;
    }

    private void parseArgs() throws IOException {
        if (isDebug()) {
            CONSOLE.info("Press return when debugger is activated");
            try {
                new BufferedReader(new InputStreamReader(System.in)).readLine();
            } catch (IOException ex) {
                LOG.error(null, ex);
            }
        }
        //What Method
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(START_METHOD)) {
                method = START_METHOD;
                return;
            } else if (args[i].equals(STOP_METHOD)) {
                method = STOP_METHOD;
                return;
            }
        }

    }

    /**
     * <p>
     * The method <code>loadConfig</code> loads the Component Configuration.
     * </p>
     * @throws IOException If an input or output exception occurred
     */
    public Properties loadConfig() throws IOException {
        final Properties componentProperties = new Properties();
        final InputStream inputStream = Component.class.getClassLoader().getResourceAsStream("main.properties");
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream,
                    "UTF-8");
            componentProperties.load(inputStreamReader);
        } finally {
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return componentProperties;
    }

    /**
     * <p>
     * The method <code>start</code> initialize the execution of the
     * sample of use.
     * </p>
     * @throws IOException If an input or output exception occurred
     */
    public void start() throws IOException {
        final Properties componentProperties = loadConfig();
        //Comment with the application description
        CONSOLE.info(componentProperties.getProperty("com.softwaresano.examples.calculator.description"));
        final Component component = new com.softwaresano.examples.calculator.impl.Component();
        CONSOLE.info("Calculadora:");
        CONSOLE.info("============");
        CONSOLE.info(format("2 + 2 = {0}",
                new Object[]{component.add(2, 2)}));
    }

    /**
     * <p>
     * The method <code>stop</code> stops the execution of the sample of use.
     * </p>
     * <p>
     * If the component isn't a server this method isn't use
     * </p>
     *
     */
    public void stop() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * <p>
     * The method <code>main</code> launch the application.
     * </p>
     * @param args an Array of Strings
     * @throws IOException If an input or output exception occurred
     */
    public static void main(final String[] args) throws IOException {
        final Bootstrap bs = new Bootstrap(args);
        bs.parseArgs();
        if (bs.method.equals(START_METHOD)) {
            bs.start();
        } else if (bs.method.equals(STOP_METHOD)) {
            bs.stop();
        }
    }
}
