
package com.aem.community.core.servlets;
import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*** @author Teja G**/
@Component(service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths="+ "/bin/recaptcha"
})
public class GetRecaptchaResponseServlet extends SlingAllMethodsServlet {
	 
	 private static final String RESPONSETYPE = "application/json";
	 private static final Logger log = LoggerFactory.getLogger(GetRecaptchaResponseServlet.class);
	 private static final String UTF8 = "UTF-8"; 
	 @Override protected void doPost(final SlingHttpServletRequest req, 
	 	final SlingHttpServletResponse resp)throws ServletException, IOException {
	  log.debug(":::::::::::::: GetRecaptchaResponseServlet GET() Starts :::::::::::::");
		  String response = "";
		  response = getResponse(req);
		  resp.setContentType(RESPONSETYPE);
		  resp.getWriter().write(response);
	  log.debug(":::::::::::::: GetRecaptchaResponseServlet GET() Ends :::::::::::::");
	 }
	 public String getResponse(SlingHttpServletRequest req) {
	  String response = "[]";
	  String str = req.getParameter("g-recaptcha-response") != null ? req.getParameter("g-recaptcha-response") : "";
	  String secretkey = req.getParameter("secretkey");
	  String configUrl = "https://www.google.com/recaptcha/api/siteverify?secret=" +secretkey +"&response=" + str;
	  try {
		  String certificatesTrustStorePath = "C:/Program Files/Java/jdk1.8.0_181/jre/lib/security/cacerts"; 
		 System.setProperty("javax.net.ssl.trustStore", certificatesTrustStorePath); 
		 System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
	   log.debug("::: Params :: token:: " + str);
		   if (str != null) {
			   
			    CloseableHttpClient client = null;
			    HttpResponse response1 = null;
			    HttpClientBuilder httpClientBuilder = null;
			    httpClientBuilder = HttpClientBuilder.create();
			    client = httpClientBuilder.build();
			    HttpGet request = new HttpGet(configUrl);
			    response1 = client.execute(request);
//			    DefaultHttpClient httpClient = new DefaultHttpClient();
//				HttpGet getRequest = new HttpGet(baseUrl);
//				getRequest.addHeader("accept", "application/json");
//				HttpResponse response1 = httpClient.execute(getRequest);
			    if(response1 != null && response1.getEntity() != null) {
				     HttpEntity entity = response1.getEntity();
				     response = EntityUtils.toString(entity, UTF8);
			    } log.info("Response::: " + response);
		   }
	  } catch (Exception e) {
	   log.error("Error in processing http request ::: ");
	  }
	  return response;
	 }
}