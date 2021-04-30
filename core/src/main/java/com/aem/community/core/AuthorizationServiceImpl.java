package com.aem.community.core;

import static org.apache.sling.api.servlets.HttpConstants.HEADER_ACCEPT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = AuthorizationService.class)
public class AuthorizationServiceImpl implements AuthorizationService {


    protected static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationServiceImpl.class);

    /*
     * This method call httpPost with parameter token and respond with a responseCode.
     */
    @Override
    public String callRestApi(String jsonData) {
        String responseCode = null;
        try {
        	CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("REST_URL");
            httpPost.setHeader(HEADER_ACCEPT, "application/json");
            httpPost.setHeader("Content-type", "application/json");
            HttpEntity entity = new StringEntity(jsonData);
            httpPost.setEntity(entity);
            HttpResponse response = httpclient.execute(httpPost);
            InputStreamReader reader = new InputStreamReader(response.getEntity().getContent());
            BufferedReader br = new BufferedReader(reader);
            responseCode = br.readLine();
            LOGGER.info("response code in callRestApi in service is {}", responseCode);
        } catch (IOException e) {
            LOGGER.error("IOException occured in callRestApi::NewsletterSignupServiceImpl is", e);
        }
        return responseCode;
    }

}