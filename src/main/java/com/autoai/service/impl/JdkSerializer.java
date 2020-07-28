package com.autoai.service.impl;

import com.autoai.service.ISerializer;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @author : zhukaishengy
 * @date : 2020/7/28 16:55
 * @Description : Jdk序列化器
 * @version : v1.0
 */
@Slf4j
public class JdkSerializer implements ISerializer {

    /**
     * 序列化
     * @param t obj对象
     * @return 数组
     */
    @Override
    public <T> byte[] serialize(T t) {
        try (
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)
        ){
            objectOutputStream.writeObject(t);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反序列化
     * @param bytes 数组
     * @return obj对象
     */
    @SuppressWarnings("all")
    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        try (
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)
        ){
            T obj = (T)objectInputStream.readObject();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 序列化到文件
     * @param t   obj对象
     * @param file 文件
     */
    @Override
    public <T> void writeFile(T t, File file) {
        try (
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ){
            objectOutputStream.writeObject(t);
            log.info("file size:{}", file.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从文件中反序列化对象
     * @param file 文件
     * @return obj对象
     */
    @SuppressWarnings("all")
    @Override
    public <T> T readFile(File file, Class<T> clazz) {
        try (
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ){
            T obj = (T)objectInputStream.readObject();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
