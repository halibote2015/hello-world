package com.ore.springboot.controller;

import com.ore.springboot.javabean.po.News;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName ApiController
 * @Description springboot controller 业务层典型写法，演示如何接收来自浏览器参数
 * @Author 37052
 * @Date 2020-07-28 11:15
 * @Version 1.0
 **/
//@Slf4j
@RestController
@Api(value = "/api", description = "api演示接口")
@RequestMapping("/api")
public class ApiController extends BasicController{

  @RequestMapping("/getNews")
  public String getNews(){
    return toResponse(null);
  }

  /**
   * @Author Ray
   * @Description 分页获取新闻列表
   * @Date 2020-07-29 10:23
   * @Param [pageSize, pageNo]
   * @return java.lang.String
   */
  @RequestMapping("/getNewsPage")
  public String getNews(int pageSize, int pageNo){
    return toResponse(null);
  }

  /**
   * @Author Ray
   * @Description 获取新闻详情
   * @Date 2020-07-29 10:22
   * @Param [id]
   * @return java.lang.String
   */
  @RequestMapping("/getNewsDetail/{id}")
  public String getNewsDetail(@PathVariable(value="id") String id){
    return toResponse(null);
  }

  /**
   * @Author Ray
   * @Description 删除新闻
   * @Date 2020-07-29 10:22
   * @Param [ids]
   * @return java.lang.String
   */
  @RequestMapping("/deleteNews")
  public String deleteNews(@RequestParam(value="ids[]") List<Integer> ids){
    return toResponse(null);
  }

  /**
   * @Author Ray
   * @Description 批量添加新闻，
   * @Date 2020-07-29 10:22
   * @Param [newsList]
   * @return java.lang.String
   */
  @RequestMapping("/addNews")
  public String addNews(@RequestParam(value="newsList[]") List<News> newsList){
    return toResponse(null);
  }

  /**
   * @Author Ray
   * @Description 更新新闻
   * @Date 2020-07-29 10:23
   * @Param [news]
   * @return java.lang.String
   */
  @RequestMapping("/updateNews")
  public String updateNews(@RequestBody News news){
    return toResponse(null);
  }
}
