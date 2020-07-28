package com.autoai.service;

import java.io.File;

/**
 * @author : zhukaishengy
 * @date : 2020/7/28 16:08
 * @Description : 序列化和反序列化操作类
 * @version : v1.0
 */
public interface ISerializer {

    /**
     * 序列化
     * @param t obj对象
     * @param <T> obj泛型
     * @return 数组
     */
    <T> byte[] serialize(T t);

    /**
     * 反序列化
     * @param bytes 数组
     * @param <T> obj泛型
     * @param clazz 泛型
     * @return obj对象
     */
    <T> T deserialize(byte[] bytes, Class<T> clazz);

    /**
     * 序列化到文件
     * @param t obj对象
     * @param file 文件
     * @param <T> obj泛型
     */
    <T> void writeFile(T t, File file);

    /**
     * 从文件中反序列化对象
     * @param file 文件
     * @param <T> obj泛型
     * @param clazz 泛型
     * @return obj对象
     */
    <T> T readFile(File file, Class<T> clazz);
}
