package com.appsecco.dvja.models;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

public class WhitelistedObjectInputStream extends ObjectInputStream {
    public WhitelistedObjectInputStream(InputStream in) throws IOException {
        super(in);
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
        // Get the class name before it's actually deserialized
        System.out.println("Deserializing class: " + desc.getName());
        if (!desc.getName().equals(User.class.getName())) {
            throw new ClassNotFoundException("Only User classes can be deserialized");
        }
        // Return the class normally
        return super.resolveClass(desc);
    }
}
