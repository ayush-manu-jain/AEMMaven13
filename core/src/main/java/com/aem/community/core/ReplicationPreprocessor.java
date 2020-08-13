package com.aem.community.core;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.replication.Preprocessor;
import com.day.cq.replication.ReplicationAction;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationException;
import com.day.cq.replication.ReplicationOptions;
import com.day.cq.replication.Replicator;

@Component(immediate=true)
public class ReplicationPreprocessor implements Preprocessor {

	@Reference
	 private ResourceResolverFactory rff;
	 
	 
	 protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	 
	 /* (non-Javadoc)
	  * @see com.day.cq.replication.Preprocessor#preprocess(com.day.cq.replication.ReplicationAction, com.day.cq.replication.ReplicationOptions)
	  */
	 @Override
	 public final void preprocess(ReplicationAction action, ReplicationOptions options) throws ReplicationException{
	  

	   ResourceResolver rr = null;
	   try {
	    rr = rff.getServiceResourceResolver(null);
	    ReplicationActionType replicationType = action.getType();
	    String[] paths = action.getPaths();
	    
	    for(String path : paths){
	     // do something here and throw some exception if the action need to interrupted
	    }
	    
	   } catch (LoginException e) {
	    LOGGER.error(e.getMessage(),e);
	    throw new ReplicationException(e);
	   } finally{
	                 if (rr != null && rr.isLive())
	                  rr.close();
	   }
	  }
	 }
