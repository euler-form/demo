package net.eulerform.web.module.lh.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.eulerform.web.core.annotation.RestEndpoint;
import net.eulerform.web.core.base.controller.AbstractRestEndpoint;
import net.eulerform.web.core.base.request.QueryRequest;
import net.eulerform.web.core.base.response.WebServicePageResponse;
import net.eulerform.web.core.base.response.WebServiceResponse;
import net.eulerform.web.module.lh.entity.HotProject;
import net.eulerform.web.module.lh.entity.Position;
import net.eulerform.web.module.lh.entity.Slideshow;
import net.eulerform.web.module.lh.service.IHotProjectService;
import net.eulerform.web.module.lh.service.IPositionService;
import net.eulerform.web.module.lh.service.ISlideshowService;

@RestEndpoint
@Scope("prototype")
@RequestMapping("/cms")
public class LHCmsRestEndpoint extends AbstractRestEndpoint {

    @Resource IHotProjectService hotProjectService;
    @Resource ISlideshowService slideshowService;
    @Resource IPositionService positionService;
    
    @ResponseBody
    @RequestMapping(value = "/findSlideshow", method = RequestMethod.GET)
    public WebServiceResponse<Slideshow> findSlideshow() {
        return new WebServiceResponse<>(this.slideshowService.loadSlideshowByOrder());
    }
    
    @ResponseBody
    @RequestMapping(value = "/findHotProject", method = RequestMethod.GET)
    public WebServiceResponse<HotProject> findHotProject() {
        return new WebServiceResponse<>(this.hotProjectService.loadHotProjectByOrder());
    }  

    @ResponseBody
    @RequestMapping(value ="/findPositionByPage", method = RequestMethod.GET)
    public WebServicePageResponse<Position> findPositionByPage(HttpServletRequest request, int pageIndex, int pageSize) {
        QueryRequest queryRequest = new QueryRequest(request);
        return new WebServicePageResponse<>(this.positionService.findPositionByPage(queryRequest, pageIndex, pageSize));
    }  

    @ResponseBody
    @RequestMapping(value ="/position/{positionId}", method = RequestMethod.GET)
    public WebServiceResponse<Position> findPositionById(@PathVariable("positionId") String positionId) {
        return new WebServiceResponse<>(this.positionService.findPosition(positionId));
    }  

    @ResponseBody
    @RequestMapping(value ="/findHotPositon", method = RequestMethod.GET)
    public WebServiceResponse<Position> findHotPositon() {
        return new WebServiceResponse<>(this.positionService.findHotPositon());
    }
}
