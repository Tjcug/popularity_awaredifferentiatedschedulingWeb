package com.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dell-pc on 2016/4/22.
 */

@Controller
public class WebController extends BaseController{

    @RequestMapping(value = "/")
    public String index(){
        return mainPath+"loadbalancing";
    }

    @RequestMapping(value = "/throughput")
    public String throughput(){
        return mainPath+"throughput";
    }

    @RequestMapping(value = "/temp")
    public String temp(){
        return "temp";
    }

    @RequestMapping("/manage_{var1}_{var2}")
    public String sendFunc(@PathVariable("var1") String var1, @PathVariable("var2") String var2){
        return mainPath+var1+"/"+var2;
    }
}
