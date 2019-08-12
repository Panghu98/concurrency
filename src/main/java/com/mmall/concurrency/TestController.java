package com.mmall.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author panghu
 * @Title: TestController
 * @ProjectName concurrency_demo
 * @Description: TODO
 * @date 19-2-15 下午4:00
 */
@RestController
@Slf4j
public class TestController {

    @RequestMapping("/test")
    public String test() {
        return "test success";
    }

}
