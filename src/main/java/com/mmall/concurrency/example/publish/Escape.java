package com.mmall.concurrency.example.publish;

import com.mmall.concurrency.annoations.NotRecommend;
import com.mmall.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author panghu
 * @Title: Escape
 * @Description: TODO
 * @date 19-2-17 下午2:48
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape() {
        new InnerClass();
    }

    private class InnerClass{

        public InnerClass() {
            log.info("{}" ,Escape.this.thisCanBeEscape);
        }

    }

    public static void main(String[] args) {
        new Escape();
    }
}
