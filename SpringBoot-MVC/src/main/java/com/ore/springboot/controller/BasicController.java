package com.ore.springboot.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.ore.springboot.javabean.dto.ResMess;
import com.ore.springboot.util.ResCodes;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

/**
 * @ClassName BasicController
 * @Description TODO
 * @Author 37052
 * @Date 2020-07-28 13:46
 * @Version 1.0
 **/
public class BasicController {

  protected final Logger log = Logger.getLogger(getClass().getName());

  @Autowired
  protected HttpServletRequest request;

  @Autowired
  protected HttpSession session;

  protected Gson gson = new GsonBuilder().enableComplexMapKeySerialization()
      .setLongSerializationPolicy(LongSerializationPolicy.DEFAULT)
      .serializeSpecialFloatingPointValues().serializeNulls()
      .setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().create();

  /**
   * @Author Ray
   * @Description //TODO
   * @Date 2020-07-29 10:06
   * @Param [code, mes, data]
   * @return java.lang.String
   */
  protected String toResponse(int code, String mes, Object data){
    return gson.toJson(new ResMess(code, mes, data));
  }
  /**
   * @Author Ray
   * @Description //TODO
   * @Date 2020-07-28 14:31
   * @Param [code, mes]
   * @return java.lang.String
   */
  protected String toResponse(int code, String mes){
    return gson.toJson(new ResMess(code, mes, null));
  }
  
  /**
  *
  * @param: 
  * @author: ray
  * @create: 2020-07-28 14:33
  **/
  protected String toResponse(Object data){
    return gson.toJson(new ResMess(ResCodes.SUCCESS, null, data));
  }

}
