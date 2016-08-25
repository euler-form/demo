package com.lehang.web.module.cms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lehang.web.module.cms.entity.Partner;
import com.lehang.web.module.cms.entity.News;
import com.lehang.web.module.cms.entity.Slideshow;
import com.lehang.web.module.cms.entity.ListResponse;
import com.lehang.web.module.cms.service.IPartnerService;
import com.lehang.web.module.cms.service.INewsService;
import com.lehang.web.module.cms.service.ISlideshowService;

import net.eulerform.web.core.annotation.WebController;
import net.eulerform.web.core.base.controller.BaseController;
import net.eulerform.web.core.base.entity.PageResponse;
import net.eulerform.web.core.base.entity.QueryRequest;

@WebController
@Scope("prototype")
@RequestMapping("/cms")
public class CmsWebController extends BaseController {

    @Resource IPartnerService partnerService;
    @Resource ISlideshowService slideshowService;
    @Resource INewsService newsService;
    
    @ResponseBody
    @RequestMapping(value ="/findPartnerByPage")
    public PageResponse<Partner> findPartnerByPage(HttpServletRequest request, String page, String rows) {
        QueryRequest queryRequest = new QueryRequest(request);
        
        int pageIndex = Integer.parseInt(page);
        int pageSize = Integer.parseInt(rows);
        return this.partnerService.findPartnerByPage(queryRequest, pageIndex, pageSize);
    }
    
    @ResponseBody
    @RequestMapping(value ="/loadPartners")
    public ListResponse<Partner> loadPartners() {
        return new ListResponse<>(this.partnerService.loadPartners());
    }

    @ResponseBody
    @RequestMapping(value ="/findNewsByPage")
    public PageResponse<News> findNewsByPage(HttpServletRequest request, String page, String rows, boolean loadText) {
        QueryRequest queryRequest = new QueryRequest(request);
        
        int pageIndex = Integer.parseInt(page);
        int pageSize = Integer.parseInt(rows);
        return this.newsService.findNewsByPage(queryRequest, pageIndex, pageSize, loadText);
    }
    
    @ResponseBody
    @RequestMapping(value = "/loadSlideshow", method = RequestMethod.GET)
    public ListResponse<Slideshow> loadSlideshow() {
        return new ListResponse<>(this.slideshowService.loadSlideshow());
    }
}
