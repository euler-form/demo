package net.eulerform.web.module.lh.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.eulerform.web.core.annotation.RestEndpoint;
import net.eulerform.web.core.base.controller.AbstractRestEndpoint;
import net.eulerform.web.core.base.response.WebServiceResponse;
import net.eulerform.web.module.lh.entity.Slideshow;
import net.eulerform.web.module.lh.service.ISlideshowService;

@RestEndpoint
@Scope("prototype")
@RequestMapping("/cms")
public class LHCmsRestEndpoint extends AbstractRestEndpoint {

    @Resource ISlideshowService slideshowService;
    
    @ResponseBody
    @RequestMapping(value = "/findSlideshowAll", method = RequestMethod.GET)
    public WebServiceResponse<Slideshow> findSlideshowAll() {
        return new WebServiceResponse<>(this.slideshowService.loadSlideshow());
    }
}
