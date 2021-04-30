package com.aem.community.core.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.UnknownHostException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;


import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths="+ "/bin/testjlf"
})
public class PostcodeLoqateServlet  extends SlingAllMethodsServlet {

	private static final long serialVersionUID = 1L;
	private static final String UTF8 = "UTF-8";
	String resp = "[]";
	Logger log = LoggerFactory.getLogger(this.getClass());
    
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException,IOException{
        log.info("JPB PostcodeLoqate - start");
      
        try {
	      String url = "https://sit.ecom.waitrose.com/address-suggestions-qa/v1/address-suggestions?postalCode=cb21sj";
            log.info("JPB PostcodeLoqate got url");
//        HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
//        log.info("JPB PostcodeLoqate open connection to "+url.toExternalForm() );
//        connection.setRequestMethod("GET");
//    connection.connect();
//    log.info("JPB PostcodeLoqate connecting " );
//        int code = connection.getResponseCode();
//        log.info("JPB PostcodeLoqate got response - code is "+code);
            
        CloseableHttpClient client = null;
            HttpResponse response1 = null;
            HttpClientBuilder httpClientBuilder = null;
            httpClientBuilder = HttpClientBuilder.create();
            client = httpClientBuilder.build();
            HttpGet req = new HttpGet(url);
            response1 = client.execute(req);
            if (response1 != null && response1.getEntity() != null) {
                HttpEntity entity = response1.getEntity();
                resp = EntityUtils.toString(entity, UTF8);
            }
	       
        } catch (MalformedURLException e) {
        log.error("JPB PostcodeLoqate malformed URL");
        e.printStackTrace();       
        } catch (UnknownHostException e) {
        log.error("JPB PostcodeLoqate Unkownhost error");
        e.printStackTrace();
        } catch (IOException e) {
        log.error("JPB PostcodeLoqate IO error");
      e.printStackTrace();
        } catch (Exception e){
        log.error("JPB PostcodeLoqate other exception: ");
        e.printStackTrace();
        }
        response.getWriter().write(resp);
    }

}
