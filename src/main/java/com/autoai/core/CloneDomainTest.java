package com.autoai.core;

import com.autoai.entity.PersonEntity;
import com.autoai.service.ISerializer;
import com.autoai.service.impl.JdkSerializer;
import com.autoai.value_obj.PersonVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

/**
 * @author : zhukaishengy
 * @date : 2020/7/28 20:25
 * @Description : clone核心域
 * @version : v1.0
 */
@Slf4j
public class CloneDomainTest {

    PersonVO personVO;
    PersonEntity personEntity;
    ISerializer iSerializer;

    @Before
    public void init() {
        personVO = new PersonVO();
        personEntity = personVO.getPersonEntity();
        iSerializer = new JdkSerializer();
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        PersonEntity personEntityClone = personEntity.clone();
        log.info(personEntityClone.toString());
        // 改变引用对象中的值
        personEntity.getEmailEntity().setContent("456");
        log.info(personEntityClone.toString());
    }

    @Test
    public void testDeepClone() {
        PersonEntity personEntityClone = iSerializer.deserialize(iSerializer.serialize(personEntity), PersonEntity.class);
        log.info(personEntityClone.toString());
        // 改变引用对象中的值
        personEntity.getEmailEntity().setContent("456");
        log.info(personEntityClone.toString());
    }
}
