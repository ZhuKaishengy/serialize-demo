package com.autoai.entity;

import lombok.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.List;

/**
 * @author : zhukaishengy
 * @date : 2020/7/28 16:13
 * @Description : 实体，jdk的序列化必须实现Serializable接口
 * @version : v1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class UserEntity extends SuperEntity {

    /**
     * 序列化和反序列化过程中不能改变
     */
    private static final long serialVersionUID = -2748979296646484182L;

    /**
     * 静态变量
     */
    @Setter
    @Getter
    private static String id;

    private String name;
    private int age;
    private LocalDate birth;

    /**
     * 引用类型
     */
    private List<HobbyEntity> hobbies;

    /**
     * transient变量
     */
    private transient String secret;
    private transient String noSecret;

    /**
     * 自定义扩充序列化方法
     * @param objectOutputStream 对象输出流
     * @throws IOException 异常
     */
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(noSecret);
    }

    /**
     * 自定义扩充反序列化方法
     * @param objectInputStream 对象输入流
     * @throws IOException 异常
     * @throws ClassNotFoundException 异常
     */
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        noSecret = (String) objectInputStream.readObject();
    }
}
