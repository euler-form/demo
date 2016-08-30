package net.eulerform.web.module.lh.controller;

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

import net.eulerform.common.CalendarTool;
import net.eulerform.common.StringTool;
import net.eulerform.web.core.annotation.WebController;
import net.eulerform.web.core.base.controller.AbstractWebController;
import net.eulerform.web.core.base.request.QueryRequest;
import net.eulerform.web.core.base.response.PageResponse;
import net.eulerform.web.module.lh.entity.Position;
import net.eulerform.web.module.lh.entity.Slideshow;
import net.eulerform.web.module.lh.service.IPositionService;
import net.eulerform.web.module.lh.service.ISlideshowService;

@WebController
@Scope("prototype")
@RequestMapping("/cms/manage")
public class LHCmsManageWebController extends AbstractWebController {

    @Resource ISlideshowService slideshowService;
    @Resource IPositionService positionService;
    
    @RequestMapping(value = "/slideshow", method = RequestMethod.GET)
    public String slideshow() {
        return "/cms/manage/slideshow";
    }
    
    @RequestMapping(value = "/position", method = RequestMethod.GET)
    public String position() {
        return "/cms/manage/position";
    }
    
    @ResponseBody
    @RequestMapping(value = "/loadSlideshow", method = RequestMethod.GET)
    public List<Slideshow> loadSlideshow() {
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

    @ResponseBody
    @RequestMapping(value = "/findPositionByPage")
    public PageResponse<Position> findPositionByPage(HttpServletRequest request, int page, int rows) {
        QueryRequest queryRequest = new QueryRequest(request);
        return this.positionService.findPositionByPage(queryRequest, page, rows);
    }
    
    @ResponseBody
    @RequestMapping(value = "/savePosition")
    public void savePosition(@Valid Position position, String pubDateStr) throws ParseException {
        if(!StringTool.isNull(pubDateStr)) {
            position.setPubDate(CalendarTool.parseDate(pubDateStr, "yyyy-MM-dd HH:mm:ss"));
        }
        this.positionService.savePosition(position);
    }
}
