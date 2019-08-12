package com.mmall.concurrency.example.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author panghu
 * @Title: CacheController
 * @ProjectName example
 * @Description: TODO
 * @date 19-3-1 下午4:33
 */
@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private RedisClient redisClient;

    @RequestMapping("/set")
    public String set(@RequestParam("k") String k, @RequestParam("v") String v) throws Exception {
        redisClient.set(k, v);
        return "SUCCESS";
    }

    @RequestMapping("/get")
    public String get(@RequestParam("k") String k) throws Exception {
        return redisClient.get(k);
    }

}
