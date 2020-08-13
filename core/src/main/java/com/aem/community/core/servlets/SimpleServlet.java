/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.aem.community.core.servlets;

import org.apache.jackrabbit.commons.JcrUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.rmi.ServerException;
import java.util.Iterator;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */
@Component(service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths="+ "/bin/abc"
})
public class SimpleServlet extends SlingAllMethodsServlet {
	
	private Session session;

    private static final long serialVersionUid = 1L;

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServerException, IOException {
      
     try
     {
    	 String pagePath = "/content";
    	    String templatePath = "/apps/AEMMaven13/templates/profile-template";
    	    String pageTitle = "AdobeSummit";
    	    Page newPage;
    	    String pageName = "adobesummit";
    	 PageManager pageManager; 
         
    	 
         String firstName = request.getParameter("firstName");
         String lastName = request.getParameter("lastName");
      
         //Encode the submitted form data to JSON
         JSONObject obj=new JSONObject();
         obj.put("firstname",firstName);
         obj.put("lastname",lastName);
          
            //Get the JSON formatted data    
         String jsonData = obj.toString();
          
            //Return the JSON formatted data
        response.getWriter().write(jsonData);
      //Invoke the adaptTo method to create a Session 
         
        session = request.getResourceResolver().adaptTo(Session.class);
                 
        
        	Node cont = session.getNode("/var");
        	int contactRec = doesContactExist(cont);
            if (contactRec == -1) {
               cont.addNode("adobesummit", JcrConstants.NT_UNSTRUCTURED);
                session.save();
                Node adobeNode = session.getNode("/var/adobesummit");
                Node userNode=adobeNode.addNode(firstName, JcrConstants.NT_UNSTRUCTURED);
                userNode.setProperty("lastName",lastName);
                session.save();
            }else {
            	Node adobeNode = session.getNode("/var/adobesummit");
                Node userNode=adobeNode.addNode(firstName, JcrConstants.NT_UNSTRUCTURED);
                userNode.setProperty("lastName",lastName);
                session.save();
            }
        
     }
     catch(Exception e)
     {
         e.printStackTrace();
     }
     
   }
    
    private int doesContactExist(Node var) {
        try {
               int index = 0;
               int childRecs = 0;

               java.lang.Iterable<Node> contactNode = JcrUtils.getChildNodes(var, "adobesummit");
               Iterator it = contactNode.iterator();

               // only going to be 1 content/customer node if it exists
               if (it.hasNext()) {
                     // Count the number of child nodes to customer
                     Node contactRoot = var.getNode("adobesummit");
                     Iterable itCust = JcrUtils.getChildNodes(contactRoot);
                     Iterator childNodeIt = itCust.iterator();

                     // Count the number of customer child nodes
                     while (childNodeIt.hasNext()) {
                            childRecs++;
                            childNodeIt.next();
                     }
                     return childRecs;
               } else
                     return -1; // content/customer does not exist
        } catch (RepositoryException e) {

        }
        return 0;
 }

}
