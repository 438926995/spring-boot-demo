package com.elasticsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author huwenwen
 * @since 17/1/1
 */
@Data
@Document(indexName = "customer", type = "customer", shards = 1, replicas = 0, refreshInterval = "-1")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

  @Id
  private String id;
  private String name;
  private String remark;

}
