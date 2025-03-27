package com.appsecco.dvja.controllers;

import com.appsecco.dvja.services.SafeModeService;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.detect.Detector;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;

import java.io.*;
import java.nio.file.Files;

public class FileUploadAction extends BaseController {
    public String outputPath;
    private String fileFileName;
    private File file;
    private String name;
    private String fileContentType;
    public static String getContentType(byte[] fileBytes, String filenameWithExtension) throws IOException {
        TikaConfig config = TikaConfig.getDefaultConfig();
        Detector detector = config.getDetector();
        TikaInputStream stream = TikaInputStream.get(new ByteArrayInputStream(fileBytes));
        Metadata metadata = new Metadata();
        metadata.add(Metadata.RESOURCE_NAME_KEY, filenameWithExtension);
        return detector.detect(stream, metadata).toString();
    }

    public String uploadFile() throws IOException {
        byte[] bytes = new byte[(int) getFile().length()];

        String[] brokenDownString = getName().split("\\.");
        //Checks if there is any extension after the last . in your input
        if (brokenDownString.length < 2) {
            setName(getName() + ".jpg");
        }
        Tika tika = new Tika();
        String rootPath = ServletActionContext.getServletContext().getRealPath("/upload");

        FileInputStream inputStream = new FileInputStream(getFile());
        inputStream.read(bytes);
        File dir = new File(rootPath + File.separator);
        if (!dir.exists())
            dir.mkdirs();

        // Create the file on server
        File serverFile = new File(dir.getAbsolutePath()
                + File.separator + name);
//        System.out.println(Files.probeContentType(getFile().toPath()));
//        System.out.println(FilenameUtils.getExtension(getFile().getName()));
//        System.out.println(getFile().getName());
        if (SafeModeService.isSafe()) {
            if (!serverFile.getParent().equals(new File(rootPath).getAbsolutePath())) {
                throw new IOException(", not a file in allowed path");
            }
            if (!getContentType(bytes,getFile().getName()).startsWith("image")) {
                throw new IOException(", Invalid file type");
            }
            if (!(FilenameUtils.getExtension(fileFileName).equals("jpg")
                    || FilenameUtils.getExtension(fileFileName).equals("jpeg")
                    || FilenameUtils.getExtension(fileFileName).equals("png")
                    || FilenameUtils.getExtension(fileFileName).equals("gif")
                    || FilenameUtils.getExtension(fileFileName).equals("bmp")
                    || FilenameUtils.getExtension(fileFileName).equals("webp"))
            ) {
                throw new IOException(", Invalid file extension");
            }
        }
        BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();
        if (inputStream != null) {
            inputStream.close();
        }
        //            System.out.println("Server File Location="
        //                     serverFile.getAbsolutePath());
        return ServletActionContext.getRequest().getContextPath() + "upload/" + getName();
    }

    public String execute() {

        if (getFile() == null) {
            addFieldError("file", "File is required");
            return INPUT;
        }
        try {
            setOutputPath(uploadFile());
        } catch (IOException e) {
            addFieldError("name", "Invalid path or file" + e.getMessage());
        }
        return SUCCESS;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String contentType) {
        this.fileContentType = contentType;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }
}
