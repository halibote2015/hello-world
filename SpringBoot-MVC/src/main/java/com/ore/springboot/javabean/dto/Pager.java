package com.ore.springboot.javabean.dto;

import lombok.Data;

import java.util.List;

/**
 * @ClassName Pager
 * @Description 分页
 * @Author 37052
 * @Date 2020-07-28 13:38
 * @Version 1.0
 **/
@Data
public class Pager<T> {
  // 分页大小
  private int pageSize;
  // 分页号
  private int pageNo;
  // 总数
  private int total;
  // 分页总数
  private int totalPage;
  /**
   * 数据
   */
  private List<T> list;

  public Pager(int total, int pageSize, int pageNo, List<T> list){
    this.total = total;
    this.pageSize = pageSize;
    this.pageNo = pageNo;
    this.totalPage = (int)(Math.ceil(total / pageSize));
    this.list = list;
  }
}
