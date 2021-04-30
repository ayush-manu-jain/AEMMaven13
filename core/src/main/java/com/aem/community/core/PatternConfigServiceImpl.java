package com.aem.community.core;


import org.apache.sling.settings.SlingSettingsService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Pattern service implementation
* @author ravik
**/
 
@Component(service = PatternConfigService.class,configurationPolicy=ConfigurationPolicy.REQUIRE)
@Designate(ocd = PatternConfiguration.class)
public class PatternConfigServiceImpl implements PatternConfigService {
 
    private PatternConfiguration config;
     
    
    @Activate
    public void activate(PatternConfiguration config) {
        this.config = config;
    }
 
    public String getPattern() {
       return config.patternExternalLinks();
       
    }
}
 
