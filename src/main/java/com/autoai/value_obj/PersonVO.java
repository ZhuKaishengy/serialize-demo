package com.autoai.value_obj;

import com.autoai.entity.EmailEntity;
import com.autoai.entity.PersonEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhukaishengy
 * @date : 2020/7/28 20:05
 * @Description : 值对象
 * @version : v1.0
 */
public class PersonVO {

    public static final List<EmailEntity> EMAIL_ENTITIES = new ArrayList<>(2);

    static {
        EMAIL_ENTITIES.add(EmailEntity.builder().content("123").build());
        EMAIL_ENTITIES.add(EmailEntity.builder().content("456").build());
    }

    public PersonEntity getPersonEntity() {
        return PersonEntity.builder().name("zks").emailEntity(EMAIL_ENTITIES.get(0)).build();
    }
}
