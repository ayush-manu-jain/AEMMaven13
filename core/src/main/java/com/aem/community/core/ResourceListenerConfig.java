//package com.aem.community.core;
//
//import org.apache.sling.api.resource.observation.ResourceChangeListener;
//import org.osgi.service.metatype.annotations.AttributeDefinition;
//import org.osgi.service.metatype.annotations.ObjectClassDefinition;
//
//@ObjectClassDefinition(name = "Asset Listener Configuration")
//public @interface ResourceListenerConfig {
//
//	    @AttributeDefinition(
//	            name = ResourceChangeListener.PATHS,
//	            description = "Configurable paths for temperature event listener"
//	            )
//	    String[] getPaths() default {"/content/dam"};
//
//	    @AttributeDefinition(
//	            name = ResourceChangeListener.CHANGES,
//	            description = "Event types"
//	            )
//	    String[] getEventTypes() default  {"REMOVED","CHANGED"};
//
//	
//}
