package com.appsecco.dvja.controllers;
        
        import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

        import java.io.*;

        public class FileUploadController extends BaseController {
    private String filename;
    private File file;
    private String contentType;
    public String outputPath;

            public String uploadFile() throws IOException {
                byte[] bytes = new byte[(int) getFile().length()];
        
                        String[] brokenDownString = getName().split("\\.");
        //Checks if there is any extension after the last . in your input
                        if (brokenDownString.length <2) {
                        setName(getName()+".jpg");
                    }
                String rootPath = ServletActionContext.getServletContext().getRealPath("/upload");
                try {
                        FileInputStream inputStream = new FileInputStream(getFile());
                        inputStream.read(bytes);
                        File dir = new File(rootPath +File.separator );
                        if (!dir.exists())
                                dir.mkdirs();
            
                                // Create the file on server
                                        File serverFile = new File(dir.getAbsolutePath()
                                         +File.separator + filename);
                        BufferedOutputStream stream = new BufferedOutputStream(
                                        new FileOutputStream(serverFile));
                        stream.write(bytes);
                        stream.close();
            //            System.out.println("Server File Location="
                    //                     serverFile.getAbsolutePath());
                                        return  ServletActionContext.getRequest().getContextPath()+"upload/"+getName();
                    } catch (IOException e) {
            
                            }
                return null;
            }

            public String execute() {
        
                        if (getFile() == null) {
                        addFieldError("file", "File is required");
                        return INPUT;
                    }
                try {
                        setOutputPath(uploadFile());
                    } catch (IOException e) {
                        addFieldError("path", "Invalid path or file");
                    }
                return SUCCESS;
            }

            public void setFile(File file) {
                this.file = file;
            }

            public File getFile() {
                return file;
            }

            public String getName() {
                return filename;
            }

            public void setName(String name) {
                this.filename = name;
            }

            public String getContentType() {
                return contentType;
            }

            public void setContentType(String contentType) {
                this.contentType = contentType;
            }
    public String getOutputPath() {
                return outputPath;
            }
    public void setOutputPath(String outputPath) {
                this.outputPath = outputPath;
            }
}
