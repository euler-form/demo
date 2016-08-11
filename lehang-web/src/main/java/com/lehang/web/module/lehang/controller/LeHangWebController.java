package com.lehang.web.module.lehang.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.eulerform.web.core.annotation.WebController;
import net.eulerform.web.core.base.controller.BaseController;

@WebController
@Scope("prototype")
@RequestMapping("/lehangManage")
public class LeHangWebController extends BaseController {

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String news() {
        System.out.println("inini");
        return "/lehangManage/news";
    }
}
