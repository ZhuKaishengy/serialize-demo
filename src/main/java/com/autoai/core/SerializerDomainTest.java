package com.autoai.core;

import com.autoai.entity.HobbyEntity;
import com.autoai.entity.UserEntity;
import com.autoai.service.ISerializer;
import com.autoai.service.impl.HessianSerializer;
import com.autoai.service.impl.JdkSerializer;
import com.autoai.service.impl.JsonSerializer;
import com.autoai.service.impl.XmlSerializer;
import com.autoai.value_obj.StudentProtos;
import com.google.protobuf.InvalidProtocolBufferException;
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
    ISerializer xmlSerializer;
    ISerializer jsonSerializer;
    ISerializer hessianSerializer;
    StudentProtos.StudentVo studentVo;
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
        xmlSerializer = new XmlSerializer();
        jsonSerializer = new JsonSerializer();
        hessianSerializer = new HessianSerializer();

        studentVo = StudentProtos.StudentVo.newBuilder().setName("zks").setAge(10).build();
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

    /**
     * 使用xml序列化
     */
    @Test
    public void test4() {
        byte[] bytes = xmlSerializer.serialize(userEntity);
        UserEntity.setId("1002");
        log.info(new String(bytes));
        UserEntity entity = xmlSerializer.deserialize(bytes, UserEntity.class);
        // 序列化之后更改的静态变量，也随之改变了，说明，序列化时不会序列化静态变量
        log.info("entity:{}, id:{}, num:{}", entity.toString(), UserEntity.getId(), userEntity.getNum());
    }

    /**
     * 使用Json序列化
     */
    @Test
    public void test5() {
        byte[] bytes = jsonSerializer.serialize(userEntity);
        UserEntity.setId("1002");
        log.info(new String(bytes));
        UserEntity entity = jsonSerializer.deserialize(bytes, UserEntity.class);
        // 序列化之后更改的静态变量，也随之改变了，说明，序列化时不会序列化静态变量
        log.info("entity:{}, id:{}, num:{}", entity.toString(), UserEntity.getId(), userEntity.getNum());
    }

    /**
     * 使用Hessian序列化
     * Hessian的一个bug，对JDK1.8新增时间类型序列化 StackOverflowError
     */
    @Test
    public void test6() {
        byte[] bytes = hessianSerializer.serialize(userEntity);
        UserEntity.setId("1002");
        log.info(new String(bytes));
        UserEntity entity = hessianSerializer.deserialize(bytes, UserEntity.class);
        // 序列化之后更改的静态变量，也随之改变了，说明，序列化时不会序列化静态变量
        log.info("entity:{}, id:{}, num:{}", entity.toString(), UserEntity.getId(), userEntity.getNum());
    }

    @Test
    public void test7() throws InvalidProtocolBufferException {
        byte[] bytes = studentVo.toByteArray();
        log.info("getSerializedSize:{}, length:{}", studentVo.getSerializedSize(), bytes.length);
        StudentProtos.StudentVo studentVoNew = StudentProtos.StudentVo.parseFrom(bytes);
        log.info(studentVoNew.toString());
    }
}
