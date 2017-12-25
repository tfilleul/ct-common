package fr.anses.ct.common.transverse;


import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cedarsoftware.util.io.JsonWriter;

public class RouteFailureHandler implements Processor {
	
	/** Le loggeur de l'interceptor. */
	  private static final Logger LOGGER = LoggerFactory.getLogger(RouteFailureHandler.class);
		 
	    public void process(Exchange exchange) throws Exception {
	    	
	    	LOGGER.debug("Entrée de RouteFailureHandler.process");
	        // the caused by exception is stored in a property on the exchange
	    	Throwable caused = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
	        // here you can do what you want, but Camel regard this exception as handled, and
	        // this processor as a failurehandler, so it wont do redeliveries. So this is the
	        // end of this route. But if we want to route it somewhere we can just get a
	        // producer template and send it.
	    	
	    	caused.printStackTrace();
	        
	        // Reconstruction de l'exception sans la stack trace
	        RfaException causedRfa = (RfaException) caused;
	        RfaException rfe = new RfaException();
	        rfe.setMessage(causedRfa.getMessage());
	        rfe.setCode(causedRfa.getCode());
	        rfe.setUuid(causedRfa.getUuid());
	        
	        String code403 = "403";
	        if (causedRfa.getCode().contains(code403)) {
		        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "403");
	        }
	        else {
	        	exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, "500");
	        }

	        
	        Map<String, Object> args = new HashMap<String, Object>();
	        args.put(JsonWriter.PRETTY_PRINT, true);
	        exchange.getIn().setBody(JsonWriter.objectToJson(rfe, args));
	    }
}
