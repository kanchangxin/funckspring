package com.kcx.demo.demo3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by JimKan on 2016/11/22.
 */
@Controller
public class CustomerCacheController {


    @Autowired
    CacheManager cacheManager;


    @RequestMapping("/customercache")
    public String hello() {
        try {

            Cache cache = cacheManager.getCache("user");
            cache.put("inUserKey", "aaa vale ");
            System.out.println("user cache -inUserKey ：" + cache.get("inUserKey").get());

            Cache cache2 = cacheManager.getCache("default");
            cache2.put("inDefualtKey", "在inDefualtKey中的值");
            System.out.println("default cache -inDefualtKey： " + cache2.get("inDefualtKey").get());


            cache2.clear();

            Cache.ValueWrapper ww=cache2.get("inDefualtKey");
            System.out.println("清空defualt的缓存 ");
            System.out.println("default cache -inDefualtKey，应该受影响" + ww);
            System.out.println("user cache -inUserKey ,应该不收影响 ：" + cache.get("inUserKey").get());



            return "success";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
