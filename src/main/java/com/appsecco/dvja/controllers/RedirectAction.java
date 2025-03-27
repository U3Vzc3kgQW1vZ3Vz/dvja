package com.appsecco.dvja.controllers;

import com.appsecco.dvja.services.SafeModeService;
import org.apache.commons.lang.StringUtils;

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
            if (!getUrl().startsWith("/"))
                return renderText("External URL redirection not supported");
        }
        return "redirect";
    }

}
