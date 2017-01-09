package com.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author huwenwen
 * @since 17/1/1
 */
public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {

  Customer findById(String id);

  List<Customer> findByName(String name);

}
