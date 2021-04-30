package com.aem.community.core;
 
import org.apache.commons.lang3.StringUtils;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;
 
@ObjectClassDefinition(name = "My config value", description = "Service Configuration")
public @interface MyServiceConfiguration {
	
	@AttributeDefinition(name = "Name", description = "name")
    String name();
	
	@AttributeDefinition(name = "Email values", description = "Email values")
    String[] email();
     
    @AttributeDefinition(name = "Checkbox Value", description = "CheckBox value")
    boolean checkBoxValue() default true;
    
    @AttributeDefinition(
            name = "Dropdown property",
            description = "Sample dropdown property",
            options = {
                @Option(label = "DAYS", value = "DAYS"),
                @Option(label = "HOURS", value = "HOURS"),
                @Option(label = "MILLISECONDS", value = "MILLISECONDS"),
                @Option(label = "MINUTES", value = "MINUTES"),
                @Option(label = "SECONDS", value = "SECONDS")
            }
        )
    String time() default StringUtils.EMPTY;
}