package com.mmall.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * @author panghu
 * @Title: ImmutableExample1
 * @Description: TODO
 * @date 19-2-17 下午7:52
 */
@Slf4j
@ThreadSafe
public class ImmutableExample2 {

    private static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        //如果注释掉下面这一句,那么操作就是允许的
//        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1, 3);
        log.info("{}", map.get(1));
    }

}
