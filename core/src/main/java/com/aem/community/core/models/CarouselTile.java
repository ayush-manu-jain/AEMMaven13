package com.aem.community.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 * Model class for ImageTitleButton component.
 * This class provides all required methods to support the Image title button component.
 *
 * @author ravik
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CarouselTile {

    @Self
    private Resource resource;
    
    @ValueMapValue
    private String ctaLink;
    
    @ValueMapValue
    private String title;
    
    @ValueMapValue
    private String ctaText;
	
	@ValueMapValue
    private String description;

	@ValueMapValue
    private String fileReference;
	
	@ValueMapValue
    private String ctaStyle;

	
	public String getFileReference() {
		return fileReference;
	}

	public void setFileReference(String fileReference) {
		this.fileReference = fileReference;
	}

	public String getCtaLink() {
		return ctaLink;
	}

	public String getTitle() {
		return title;
	}

	public String getCtaText() {
		return ctaText;
	}

	public String getDescription() {
		return description;
	}

	public String getCtaStyle() {
		return ctaStyle;
	}	

	
}