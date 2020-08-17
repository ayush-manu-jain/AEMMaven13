package com.aem.community.core.servlets;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;


import org.apache.sling.api.servlets.HttpConstants;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.Servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.felix.scr.annotations.Activate;
//import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
//import org.json.simple.parser.JSONParser;

@Component(service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths="+ "/bin/recaptcha1"
})
public class RecaptchaServlet extends SlingAllMethodsServlet {

   
    public static final Logger LOGGER = LoggerFactory.getLogger(RecaptchaServlet.class);
    private static final String RESPONSETYPE = "application/json";
    private static final String UTF8 = "UTF-8";

    @Override
    protected void doPost(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) {
        try {
            
            String response = "";
            String secretKey = req.getParameter("sk");
            response = getResponse(req, secretKey);
            resp.setContentType(RESPONSETYPE);
            resp.getWriter().write(response);
            
        } catch (Exception e) {
            LOGGER.error("exceptionDoPost----------v1" + e.getMessage());
            e.printStackTrace();
        }
    }

    public String getResponse(SlingHttpServletRequest req, String secretKey) {
        String response = "[]";
        String gRecaptchaResponse = req.getParameter("g-recaptcha-response");
        try {
            LOGGER.error("secretKey point..v1" + secretKey);
            String certificatesTrustStorePath = "C:/Program Files/Java/jdk1.8.0_181/jre/lib/security/cacerts";
            System.setProperty("javax.net.ssl.trustStore", certificatesTrustStorePath);
            System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
            String configUrl = "https://www.google.com/recaptcha/api/siteverify?secret=" + secretKey + "&response=" + gRecaptchaResponse;
            LOGGER.error("configUrl point..v1" + configUrl);

            CloseableHttpClient client = null;
            HttpResponse response1 = null;
            HttpClientBuilder httpClientBuilder = null;
            httpClientBuilder = HttpClientBuilder.create();
            client = httpClientBuilder.build();
            HttpGet request = new HttpGet(configUrl);
            response1 = client.execute(request);
            if (response1 != null && response1.getEntity() != null) {
                HttpEntity entity = response1.getEntity();
                response = EntityUtils.toString(entity, UTF8);
            }

        } catch (Exception e) {
            LOGGER.error("Recaptchaexception----------v1" + e.getMessage());
        }
        return response;
    }
}
