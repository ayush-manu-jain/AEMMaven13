package com.aem.community.core.models;
 
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.settings.SlingSettingsService;

@Model(adaptables=Resource.class)
public class HelloWorldModel {

    @Inject
    private SlingSettingsService settings;

    @Inject @Named("sling:resourceType") @Default(values="No resourceType")
    protected String resourceType;
    
    @ValueMapValue
    String link;
    
    private String message;

    @PostConstruct
    protected void init() {
    	Date date = new Date();  
        message = date.toString();
        if (!StringUtils.startsWithIgnoreCase(link, "http")) {
        	  link = link +".html";
        	  }
    }

    public String getMessage() {
        return message;
    }
    
    public String getLink() {
    	return link;
    }
}
