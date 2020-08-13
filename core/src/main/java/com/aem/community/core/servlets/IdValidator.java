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
import java.util.Iterator;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;
import javax.servlet.Servlet;

import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.jackrabbit.api.security.user.User;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */
@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=" + "/bin/abc1" })
public class IdValidator extends SlingAllMethodsServlet {

	private static final long serialVersionUid = 1L;

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServerException, IOException {
		Session session = request.getResourceResolver().adaptTo(Session.class);
		UserManager userManager = request.getResourceResolver().adaptTo(UserManager.class);
		User user;
		String username = "";
		try {
			String email = request.getParameter("email");
			String action = request.getParameter("action");

			Iterator<Authorizable> userDetails =  userManager.findAuthorizables("email", email, UserManager.SEARCH_TYPE_USER);
			while(userDetails.hasNext()) {
			username =	userDetails.next().getPrincipal().getName();
			}
			 Authorizable authorizable = userManager.getAuthorizable(username);
		      if (authorizable instanceof User && authorizable != null) {
		    	  if(action.equalsIgnoreCase("submit")) {
		    		  Value[] familyName = authorizable.getProperty("./profile/familyName");
		    		  Value[] givenName = authorizable.getProperty("./profile/givenName");
		    		  JSONObject obj=new JSONObject();
		    		  obj.put("userid",username);
		    		  obj.put("firstname",givenName[0].toString());
		    	      obj.put("lastname",familyName[0].toString());
		    	      String jsonData = obj.toString();
		    		  response.getWriter().write(jsonData);
		    	  }else if(action.equalsIgnoreCase("delete")){
				         user = (User)authorizable;
				         user.remove(); //Remove the user
				         session.save();
				         response.getWriter().write("User deleted successfully");
		    	  }
		    } else {
		    	response.getWriter().write("fail");
		    }
		} catch (RepositoryException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
