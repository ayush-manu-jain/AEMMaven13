package com.aem.community.core;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

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

@Component(service = WorkflowProcess.class, property = { "process.label=Set Metadata Custom Step" })
public class GetCustomStepData implements WorkflowProcess {
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
	public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap)
			throws WorkflowException {
		
		String title = workItem.getWorkflowData().getMetaDataMap().get("oldtitle", String.class);
		String description = workItem.getWorkflowData().getMetaDataMap().get("olddescription", String.class);
		String path = workItem.getWorkflowData().getPayload().toString();
		String uploadedMetadataPath = path.substring(0, path.lastIndexOf("rendition") - 1) + "/metadata";
		LOGGER.info("$$$$$$$$$ This is Uploaded asset name $$$$$$$$$ " + title + " $$$$$$ " + description + " $$$$$$ ");

		Map<String, Object> param = new HashMap<String, Object>();
		param.put(ResourceResolverFactory.SUBSERVICE, "racvService");
		ResourceResolver resolver;
		Session session;
		try {
			resolver = rrFactory.getServiceResourceResolver(param);
			Resource metadataResource = resolver.getResource(uploadedMetadataPath);
			session = resolver.adaptTo(Session.class);

			if (metadataResource != null) {
				Node metadataNode = metadataResource.adaptTo(Node.class);
				if(title != null){
				metadataNode.setProperty("dc:title", title);
				metadataNode.setProperty("dc:description", description);
				LOGGER.info("@@@@@@@@@ title is set @@@@@@@@@@ ");
				}
			}

			session.save();

			session.logout();
		} catch (LoginException | RepositoryException e1) {
// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
