package com.pjqdyd;

import com.pjqdyd.entity.Phone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**   
 * @Description:  [创建索引和映射相关测试类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class IndexTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    //创建索引测试(Get访问http://127.0.0.1:9200/phone查看)
    @Test
    public void createTest(){
        // 创建索引，会根据Phone类的@Document注解信息来创建
        elasticsearchTemplate.createIndex(Phone.class);

        // 配置映射，会根据Phone类中的id、Field等字段来自动完成映射
        elasticsearchTemplate.putMapping(Phone.class);
    }

    //删除索引测试
    @Test
    public void deleteTest(){
        //根据类名或索引名删除
        elasticsearchTemplate.deleteIndex(Phone.class);
    }

}
