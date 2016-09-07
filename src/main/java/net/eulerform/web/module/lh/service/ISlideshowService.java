package net.eulerform.web.module.lh.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;

import net.eulerform.web.core.base.service.IBaseService;
import net.eulerform.web.module.lh.entity.Slideshow;

@PreAuthorize("isFullyAuthenticated() and hasAnyAuthority('CMS_ADMIN','ADMIN','SYSTEM')")
public interface ISlideshowService extends IBaseService {

    public List<Slideshow> loadSlideshow();
    
    @PreAuthorize("permitAll")
    public List<Slideshow> loadSlideshowByOrder();

    public void saveSlideshow(List<MultipartFile> img, List<String> url);


}