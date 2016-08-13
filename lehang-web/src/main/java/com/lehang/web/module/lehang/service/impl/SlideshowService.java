package com.lehang.web.module.lehang.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lehang.web.module.lehang.dao.ISlideshowDao;
import com.lehang.web.module.lehang.entity.Slideshow;
import com.lehang.web.module.lehang.entity.SlideshowVO;
import com.lehang.web.module.lehang.service.ISlideshowService;

import net.eulerform.common.FileReader;
import net.eulerform.common.GlobalProperties;
import net.eulerform.common.GlobalPropertyReadException;
import net.eulerform.common.StringTool;
import net.eulerform.web.core.base.service.impl.BaseService;
import net.eulerform.web.core.util.WebFileTool;

@Service
public class SlideshowService extends BaseService implements ISlideshowService {
    
    @Resource private ISlideshowDao slideshowDao;

    @Override
    public SlideshowVO loadSlideshow() {
        List<Slideshow> data = this.slideshowDao.loadSlideshow();
        SlideshowVO ret = new SlideshowVO();
        ret.setSlideshows(data);
        return ret;
    }

    @Override
    public void saveSlideshow(MultipartFile img1, String url1, MultipartFile img2, String url2, MultipartFile img3,
            String url3) {
        this.saveSlideshowByOrder(img1, url1, 1);
        this.saveSlideshowByOrder(img2, url2, 2);
        this.saveSlideshowByOrder(img3, url3, 3);
    }
    
    private void saveSlideshowByOrder(MultipartFile img, String url, int order){
        if(img != null && img.getSize() > 0){
            Slideshow slideshow = this.slideshowDao.findSlideshowByOrder(order);
            
            if(slideshow == null) {
                slideshow = new Slideshow();
                slideshow.setOrder(order);
            }
            slideshow.setUrl(url);
            try {
                String uploadPath = this.getServletContext().getRealPath(GlobalProperties.get(GlobalProperties.UPLOAD_PATH));
                
                //删除旧图片
                if(!StringTool.isNull(slideshow.getId())) {
                    String filePath = uploadPath+"/"+slideshow.getImgFileName();
                    FileReader.deleteFile(new File(filePath));
                }
                
                File savedFile = WebFileTool.saveMultipartFile(img);
                slideshow.setImgFileName(savedFile.getName());
            } catch (IllegalStateException | IOException | GlobalPropertyReadException e) {
                throw new RuntimeException(e);
            }
            this.slideshowDao.saveOrUpdate(slideshow);
        }
        
    }
}
