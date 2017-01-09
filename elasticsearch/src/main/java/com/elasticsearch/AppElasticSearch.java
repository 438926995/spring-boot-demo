package com.elasticsearch;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author huwenwen
 * @since 17/1/1
 */
@SpringBootApplication
@Log4j
public class AppElasticSearch implements CommandLineRunner {

  @Autowired
  private CustomerRepository repository;

  @Override
  public void run(String... strings) throws Exception {
    this.repository.deleteAll();
    save();
    fetchAll();
    log.info("-----" + JSON.toJSONString(repository.findById("1")));
  }

  private void save() {
    repository.save(new Customer("1", "wen1", "remark1"));
    repository.save(new Customer("2", "wen2", "remark2"));
  }

  private void fetchAll() {
    for (Customer customer : repository.findAll()) {
      log.info("------" + JSON.toJSONString(customer));
    }
  }

  public static void main(String[] args) {
    SpringApplication.run(AppElasticSearch.class, "--debug").close();
  }

}
