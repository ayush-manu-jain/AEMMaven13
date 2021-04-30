
package com.aem.community.core.servlets;

import java.util.Iterator;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONObject;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=" + "/bin/urllinkservlet" })
public class DropDownServlet extends SlingAllMethodsServlet {

    private static Logger LOGGER = LoggerFactory.getLogger(DropDownServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request,
                         SlingHttpServletResponse response) {
        try {
        	JSONObject obj=new JSONObject();
        	String urlLinktitle = request.getParameter("urllabel");
        	ValueMap childProperties;
        //    ResourceResolver resourceResolver = request.getResourceResolver();
        //    Resource pathResource = request.getResource();
                String rootPath = "/etc/acs-commons/lists/urllinks-list/jcr:content/list";
                Resource resource = request.getResourceResolver().getResource(rootPath);
                Iterator<Resource> items = resource.listChildren();
                while (items.hasNext()) {
                    Resource child = items.next();
                    childProperties = child.adaptTo(ValueMap.class);
                    String title = childProperties.get("jcr:title",(String)null);
                    String link = childProperties.get("value",(String)null);
                    if(urlLinktitle.equalsIgnoreCase(title)) {
                    obj.put("urllink",link);
                    String jsonData = obj.toString();
                    response.getWriter().write(jsonData);
                    }
                }

        } catch (Exception e) {
            LOGGER.error("Error in Get Drop Down Values", e);
        }
    }
	}

