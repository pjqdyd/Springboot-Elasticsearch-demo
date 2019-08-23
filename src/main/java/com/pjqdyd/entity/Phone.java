package com.pjqdyd.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**   
 * @Description:  [手机实体类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Data
@Document(indexName = "phone",type = "docs", shards = 1, replicas = 0)
public class Phone {

    @Id
    Long id;

    //@Field(type = FieldType.Text, analyzer = "ik_max_word") //需要ElasticSearch安装ik分词器插件
    @Field(type = FieldType.Text)
    String title; //标题

    @Field(type = FieldType.Keyword)
    String category;// 分类

    @Field(type = FieldType.Keyword)
    String brand; // 品牌

    @Field(type = FieldType.Double)
    Double price; // 价格

    @Field(index = false, type = FieldType.Keyword)
    String images; // 图片地址

    //空构造器
    public Phone(){}

    //构造器
    public Phone(Long id, String title, String category, String brand, Double price, String images) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.images = images;
    }
}

/**
 * @Document 作用在类，标记实体类为文档对象，一般有两个属性
 * indexName：对应索引库名称
 * type：对应在索引库中的类型
 * shards：分片数量，默认5
 * replicas：副本数量，默认1
 *
 * @Id 作用在成员变量，标记一个字段作为id主键
 *
 * @Field 作用在成员变量，标记为文档的字段，并指定字段映射属性：
 * type：字段类型，取值是枚举：FieldType
 * index：是否索引，布尔类型，默认是true
 * store：是否存储，布尔类型，默认是false
 * analyzer：分词器名称
 */