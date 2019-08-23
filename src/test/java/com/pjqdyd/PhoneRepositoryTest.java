package com.pjqdyd;

import com.pjqdyd.entity.Phone;
import com.pjqdyd.repository.PhoneRepository;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**   
 * @Description:  [PhoneRepository的测试类]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PhoneRepositoryTest {

    @Autowired
    private PhoneRepository phoneRepository;

    /**
     * 新增测试 (http://127.0.0.1:9200/phone/_search查看)
     */
    @Test
    public void createTest(){
        Phone phone = new Phone();
        phone.setId(1L);
        phone.setTitle("小米手机9");
        phone.setBrand("小米");
        phone.setCategory("手机");
        phone.setPrice(2999.00);
        phone.setImages("http://image/xiaomi.png");

        Phone result = phoneRepository.save(phone);
        System.out.println(result);
        Assert.assertNotNull(result);
    }

    /**
     * 批量新增测试
     */
    @Test
    public void createListTest() {
        List<Phone> list = new ArrayList<>();
        list.add(new Phone(4L, "坚果手机R3", " 手机", "锤子", 5699.00, "http://image.zq/1.jpg"));
        list.add(new Phone(5L, "华为P10", " 手机", "华为", 3499.00, "http://image.zq.com/3.jpg"));
        // 接收对象集合，实现批量新增
        Iterable<Phone> results = phoneRepository.saveAll(list);
        Iterator res = results.iterator(); //获取结果迭代器对象
        while (res.hasNext()){
            System.out.println(res.next());
        }

        Assert.assertNotNull(results);
    }

    /**
     *  查询所有测试
     */
    @Test
    public void findAllTest(){
        // 查询全部，并安装价格降序排序
        Iterable<Phone> results = phoneRepository.findAll(Sort.by(Sort.Direction.DESC, "price"));
        results.forEach(phone -> System.out.println(phone));
        Assert.assertNotNull(results);
    }

    /**
     * 区间查询测试
     */
    @Test
    public void findByPriceBetweenTest(){
        List<Phone> list = phoneRepository.findByPriceBetween(2000.00, 3500.00);
        for (Phone phone : list) {
            System.out.println(phone);
        }
    }

    /**
     * 基本查询测试
     */
    @Test
    public void queryTest(){
        // 词条查询
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("title", "小米");
        // 执行查询
        Iterable<Phone> results = phoneRepository.search(queryBuilder);
        results.forEach(System.out::println);
    }

    /**
     * 自定义查询测试
     */
    @Test
    public void nativeQueryTest(){
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本的分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("title", "小米"));
        // 执行搜索，获取结果
        Page<Phone> results = phoneRepository.search(queryBuilder.build());
        // 打印总条数
        System.out.println(results.getTotalElements());
        // 打印总页数
        System.out.println(results.getTotalPages());
        results.forEach(System.out::println);
    }

    /**
     * 自定义分页排序查询测试
     */
    @Test
    public void nativeQueryPageTest(){
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本的分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("category", "手机"));
        // 初始化分页参数
        int page = 0;
        int size = 3;
        // 设置分页参数
        queryBuilder.withPageable(PageRequest.of(page, size));
        // 排序
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        // 执行搜索，获取结果
        Page<Phone> results = phoneRepository.search(queryBuilder.build());
        // 打印总条数
        System.out.println(results.getTotalElements());
        // 打印总页数
        System.out.println(results.getTotalPages());
        results.forEach(System.out::println);
    }

}
