package com.mmall.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.mmall.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author panghu
 * @Title: ImmutableExample1
 * @Description: 不可变对象
 * @date 19-2-17 下午7:52
 */
@Slf4j
@NotThreadSafe
public class ImmutableExample1 {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
//        a = 2;
//        b = "3";
//        map = Maps.newHashMap();
        /**
         * final修饰引用数据类型
         * 里面的值是可以修改的，只是对象不可以指向另外一个对象
         * */
        map.put(1, 3);
        log.info("{}", map.get(1));
    }

    /**
     * 如果通过final关键字传入的参数是基本类型，那么也是不允许修改的
     *
     * @param a 这里的参数只能是方法调用中传入的参数了
     */
    private void test(final int a) {
//        a = 1;
    }
}
