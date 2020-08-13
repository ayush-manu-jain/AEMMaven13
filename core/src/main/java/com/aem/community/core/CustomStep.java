package com.aem.community.core;
 
import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.search.QueryBuilder;

@Component(service = WorkflowProcess.class, property = { "process.label=My Custom Workflow Process"})
public class CustomStep implements WorkflowProcess {
	Resource resource;
	Resource metadataResource;
	ResourceResolver resourceResolver;
	ValueMap assetMetadataProperties;

	@Reference
	private QueryBuilder builder;

	@Reference
	public ResourceResolverFactory rrFactory;

	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {
		
		String path = workItem.getWorkflowData().getPayload().toString(); 
	    String uploadedMetadataPath = path.substring(0, path.lastIndexOf("rendition")-1) + "/metadata";
	    LOGGER.info("######### This is Uploaded asset name ########## "+uploadedMetadataPath);
	    
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(ResourceResolverFactory.SUBSERVICE, "racvService");
		ResourceResolver resolver;
		try {
			resolver = rrFactory.getServiceResourceResolver(param);
			Resource metadataResource = resolver.getResource(uploadedMetadataPath);

			String title = "";
			String description = "";
			if(metadataResource != null) {
					ValueMap prop = metadataResource.adaptTo(ValueMap.class);
					title = prop.get("dc:title", (String)null);
					description = prop.get("dc:description", (String)null);
					workItem.getWorkflowData().getMetaDataMap().put("oldtitle", title);
					workItem.getWorkflowData().getMetaDataMap().put("olddescription", description);
					
					LOGGER.info("!!!!!!!!!! This is myTitle !!!!!!!! "+title);
			}
		} catch (LoginException e1) {
// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
