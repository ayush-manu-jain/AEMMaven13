package com.aem.community.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;


@Model(adaptables = SlingHttpServletRequest.class)
public class CountriesModel {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @ChildResource(name="countries") @Via("resource")
    @Optional
    public Resource country;
    
    @ScriptVariable(name="currentPage")
    Page page;
    
    @ValueMapValue(via = "resource")
    Boolean textIsRich;

    public Boolean getRichText() {
        return textIsRich;
    }

    public String getPagePath() {
        return  page.getPath();
    }
    
	public Resource getCountries() {
		return country;
	}
	public String getPath() {     
	    return country.getPath();    
	 } 
}