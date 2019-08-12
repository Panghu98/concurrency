package com.mmall.concurrency.example.threadLocal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author panghu
 * @Title: ThreadLocalController
 * @ProjectName cuncurrency_demo
 * @Description: TODO
 * @date 19-2-19 下午10:02
 */
@Controller
@RequestMapping("/threadLocal")
public class ThreadLocalController {

    @RequestMapping("/test")
    @ResponseBody
    public Long test() {
        return RequestHolder.getId();
    }

}
