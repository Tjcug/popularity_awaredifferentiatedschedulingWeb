package com.basic.controller;

import com.basic.service.TBalancinggroupingService;
import com.basic.service.TPkggroupingService;
import com.basic.util.JedisPoolUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;

/**
 * locate com.basic.controller
 * Created by 79875 on 2017/7/14.
 */
public class BaseController {
    protected static final Logger log = LoggerFactory.getLogger(BaseController.class);

    @Value("#{prop.hostname}")
    protected String hostname;
    @Value("#{prop.user}")
    protected String user;
    @Value("#{prop.password}")
    protected String password;

    //后台项目基础url
    protected String mainPath="manage/";

    protected JedisPool jedisPool= JedisPoolUtil.getJedisPoolInstance();

    @ModelAttribute("BasePath")
    public String getBasePath(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getContextPath();
    }

    protected Gson gson = new Gson();

    @Autowired
    protected TBalancinggroupingService balancinggroupingService;

    @Autowired
    protected TPkggroupingService pkggroupingService;
}
