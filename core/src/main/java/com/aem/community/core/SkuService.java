package com.aem.community.core;

import org.apache.sling.commons.json.JSONObject;

public interface SkuService {

    /**
     * Service to read sku osgi values
     * @return String[]
     */
         
    JSONObject getSkuValues();

}