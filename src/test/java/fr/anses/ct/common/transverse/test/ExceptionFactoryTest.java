package fr.anses.ct.common.transverse.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import fr.anses.ct.common.transverse.ExceptionFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/tech-context.xml" })
public class ExceptionFactoryTest {
	  @Autowired
	  private ExceptionFactory exceptionFactory;
	  
	  private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionFactoryTest.class);
	  
	  @Test
	  public void creerException() {
		  String mess = exceptionFactory.getMessage("HEADER_READ_ERROR", new String[] {"0","1"});
		  LOGGER.info("Mess :" + mess);
		  Assert.assertEquals(mess.length(), 45);  
	  }

}
