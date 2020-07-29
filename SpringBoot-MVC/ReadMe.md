## 一、Springboot 静态资源配置
1. 静态资源路径是指系统可以直接访问的路径，且路径下所有文件均可被用户通过浏览器直接读取
2. Springboot中默认的静态资源路径有：classpath:/META-INF/resources/，classpath:/resources/，classpath:/static/，classpath:/public/
3. 在Springboot中可以直接在配置文件中覆盖默认的静态资源路径的配置信息：
```$xslt
#自定义的属性，指定了一个路径，注意要以/结尾
web.upload-path=D:/temp/study13/

#表示所有的访问都经过静态资源路径
spring.mvc.static-path-pattern=/**

#覆盖默认配置，所以需要将默认的也加上否则static、public等这些路径将不能被当作静态资源路径
#在最末尾的file:${web.upload-path}中的file:表示是一个具体的硬盘路径，其他的使用classpath指的是系统环境变量
spring.resources.static-locations=classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/,classpath:/public/,file:${web.upload-path}

```
4. 在Springboot开发中，可通过java代码覆盖默认静态资源类路径
```$xslt

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        if(!registry.hasMappingForPattern("/static/**")){
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        }
        super.addResourceHandlers(registry);
    }

}
```
 5、由于Spring Boot 默认资源路径配置的问题，使用IDEA开发Spring Boot应用时，会导致一个问题————浏览器、编辑器 不能同时访问 JS 等资源的问题。这时往往通过配置 4 中的代码，来实现同时访问资源文件的效果
 
 
 ## 二、Ajax 请求头中常用的Content-Type 以及后端接口对应的接收参数方式方法
 1. application/x-www-form-urlencoded
 2. multipart/form-data
 3. application/json
 4. _text/html_