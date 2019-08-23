package com.pjqdyd.repository;

import com.pjqdyd.entity.Phone;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**   
 * @Description:  [Phone的Repository层]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public interface PhoneRepository extends ElasticsearchRepository<Phone, Long> {

    /**
     * 根据价格区间查询
     * @param price1
     * @param price2
     * @return
     */
    List<Phone> findByPriceBetween(double price1, double price2);

}
