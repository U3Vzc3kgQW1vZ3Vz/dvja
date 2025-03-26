package com.appsecco.dvja.controllers;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;

/*
Credit to https://github.com/JoyChou93/java-sec-code/blob/master/src/main/java/org/joychou/controller/SSTI.java
*/
public class TemplateAction extends BaseController {
    private String name;

    public void execTemplate() {

        try {
            Velocity.init();
            VelocityContext context = new VelocityContext();
            context.put("name", "home");
            StringWriter writer = new StringWriter();
//            SSTI Sink
            if (Velocity.evaluate(context, writer, "test", name)) {
                setName("lol " + name);
            }
        } catch (Exception e) {

        }
    }

    public String execute() {
        if (StringUtils.isEmpty(name)) {
            return INPUT;
        }
        execTemplate();
        return SUCCESS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
