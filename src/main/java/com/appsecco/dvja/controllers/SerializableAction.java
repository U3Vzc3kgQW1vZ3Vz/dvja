package com.appsecco.dvja.controllers;

import com.appsecco.dvja.models.WhitelistedObjectInputStream;
import com.appsecco.dvja.services.SafeModeService;
import org.apache.commons.lang.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SerializableAction extends BaseController{
private String serialString;
private String output;

public void deserialize(){
try{
    byte[] decoded = Base64.getDecoder().decode(getSerialString());

    ByteArrayInputStream bytes = new ByteArrayInputStream(decoded);
    ObjectInputStream in = null;
    if(SafeModeService.isSafe()){
        in=new WhitelistedObjectInputStream(bytes);

    }else {
        in=new ObjectInputStream(bytes);
    }
    setOutput(new String(decoded, StandardCharsets.UTF_8));
    Object obj=in.readObject();
    in.close();
}
catch(IOException e){
//    e.printStackTrace();
addFieldError("serialString", e.getMessage());
}
catch(ClassNotFoundException e){
//    e.printStackTrace();
    addFieldError("serialString", e.getMessage());
}
}
public String execute() throws Exception {
    if(StringUtils.isBlank(getSerialString())){
        addFieldError("serialString", "Serial string is required");
        return INPUT;
    }
    deserialize();
    return SUCCESS;
}
public String getSerialString() {
    return serialString;
}
public void setSerialString(String serialString) {
    this.serialString = serialString;
}
public String getOutput() {
    return output;
}
public void setOutput(String output) {
    this.output = output;
}
}
