package com.aem.community.core;


import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(	service = NewRelicCntl.class,immediate = true,configurationPolicy=ConfigurationPolicy.REQUIRE)
@Designate(ocd = NewRelicCntlImpl.Configuration.class)


public class NewRelicCntlImpl  implements NewRelicCntl {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	protected static final String BOB = "bob";
	private String bob;
	
	@ObjectClassDefinition(name = "JLF = New Relic Control", description = "This conrols New Relic for JLF")
	public @interface Configuration {
		@AttributeDefinition(
				name = "bob",
				description = "This is Bob",
				type = AttributeType.STRING
		)
		String getBob() default "Y";
	}
	
		
	@Activate
	protected void activate(Configuration config) {
		bob = config.getBob();
		
	}
	public String getBob() {
		logger.error("JPB - NewRelicCntl Service");
		return bob;
	}
}
