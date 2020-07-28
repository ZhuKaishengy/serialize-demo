package com.autoai.core;

import com.autoai.entity.HobbyEntity;
import com.autoai.entity.UserEntity;
import com.autoai.service.ISerializer;
import com.autoai.service.impl.JdkSerializer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhukaishengy
 * @date : 2020/7/28 17:28
 * @Description : 序列化核心域
 * @version : v1.0
 */
@Slf4j
public class SerializerDomainTest {

    UserEntity userEntity;
    ISerializer jdkSerializer;
    File file;

    @Before
    public void init() {
        file = new File("/Users/zhukaishengy/StudyWorkSpace/serialize-demo/src/main/resources/a.class");

        List<HobbyEntity> hobbies = new ArrayList<>();
        hobbies.add(HobbyEntity.builder().name("read books").build());
        hobbies.add(HobbyEntity.builder().name("exercise").build());
        hobbies.add(HobbyEntity.builder().name("movies").build());

        // 静态变量初始值1001
        UserEntity.setId("1001");
        userEntity = UserEntity.builder().name("朱开生").age(10)
                .birth(LocalDate.of(1994, 6, 20))
                // 引用类型
                .hobbies(hobbies)
                // transient类型
                .secret("哈哈哈")
                .noSecret("嘻嘻")
                .build();
        // 初始化其超类中的变量
        userEntity.setNum("1");
        jdkSerializer = new JdkSerializer();
    }

    /**
     * 使用jdk序列化
     */
    @Test
    public void test1() {
        byte[] bytes = jdkSerializer.serialize(userEntity);
        UserEntity.setId("1002");
        UserEntity entity = jdkSerializer.deserialize(bytes, UserEntity.class);
        // 序列化之后更改的静态变量，也随之改变了，说明，序列化时不会序列化静态变量
        log.info("entity:{}, id:{}, num:{}", entity.toString(), UserEntity.getId(), userEntity.getNum());
    }

    /**
     * jdk序列化到文件
     * java的序列化，转为二进制文件，保存类元信息，不可轻易更改
     */
    @Test
    public void test2() {
        jdkSerializer.writeFile(userEntity, file);
        UserEntity userRevEntity = jdkSerializer.readFile(file, UserEntity.class);
        log.info(userRevEntity.toString());
    }

    /**
     * jdk序列化存储规则
     * 同一对象序列化到同一文件，第二次为引用
     */
    @Test
    public void test3() {
        try (
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ){
            objectOutputStream.writeObject(userEntity);
            log.info("file size:{}", file.length());
            objectOutputStream.writeObject(userEntity);
            log.info("file size:{}", file.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
