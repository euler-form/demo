package com.lehang.web.module.lehang.controller;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lehang.web.module.lehang.entity.Collaborator;
import com.lehang.web.module.lehang.entity.News;
import com.lehang.web.module.lehang.entity.SlideshowVO;
import com.lehang.web.module.lehang.service.ICollaboratorService;
import com.lehang.web.module.lehang.service.INewsService;
import com.lehang.web.module.lehang.service.ISlideshowService;

import net.eulerform.common.BeanTool;
import net.eulerform.common.CalendarTool;
import net.eulerform.common.StringTool;
import net.eulerform.web.core.annotation.WebController;
import net.eulerform.web.core.base.controller.BaseController;
import net.eulerform.web.core.base.entity.PageResponse;
import net.eulerform.web.core.base.entity.QueryRequest;
import net.eulerform.web.core.exception.MultipartFileSaveException;
import net.eulerform.web.core.util.WebFileTool;

@WebController
@Scope("prototype")
@RequestMapping("/lehangManage")
public class LeHangWebController extends BaseController {

    @Resource ICollaboratorService collaboratorService;
    @Resource ISlideshowService slideshowService;
    @Resource INewsService newsService;
    
    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String news() {
        return "/lehangManage/news";
    }
    
    @RequestMapping(value = "/collaborator", method = RequestMethod.GET)
    public String collaborator() {
        return "/lehangManage/collaborator";
    }
    
    @RequestMapping(value = "/slideshow", method = RequestMethod.GET)
    public String slideshow() {
        return "/lehangManage/slideshow";
    }
    
    @RequestMapping(value = "/ueditor", method = RequestMethod.GET)
    public String ueditor() {
        return "/lehangManage/ueditor";
    }
    
    @ResponseBody
    @RequestMapping(value ="/findCollaboratorByPage")
    public PageResponse<Collaborator> findCollaboratorByPage(HttpServletRequest request, String page, String rows) {
        QueryRequest queryRequest = new QueryRequest(request);
        
        int pageIndex = Integer.parseInt(page);
        int pageSize = Integer.parseInt(rows);
        return this.collaboratorService.findCollaboratorByPage(queryRequest, pageIndex, pageSize);
    }

    @ResponseBody
    @RequestMapping(value ="/findNewsByPage")
    public PageResponse<News> findNewsByPage(HttpServletRequest request, String page, String rows) {
        QueryRequest queryRequest = new QueryRequest(request);
        
        int pageIndex = Integer.parseInt(page);
        int pageSize = Integer.parseInt(rows);
        return this.newsService.findNewsByPage(queryRequest, pageIndex, pageSize);
    }
    
    @ResponseBody
    @RequestMapping(value = "/saveCollaborator", method = RequestMethod.POST)
    public void collaborator(@RequestParam(value = "logo", required = false) MultipartFile logo, @Valid Collaborator collaborator) throws MultipartFileSaveException {
        BeanTool.clearEmptyProperty(collaborator);
        if(logo != null && logo.getSize() > 0){
            if(collaborator.getId() != null) {
                this.collaboratorService.deleteLogo(collaborator.getId() );
            }
            File savedFile = WebFileTool.saveMultipartFile(logo);
            collaborator.setLogoFileName(savedFile.getName());            
        }
        
        if(!StringTool.isNull(collaborator.getUrl())) {
            String url = collaborator.getUrl();
            
            if(url.indexOf("://") < 0) {
                url = "http://" + url;
                collaborator.setUrl(url);
            }
        }
        this.collaboratorService.saveCollaborator(collaborator);
    }

    @ResponseBody
    @RequestMapping(value = "/saveNews", method = RequestMethod.POST)
    public void saveNews(
            @RequestParam(value = "img", required = false) MultipartFile img,
            @RequestParam(value = "pubDateStr", required = true) String pubDateStr,
            @Valid News news) throws MultipartFileSaveException, ParseException {
        
        news.setPubDate(CalendarTool.parseDate(pubDateStr, "yyyy-MM-dd HH:mm:ss"));
        this.newsService.saveNews(news, img);
    }
    
    @ResponseBody
    @RequestMapping(value = "/deleteCollaborators", method = RequestMethod.POST)
    public void deleteCollaborators(@RequestParam String ids) {
        String[] idArray = ids.trim().replace(" ", "").split(";");
        this.collaboratorService.deleteCollaborators(idArray);
    }
    
    @ResponseBody
    @RequestMapping(value = "/deleteNews", method = RequestMethod.POST)
    public void deleteNews(@RequestParam String ids) {
        String[] idArray = ids.trim().replace(" ", "").split(";");
        this.newsService.deleteNews(idArray);
    }
    
    @ResponseBody
    @RequestMapping(value = "/loadSlideshow", method = RequestMethod.GET)
    public SlideshowVO loadSlideshow() {
        return this.slideshowService.loadSlideshow();
    }
    
    @ResponseBody
    @RequestMapping(value = "/saveSlideshow", method = RequestMethod.POST)
    public void saveSlideshow(
            @RequestParam(value = "img1", required = false) MultipartFile img1,
            @RequestParam(value = "img2", required = false) MultipartFile img2,
            @RequestParam(value = "img3", required = false) MultipartFile img3,
            @RequestParam(value = "img4", required = false) MultipartFile img4,
            @RequestParam(value = "url1", required = false) String url1,
            @RequestParam(value = "url2", required = false) String url2,
            @RequestParam(value = "url3", required = false) String url3,
            @RequestParam(value = "url4", required = false) String url4
        ) {
        List<MultipartFile> img = new ArrayList<>();
        List<String> url = new ArrayList<>();
        img.add(img1);
        img.add(img2);
        img.add(img3);
        img.add(img4);
        url.add(url1);
        url.add(url2);
        url.add(url3);
        url.add(url4);
        this.slideshowService.saveSlideshow(img, url);
    }
}
