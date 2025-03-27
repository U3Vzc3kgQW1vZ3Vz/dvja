package com.appsecco.dvja.controllers;

import com.appsecco.dvja.services.SafeModeService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;

public class FileTraversalAction extends BaseController {
    public String path;
    public String fileOutput;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileOutput() {
        return fileOutput;
    }

    public void setFileOutput(String fileOutput) {
        this.fileOutput = fileOutput;
    }

    public void getFile() throws IOException {

        String rootPath = ServletActionContext.getServletContext().getRealPath("/upload");

        File file = new File(rootPath+File.separator+getPath());
        if (file.exists()) {
            if (SafeModeService.isSafe()) {
                if (!file.getParent().equals(new File(rootPath+File.separator).getAbsolutePath())) {
                    throw new IOException(", not a file in allowed path");
                }
            }
            byte[] bytes = Files.readAllBytes(file.toPath());
            byte[] encodeBase64 = Base64.encodeBase64(bytes);
            String base64Encoded = new String(encodeBase64, "UTF-8");
            setFileOutput(base64Encoded);
        } else throw new IOException(" ");
    }

    public String execute() {
        if (StringUtils.isEmpty(getPath())) {
            return INPUT;
        }
        try {
            getFile();
        } catch (IOException e) {
            addFieldError("path", "Invalid path or file" + e.getMessage());
        }
        return SUCCESS;
    }
}
