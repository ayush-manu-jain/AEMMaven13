//package com.aem.community.core;
//
//import java.util.List;
//
//import org.apache.sling.api.resource.observation.ResourceChange;
//import org.apache.sling.api.resource.observation.ResourceChangeListener;
//import org.osgi.service.component.annotations.Activate;
//import org.osgi.service.component.annotations.Component;
//import org.osgi.service.component.annotations.Modified;
//import org.osgi.service.metatype.annotations.Designate;
//
//
///**
// * Class that provides functions to query pages in the repository.
// *
// * @author pcabo
// */
//@Component(immediate=true,service = ResourceChangeListener.class)
//@Designate(ocd = ResourceListenerConfig.class)
//public class PageServiceImpl implements ResourceChangeListener {
//
//	@Override
//	public void onChange(List<ResourceChange> changes) {
//		for(ResourceChange resourceChange : changes) {
//			String resourcePath = resourceChange.getPath();
//		}
//		
//	}
//	@Activate
//    @Modified
//    public void activate(ResourceListenerConfig config) {
//        //config.getPaths();
//      //  logger.info("**************************TemperaturePropertyListener******************activate**********************");
//    }
//
//
//  
//}
