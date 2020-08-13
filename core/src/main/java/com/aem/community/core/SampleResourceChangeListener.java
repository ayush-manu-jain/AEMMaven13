package com.aem.community.core;
import java.util.List;
import org.apache.sling.api.resource.observation.ResourceChange;
import org.apache.sling.api.resource.observation.ResourceChangeListener;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
    service = ResourceChangeListener.class,
    property = {
        // filter the notifications by path in the repo. Can be array and supports globs
        ResourceChangeListener.PATHS+"="+"/content/dam",
        //The type of change you want to listen to.
        //Possible values at https://sling.apache.org/apidocs/sling9/org/apache/sling/api/resource/observation/ResourceChange.ChangeType.html.
        ResourceChangeListener.CHANGES+"="+"ADDED",
        ResourceChangeListener.CHANGES+"="+"REMOVED",
        ResourceChangeListener.CHANGES+"="+"CHANGED"

        //PS: If you want to declare multiple values for a prop, you repeat it in OSGI R6 annotations.
        //https://stackoverflow.com/questions/41243873/osgi-r6-service-component-annotations-property-list#answer-41248826
    }
)
public class SampleResourceChangeListener implements ResourceChangeListener{ // Use ExternalResourceChangeListener to listen for changes that happen in a different node
  public static final Logger LOGGER = LoggerFactory.getLogger(SampleResourceChangeListener.class);

  //This method will be called with paths and types of change that occurred
  //The task in this should be fast. In case it takes more time, trigger a sling job from here.
  //The listener can be blacklisted if it's slow [it does for event handler, should be same for ResourceListener IMHO]
  @Override
  public void onChange(List<ResourceChange> list) {
    list.forEach((change) -> {
      LOGGER.info(change.getPath());
      LOGGER.info(change.getType().toString());
      //the methods to identify the property that changed are deprecated.
    });
  }
} 