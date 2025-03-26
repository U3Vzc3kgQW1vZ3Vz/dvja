package com.appsecco.dvja.controllers;

import com.appsecco.dvja.services.SafeModeService;
import org.apache.commons.lang.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;

public class RedirectAction extends BaseController {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String execute() {

        if (StringUtils.isEmpty(getUrl()))
            return renderText("Missing url");

        if (SafeModeService.isSafe()) {
            try {
                URL resolvedUrl = new URL(getUrl());
                if (!resolvedUrl.getHost().equalsIgnoreCase("localhost")) {
                    return renderText("Can't redirect to external page");
                }
            } catch (MalformedURLException e) {
                if (getUrl().startsWith("/") || getUrl().startsWith("localhost"))
                    return "redirect";
                return renderText(e.getMessage());
            }
        }
        return "redirect";
    }

}
