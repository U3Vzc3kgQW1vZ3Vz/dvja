package com.appsecco.dvja.controllers;

import org.apache.struts2.ServletActionContext;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NetFileUploadAction extends BaseController {
    private String url;
    public String textOutput;
    public String fetchFile() throws  IOException {
        InputStream in = null;
        FileOutputStream out = null;
        String rootPath = ServletActionContext.getServletContext().getRealPath("/upload");
            File dir = new File(rootPath +File.separator );
            if (!dir.exists())
                dir.mkdirs();
        File serverFile = new File(dir.getAbsolutePath()+File.separator + "output.jpg");
try{
    URL url = new URL(getUrl());
    //SSRF sink
    URLConnection conn = url.openConnection();
    in = conn.getInputStream();
    out = new FileOutputStream(serverFile);
    int c;
    byte[] b = new byte[1024];
    while ((c = in.read(b)) != -1) {
        out.write(b, 0, c);
    }
} catch (MalformedURLException e) {
    serverFile.delete();
    return "Not Valid URL";

}
finally {
    if (out != null) {
        out.close();
    }
    if (in != null) {
        in.close();
    }
}
return  ServletActionContext.getRequest().getContextPath()+"upload/"+"output.jpg";
    }
    public String execute() {
        if(getUrl()==null || getUrl().trim().equals("")){
            return INPUT;
        }
try{
    setTextOutput(fetchFile());
}catch(IOException e){
    setTextOutput(e.getMessage());
}
        return SUCCESS;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getTextOutput() {
        return textOutput;
    }
    public void setTextOutput(String textOutput) {
        this.textOutput = textOutput;
    }
}
