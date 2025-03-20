package com.appsecco.dvja.controllers;

import org.apache.commons.lang.StringUtils;

public class TemplateAction extends BaseController{
    private String name;
    public String execute() {
        if(StringUtils.isEmpty(name)) {
            return INPUT;
        }
        return SUCCESS;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
