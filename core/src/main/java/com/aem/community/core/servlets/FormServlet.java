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

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.community.core.AuthorizationService;
/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */
@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_POST, "sling.servlet.paths=" + "/bin/formservlet" })
public class FormServlet extends SlingAllMethodsServlet {
	
	@Reference
    public transient AuthorizationService authorizationService;

	private static final long serialVersionUid = 1L;

	protected static final Logger LOGGER = LoggerFactory.getLogger(FormServlet.class);
	
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServerException, IOException {
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
		    		  JSONObject obj=new JSONObject();
		    		  obj.put("username",username);
		    		  obj.put("password",password);
		    	      String jsonData = obj.toString();
		    	      String responseCode = authorizationService.callRestApi(jsonData);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			LOGGER.error("JSONException", e);
		}

	}
}
