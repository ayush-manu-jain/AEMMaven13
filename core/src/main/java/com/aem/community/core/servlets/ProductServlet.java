package com.aem.community.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Session;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

/**
* Servlet to expose product details
* 
 * @author kiratsingh
*
*/

@Component(service = Servlet.class, property = {
             Constants.SERVICE_DESCRIPTION + "=Abbott Products",
             "sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=/bin/adcworkshop/products",
             "sling.servlet.extensions=json" })
public class ProductServlet extends SlingSafeMethodsServlet {
       
       @Reference
       private QueryBuilder builder;
       
       Map<String, String> queryMap = new HashMap<>();
       Resource resource;
       
       @Override
       protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException{
             try{
                    queryMap.put("1_group.p.or", "true");
                    queryMap.put("type", "cq:Page");
                    queryMap.put("1_group.1_path", "/content/we-retail/us/en/experience");
                    queryMap.put("1_group.2_path", "/content/we-retail/us/en/men");
                    Session session= request.getResourceResolver().adaptTo(Session.class);
                     
                    
                    Query query = builder.createQuery(PredicateGroup.create(queryMap), session);
                      SearchResult result = query.getResult();
                      for (Hit hit : result.getHits()) {                         
                            resource = hit.getResource();
                            ((Map<String, String>) resource).entrySet().removeIf(e -> e.getValue().contains("wester-australia-by-camper-vana")); 
                            response.getWriter().write(resource.toString());
                            
                      }
                    
             }
             
             catch(Exception e){
                    
             }
       
       }
}
