package com.aem.community.core.models;
 
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.aem.community.core.MySimpleService;

@Model(adaptables=Resource.class)
public class HelloWorldModel {
	
	private String abc;
	private String efg;
	
	@OSGiService
	MySimpleService mySimpleService;
    
    public String getName() {
    	return mySimpleService.getName();
    }
    
    public String[] getEmail() {
    	return mySimpleService.getEmail();
    }
    public boolean getCheckBoxValue() {
    	return mySimpleService.getCheckBoxValue();
    }
    public String getTime() {
    	return mySimpleService.getTime();
    }
}
