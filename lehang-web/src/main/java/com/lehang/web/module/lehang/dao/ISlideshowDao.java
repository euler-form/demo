package com.lehang.web.module.lehang.dao;

import java.util.List;

import com.lehang.web.module.lehang.entity.Slideshow;

import net.eulerform.web.core.base.dao.IBaseDao;

public interface ISlideshowDao extends IBaseDao<Slideshow> {

    public Slideshow findSlideshowByOrder(int order);

    public List<Slideshow> loadSlideshow();

}
