package com.appsecco.dvja.controllers;

import com.appsecco.dvja.services.SafeModeService;
import org.apache.struts2.ServletActionContext;

import java.io.PrintWriter;

public class SafeModeAction extends BaseController {
    private boolean safe;

    public String execute() {
        SafeModeService.setSafe(isSafe());
        super.getSession().put("safe", isSafe());
        System.out.println(isSafe());
        return SUCCESS;
    }

    public String getSafeState() {
        try {
            // Retrieve stored safe state from super.getSession() (default to false if not set)
            boolean storedSafeValue = super.getSession().get("safe") != null ? (Boolean) super.getSession().get("safe") : false;

            // Return plain text response
            ServletActionContext.getResponse().setContentType("text/plain");
            PrintWriter out = ServletActionContext.getResponse().getWriter();
            out.print(storedSafeValue);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NONE;
    }

    public boolean isSafe() {
        return safe;
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }
}
