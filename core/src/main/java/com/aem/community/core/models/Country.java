package com.aem.community.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Country.
 */
@Model(adaptables = Resource.class)
public class Country {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    @Optional
    private String countryName;

    @Inject
    @Optional
    private List<Resource> states;

    @Optional
    private List<State> stateList = new ArrayList<>();

    
    public List<State> getStateList() {
        return stateList;
    }

    public String getCountryName() {
  return countryName;
 }

 public void setCountryName(String countryName) {
  this.countryName = countryName;
 }

 public void setStateList(List<State> stateList) {
        this.stateList = stateList;
    }


    @PostConstruct
    protected void init() {
        logger.debug("In init method of Country model.");
        if(!states.isEmpty()) {
            for (Resource resource : states) {
                State state = resource.adaptTo(State.class);
                stateList.add(state);
            }
        }
    }

}