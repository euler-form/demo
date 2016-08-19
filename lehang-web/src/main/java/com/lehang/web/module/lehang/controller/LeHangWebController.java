package com.lehang.web.module.lehang.controller;

import java.io.File;

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
        
        this.collaboratorService.saveCollaborator(collaborator);
    }

    @ResponseBody
    @RequestMapping(value = "/saveNews", method = RequestMethod.POST)
    public void saveNews(
            @RequestParam(value = "img", required = false) MultipartFile img,
            @RequestParam(value = "editorValue", required = true) String text,
            @Valid News news) throws MultipartFileSaveException {
        
        if(img != null && img.getSize() > 0){
            if(news.getId() != null) {
                this.newsService.deleteNewsImg(news.getId() );
            }
            File savedFile = WebFileTool.saveMultipartFile(img);
            news.setImageFileName(savedFile.getName());            
        }
        news.setText(text);
        this.newsService.saveNews(news);
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
            @RequestParam(value = "url1", required = false) String url1,
            @RequestParam(value = "url2", required = false) String url2,
            @RequestParam(value = "url3", required = false) String url3
        ) {
        this.slideshowService.saveSlideshow(img1, url1, img2, url2, img3, url3);
    }
}
