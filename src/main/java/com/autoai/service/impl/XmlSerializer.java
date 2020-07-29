package com.autoai.service.impl;

import com.autoai.service.ISerializer;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author : zhukaishengy
 * @date : 2020/7/29 09:47
 * @Description : XML序列化器
 * @version : v1.0
 */
public class XmlSerializer implements ISerializer {

    private static final XStream xStream = new XStream();

    @Override
    public <T> byte[] serialize(T t) {
        return xStream.toXML(t).getBytes();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        return (T)xStream.fromXML(new String(bytes));
    }

    @Override
    public <T> void writeFile(T t, File file) {
        try (
            FileOutputStream fileOutputStream = new FileOutputStream(file)
        ){
            xStream.toXML(t, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T readFile(File file, Class<T> clazz) {
        return (T)xStream.fromXML(file);
    }
}
