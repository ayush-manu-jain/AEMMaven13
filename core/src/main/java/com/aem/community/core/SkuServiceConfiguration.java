package com.aem.community.core;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
 
@ObjectClassDefinition(name = "Sherwin - Sku Configuration", description = "Sku Service Configuration")
public @interface SkuServiceConfiguration {
     
    @AttributeDefinition(name = "Sku Attributes", description = "Sku values in key:value format")
    String[] configValue();
}