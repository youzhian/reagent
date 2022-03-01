package com.ylv.dict;

import com.ylv.modules.dict.bean.DictType;
import com.ylv.modules.dict.service.DictTypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DictTypeServiceTest {

    @Autowired
    private DictTypeService dictTypeService;

    @Test
    public void testAdd(){

        DictType dictType = new DictType();

        dictType.setDictType("REAGENT_TYPE");
        dictType.setLabel("试剂类型");
        dictType.setDictDesc("试剂分类");
        dictType.setOrderNum(1);
        dictType.setCreateTime(new Date());
        dictType.setUpdateTime(new Date());

        dictTypeService.save(dictType);
    }
}
