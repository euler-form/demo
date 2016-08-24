package com.lehang.web.module.lehang.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lehang.web.module.lehang.entity.SlideshowVO;

import net.eulerform.web.core.base.service.IBaseService;

public interface ISlideshowService extends IBaseService {

    public SlideshowVO loadSlideshow();

    public void saveSlideshow(List<MultipartFile> img, List<String> url);

}
