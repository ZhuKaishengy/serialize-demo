package com.autoai.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : zhukaishengy
 * @date : 2020/7/28 19:23
 * @Description : 体验引用类型实体序列化
 * @version : v1.0
 */
@SuppressWarnings("SerializableHasSerializationMethods")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HobbyEntity implements Serializable {

    private static final long serialVersionUID = -4797586556025555018L;

    private String name;
}
