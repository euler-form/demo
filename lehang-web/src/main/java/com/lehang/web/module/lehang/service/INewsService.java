package com.lehang.web.module.lehang.service;

import com.lehang.web.module.lehang.entity.News;

import net.eulerform.web.core.base.entity.PageResponse;
import net.eulerform.web.core.base.entity.QueryRequest;
import net.eulerform.web.core.base.service.IBaseService;

public interface INewsService extends IBaseService {

    public void saveNews(News news);

    public PageResponse<News> findNewsByPage(QueryRequest queryRequest, int pageIndex, int pageSize);

    public void deleteNewsImg(String newsId);

    public void deleteNews(String[] idArray);

}
