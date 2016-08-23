package com.lehang.web.module.lehang.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lehang.web.module.lehang.dao.INewsDao;
import com.lehang.web.module.lehang.entity.News;
import com.lehang.web.module.lehang.service.INewsService;

import net.eulerform.common.BeanTool;
import net.eulerform.web.core.base.entity.PageResponse;
import net.eulerform.web.core.base.entity.QueryRequest;
import net.eulerform.web.core.base.service.impl.BaseService;

@Service
public class NewsService extends BaseService implements INewsService {

    @Resource INewsDao newsDao;
    
    @Override
    public void saveNews(News news) {
        BeanTool.clearEmptyProperty(news);
        if(news.getPubDate() == null)
            news.setPubDate(new Date());

        if(news.getTop() == null)
            news.setTop(false);
        
        this.newsDao.saveOrUpdate(news);
    }

    @Override
    public PageResponse<News> findNewsByPage(QueryRequest queryRequest, int pageIndex, int pageSize) {
        return this.newsDao.findNewsByPage(queryRequest, pageIndex, pageSize);
    }

    @Override
    public void deleteNewsImg(String newsId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteNews(String[] idArray) {
        this.newsDao.deleteByIds(idArray);
    }

}
