package com.aem.community.core;


import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.metatype.annotations.Designate;

 
 
@Component(service = SkuService.class,configurationPolicy=ConfigurationPolicy.REQUIRE)
@Designate(ocd = SkuServiceConfiguration.class)
@SuppressWarnings("PMD")
public class SkuServiceImpl implements SkuService {

 
    private SkuServiceConfiguration config;

    
    @Activate
    public void activate(SkuServiceConfiguration config) {
        this.config = config;
    }
    
    public JSONObject getSkuValues() {
    	 JSONObject skuJson=new JSONObject();
    	String[] configArray = config.configValue();
    	if(configArray.length != 0) {
    	for(int i = 0; i< configArray.length; i++) {
    	String[] skuArray = configArray[i].split(":");
    	try {
			skuJson.put(skuArray[0], skuArray[1]);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	}
    	}
       return skuJson;
       
    }
}
 