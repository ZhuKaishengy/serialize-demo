package com.autoai.service.impl;

import com.alibaba.fastjson.JSON;
import com.autoai.service.ISerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Type;

/**
 * @author : zhukaishengy
 * @date : 2020/7/29 10:46
 * @Description : JSON序列化器
 * @version : v1.0
 */
public class JsonSerializer implements ISerializer {

    @Override
    public <T> byte[] serialize(T t) {
        return JSON.toJSONBytes(t);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }

    @Override
    public <T> void writeFile(T t, File file) {
        try (
            FileOutputStream fileOutputStream = new FileOutputStream(file)
        ){
            JSON.writeJSONString(fileOutputStream, t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> T readFile(File file, Class<T> clazz) {
        try (
            FileInputStream fileInputStream = new FileInputStream(file)
        ){
            return JSON.parseObject(fileInputStream, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
