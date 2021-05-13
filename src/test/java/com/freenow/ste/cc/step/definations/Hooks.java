package com.freenow.ste.cc.step.definations;







import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

	
	
	
	@Before
    public void beforeScenario(){
		 
     System.out.println("Start of Scenario");	
    
    } 
 
 @After
    public void afterScenario(){
	  System.out.println("End  of Scenario");	
    }
	
	
	
	
}
