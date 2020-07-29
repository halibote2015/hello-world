package com.ore.springboot.javabean.po;

import lombok.Data;

/**
 * @ClassName News
 * @Description 新闻实体
 * @Author 37052
 * @Date 2020-07-28 13:36
 * @Version 1.0
 **/
@Data
public class News {

  private int id;

  private String title;

  private String subTitle;

  private String author;

  private String kind;

  private String comeFrom;
}
