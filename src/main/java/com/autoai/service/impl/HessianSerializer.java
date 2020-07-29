package com.autoai.service.impl;

import com.autoai.service.ISerializer;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @author : zhukaishengy
 * @date : 2020/7/28 16:55
 * @Description : Hessian序列化器
 * @version : v1.0
 */
@Slf4j
public class HessianSerializer implements ISerializer {

    /**
     * 序列化
     * @param t obj对象
     * @return 数组
     */
    @Override
    public <T> byte[] serialize(T t) {
        try (
            ByteArrayOutputStream os = new ByteArrayOutputStream()
        ){
            // 不支持autoClosable
            Hessian2Output output = new Hessian2Output(os);
            output.writeObject(t);
            output.close();
            return os.toByteArray();
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
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes)
        ){
            Hessian2Input hessian2Input = new Hessian2Input(byteArrayInputStream);
            T obj = (T)hessian2Input.readObject();
            hessian2Input.close();
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
            FileOutputStream fileOutputStream = new FileOutputStream(file)
        ){
            // 不支持autoClosable
            Hessian2Output hessian2Output = new Hessian2Output(fileOutputStream);
            hessian2Output.writeObject(t);
            hessian2Output.flush();
            hessian2Output.completeMessage();
            hessian2Output.close();
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
            FileInputStream fileInputStream = new FileInputStream(file)
        ){
            Hessian2Input hessian2Input = new Hessian2Input(fileInputStream);
            T obj = (T)hessian2Input.readObject();
            hessian2Input.close();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
