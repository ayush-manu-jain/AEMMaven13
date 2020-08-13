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
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
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
@Component(service=Servlet.class,
           property={
                   Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
                   "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                   "sling.servlet.paths="+ "/bin/testjson"
           })
public class MyServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUid = 1L;
   
    JSONObject jObject1;
    JSONArray jArray;

    @Override
    protected void doGet(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {
    	
        
        jObject1=new JSONObject();
    	jArray= new JSONArray();
    	List<String> emailList = new ArrayList();
        
            try {
                	jObject1=new JSONObject();
                	
                	jObject1.put("id", "5ec317202563c70001407758");
                	jObject1.put("assetPath", "/content/dam/splunk-gfe/content/Product/Platform/PPT-Splunk-Platform-Overview-Feb-2019.pptx");
                	jObject1.put("userEmail", "KiratTheGreat.com");
                	jArray.put(jObject1);
                	jObject1=new JSONObject();
                	jObject1.put("id", "5ec317282563c70001407759");
                	jObject1.put("assetPath", "/content/dam/splunk-gfe/content/Product/Platform/PPT-Splunk-Platform-Overview-Feb-2019.pptx");
                	jObject1.put("userEmail", "KiratSabKaBaap.com");
                	jArray.put(jObject1);
                	
                	for(int i=0; i<jArray.length();i++) {
                		JSONObject jsn = jArray.getJSONObject(i);
                		String email = jsn.getString("userEmail");
                		InternetAddress address = new InternetAddress(jsn.getString("userEmail"));
                		emailList.add(email);
                	}
            } catch (JSONException e) {
    			e.printStackTrace();

                           } catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

				resp.getWriter().write(emailList.toString());
			
    }
}
