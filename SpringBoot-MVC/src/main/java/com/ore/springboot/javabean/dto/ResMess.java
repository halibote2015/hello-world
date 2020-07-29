package com.ore.springboot.javabean.dto;

import lombok.Data;

/**
 * @ClassName ResMess
 * @Description 包装返回页面数据
 * @Author 37052
 * @Date 2020-07-28 13:37
 * @Version 1.0
 **/
@Data
public class ResMess {

  private int code;

  private String msg;

  private Object data;

  public ResMess(int code, String msg, Object data){
    this.code = code;
    this.msg = msg;
    this.data = data;
  }
}
