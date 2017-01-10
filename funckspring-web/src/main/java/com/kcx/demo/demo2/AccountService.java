package com.kcx.demo.demo2;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;

import java.io.Serializable;

@Controller
public class AccountService {

    private final Logger logger = LoggerFactory.getLogger(AccountService.class);

    /**
     *
     * @param para
     * @return
     */
    @Cacheable(value = "default")
    public  Account getAccountByJson(JSONObject para){
        String accountName=para.getString("accountName");
        logger.info("real querying account... {}", accountName);
        Optional<Account> accountOptional = getFromDB(accountName);
        if (!accountOptional.isPresent()) {
            throw new IllegalStateException(String.format("can not find account by account name : [%s]", accountName));
        }

        return accountOptional.get();
    }

    // 使用了一个缓存名叫 accountCache
    @Cacheable(value = "default")
    public Account getAccountByName(String accountName) {

        // 方法内部实现不考虑缓存逻辑，直接实现业务
        logger.info("real querying account... {}", accountName);
        Optional<Account> accountOptional = getFromDB(accountName);
        if (!accountOptional.isPresent()) {
            throw new IllegalStateException(String.format("can not find account by account name : [%s]", accountName));
        }

        return accountOptional.get();
    }

    @CacheEvict(value = "default", key = "#account.getName()")
    public void updateAccount(Account account) {
        updateDB(account);
    }

    @CacheEvict(value = "default", allEntries = true)
    public void reload() {
    }

    private void updateDB(Account account) {
        logger.info("real update db...{}", account.getName());
    }

    private Optional<Account> getFromDB(String accountName) {
        logger.info("real querying db... {}", accountName);
        //Todo query data from database
        return Optional.fromNullable(new Account(accountName));
    }
}

class Account implements Serializable {
    public Account(String name) {
        this.name = name;
    }


    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}