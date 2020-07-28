package com.autoai.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : zhukaishengy
 * @date : 2020/7/28 20:04
 * @Description : 实体
 * @version : v1.0
 */
@SuppressWarnings("SerializableHasSerializationMethods")
@Data
@Builder
public class EmailEntity implements Cloneable, Serializable {

    private static final long serialVersionUID = 2534831133751596598L;
    private String content;
}
