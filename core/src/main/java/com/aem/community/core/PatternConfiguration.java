package com.aem.community.core;
 
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
* Pattern configuration for external links
* @author ravik
**/ 

@ObjectClassDefinition(name = "TIAA - Pattern Configuration", description = "Pattern Configuration for link dialogs")
public @interface PatternConfiguration {
     
    @AttributeDefinition(name = "Pattern Value", description = "Value of pattern for external links")
    String patternExternalLinks();
}
