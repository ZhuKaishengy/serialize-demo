package com.autoai.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : zhukaishengy
 * @date : 2020/7/28 19:17
 * @Description : 实体超类 体验序列化的父子传递关系
 * @version : v1.0
 */
@SuppressWarnings("SerializableHasSerializationMethods")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuperEntity implements Serializable {

    /**
     * 序列化和反序列化过程中不能改变
     */
    private static final long serialVersionUID = -1693946160172152478L;

    /**
     * 超类中的成员变量
     */
    private String num;
}
