package com.ki5s.base.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by BaoZhuo on 2016/9/2.
 */
@Controller
@RequestMapping
public class BaseController {
    private static Log log = LogFactory.getLog(BaseController.class);

    /**
     * 网站首页
     * @return
     */
    @RequestMapping(value = "/index.html",method = RequestMethod.GET)
    public ModelAndView index(){
        log.info("进入首页1");
        log.debug("进入首页2");
        log.error("进入首页3");
        ModelAndView view = new ModelAndView();
        view.setViewName("/index");
        return view;
    }



}
