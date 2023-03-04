package xyr.riun.webcommon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyr.riun.webcommon.exception.ApiAssert;
import xyr.riun.webcommon.service.TestService;
import xyr.riun.webcommon.wrapper.annotation.ResultUnite;

/**
 * @author: framework codegen
 * @date: 2021-05-17
 * 不要将此类放在controller包下，否则因aop记录请求过程，而容器云一直在调hs保证服务正常，所以会一直记录hs的日志。
 */
@RestController
public class HsController {

    @Autowired
    private TestService testService;

    @ResultUnite
    @RequestMapping(value = "/hs", method = RequestMethod.GET)
    public String hs(){
        return "ok";
    }

    @ResultUnite
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        int i = 1 / 0;
        return "ok";
    }

    @ResultUnite
    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String test2(){
        ApiAssert.badRequest("错误");
        return "ok";
    }

    @ResultUnite
    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public String test3(){
        testService.asyncDbLogDown();
        return "ok";
    }

    @ResultUnite
    @RequestMapping(value = "/test4", method = RequestMethod.GET)
    public String test4(){
        testService.test();
        return "ok";
    }
}
