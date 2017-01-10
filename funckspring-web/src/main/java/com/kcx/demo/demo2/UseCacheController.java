package com.kcx.demo.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试使用 cache
 * Created by JimKan on 2016/11/22.
 */
@Controller
public class UseCacheController {

    @Autowired
    AccountService accountService;

    @RequestMapping("/hellocache")
    public String hello() {
        Account account=null;
        try {
            account = accountService.getAccountByName("张三");
        }catch (Exception ex){
            ex.printStackTrace();
        }
         System.out.println(account.getName()+","+account.getId());

        account =   accountService.getAccountByName("张三");
        System.out.println(account.getName()+","+account.getId());

        account.setId(22222);
        accountService.updateAccount(account);

        account =   accountService.getAccountByName("张三");
        System.out.println(account.getName()+","+account.getId());


        return "success";
    }

}
