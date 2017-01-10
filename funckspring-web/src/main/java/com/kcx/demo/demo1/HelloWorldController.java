package com.kcx.demo.demo1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试不同xml 的引入，不同xml间 bean的注入,结论是:所有xml同时引入不会因为多个  <context:component-scan base-package="com.kcx"></context:component-scan> 冲突而引发注入一场
 * 非同时引入比如servlet和listener两种方式引入
 */
@Controller
public class HelloWorldController {


    @Autowired
    @Qualifier("aBeanInMvc1")
    ABeanInMvc aBeanInMvc1;

    @Autowired
    @Qualifier("aBeanInMvc2")
    ABeanInMvc aBeanInMvc2;

    @Autowired
    @Qualifier("aBeanInMvc3")
    ABeanInMvc aBeanInMvc3;



    /**
     * 1. 使用RequestMapping注解来映射请求的URL
     * 2. 返回值会通过视图解析器解析为实际的物理视图, 对于InternalResourceViewResolver视图解析器，会做如下解析
     * 通过prefix+returnVal+suffix 这样的方式得到实际的物理视图，然后会转发操作
     * "/WEB-INF/views/success.jsp"
     *
     * @return
     */
    @RequestMapping("/helloworld")
    public String hello() {
        System.out.println("hello world" + aBeanInMvc1.getName() + "," + aBeanInMvc2.getName() + "," + aBeanInMvc3.getName());


        return "success";
    }
}