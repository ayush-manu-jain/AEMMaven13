package com.aem.community.core.models;
 
import org.apache.sling.api.resource.Resource;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.aem.community.core.SkuService;

@Model(adaptables=Resource.class)
public class SkuModel {

	@OSGiService
	SkuService skuService;
	
	 public String getSkuModelValues() {
		 return skuService.getSkuValues().toString();
	 }

}
