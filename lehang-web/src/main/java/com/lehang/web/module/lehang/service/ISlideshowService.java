package com.lehang.web.module.lehang.service;

import org.springframework.web.multipart.MultipartFile;

import com.lehang.web.module.lehang.entity.SlideshowVO;

import net.eulerform.web.core.base.service.IBaseService;

public interface ISlideshowService extends IBaseService {

    public SlideshowVO loadSlideshow();

    public void saveSlideshow(MultipartFile img1, String url1, MultipartFile img2, String url2, MultipartFile img3,
            String url3);

}
