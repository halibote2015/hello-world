package com.ore.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @ClassName ApplicationStarter
 * @Description TODO
 * @Author 37052
 * @Date 2020-07-29 10:53
 * @Version 1.0
 **/
@SpringBootApplication()
public class ApplicationStarter {
  public static void main(String[] args) {
    SpringApplication.run(ApplicationStarter.class, args);
  }
}
