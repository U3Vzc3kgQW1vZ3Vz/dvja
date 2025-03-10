package com.appsecco.dvja.controllers;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileTraversalController extends BaseController {
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
        String BASE_PATH = "/../../../../docs/assets/";
        String path = this.getClass().getClassLoader().getResource("").getPath();
        String fullPath = URLDecoder.decode(path, "UTF-8");
        String pathArr[] = fullPath.split("/classes/");
        fullPath = pathArr[0];
        File file = new File(fullPath+BASE_PATH + getPath());
        if (file.exists()) {
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
            addFieldError("path", "Invalid path or file");
        }
        return SUCCESS;
    }
}
