package com.lehang.web.module.cms.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lehang.web.module.cms.entity.Slideshow;

import net.eulerform.web.core.base.service.IBaseService;

public interface ISlideshowService extends IBaseService {

    public List<Slideshow> loadSlideshow();

    public void saveSlideshow(List<MultipartFile> img, List<String> url);

}
