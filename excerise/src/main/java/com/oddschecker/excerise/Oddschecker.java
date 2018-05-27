
package com.oddschecker.excerise;

import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.oddschecker.controller.OddscheckerController;

/**
 * Hello world!
 *
 */
public class Oddschecker 
{
	private static Logger log = Logger.getLogger(Oddschecker.class);
	private static final int INPUT_PARAMETERS = 1;
	
    public static void main( String[] args )
    {
    	log.log(Level.INFO, "Application " + Oddschecker.class.getName() + "started");
    	
    	OddscheckerController controller = new OddscheckerController();
    	
    	//Check input filePath is valid
    	if( args.length == INPUT_PARAMETERS) {
    		try
    		{
    			controller.assemble(args[0]);
    		}catch(IOException e) {
    			log.log(Level.ERROR, e.getLocalizedMessage());
    		}
    		
    	}
    	else {
    		log.log(Level.INFO, "Incorrect parameters entered.");
    	}
    	
    }
}
