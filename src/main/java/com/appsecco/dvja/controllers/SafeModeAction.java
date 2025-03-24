package com.appsecco.dvja.controllers;

import com.appsecco.dvja.services.SafeModeService;

public class SafeModeAction extends BaseController{
    private boolean safe;
    public String execute() {
            SafeModeService.setSafe(isSafe());
        System.out.println(isSafe());
        return NONE;
    }
    public boolean isSafe() {
        return safe;
    }
    public void setSafe(boolean safe) {
        this.safe = safe;
    }
}
