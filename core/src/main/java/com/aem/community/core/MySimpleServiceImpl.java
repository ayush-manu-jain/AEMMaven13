package com.aem.community.core;
 
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
 
@Component(service = MySimpleService.class, immediate = true)
@Designate(ocd = MyServiceConfiguration.class)
public class MySimpleServiceImpl implements MySimpleService {
     
    // to use the OSGi annotations
    // use version 3.2.0 of maven-bundle-plugin
 
    private MyServiceConfiguration config;
     
    private static final Logger log = LoggerFactory.getLogger(MySimpleServiceImpl.class);
    
    @Activate
    public void activate(MyServiceConfiguration config) {
    	log.error("inside config ########### " +config);
        this.config = config;
    }
 
    public boolean getCheckBoxValue() {
       return config.checkBoxValue(); 
    }
    
    public String getName() {
    	return config.name();
    }

	public String[] getEmail() {
		return config.email();
	}

	@Override
	public String getTime() {
		return config.time();
	}
}
 
