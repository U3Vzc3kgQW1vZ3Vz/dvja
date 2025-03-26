package com.appsecco.dvja.controllers;

import com.appsecco.dvja.services.SafeModeService;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NetFileUploadAction extends BaseController {
    public String textOutput;
    private String url;

    public String fetchFile() throws IOException {
        InputStream in = null;
        FileOutputStream out = null;
        String rootPath = ServletActionContext.getServletContext().getRealPath("/upload");
        File dir = new File(rootPath + File.separator);
        if (!dir.exists())
            dir.mkdirs();
        File serverFile = new File(dir.getAbsolutePath() + File.separator + "output.jpg");
        try {
            URL url = new URL(getUrl());
            if (SafeModeService.isSafe()) {
                String host = url.getHost();
                InetAddress inetAddress = InetAddress.getByName(host);
                String protocol = url.getProtocol();
                if (!protocol.equals("https") && !protocol.equals("http")) {
                    return "Not Valid URL";
                }
                if (inetAddress.isLoopbackAddress() || inetAddress.isLinkLocalAddress()) {
                    return "Not Valid URL";

                }
                if (inetAddress.isAnyLocalAddress()) {
                    return "Not Valid URL";
                }
                if (inetAddress.isSiteLocalAddress()) {
                    return "Not Valid URL";
                }
            }
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
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
        return ServletActionContext.getRequest().getContextPath() + "upload/" + "output.jpg";
    }

    public String execute() {
        if (getUrl() == null || getUrl().trim().equals("")) {
            return INPUT;
        }
        try {
            setTextOutput(fetchFile());
        } catch (IOException e) {
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
