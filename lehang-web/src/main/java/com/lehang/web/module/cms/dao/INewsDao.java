package com.lehang.web.module.cms.dao;

import com.lehang.web.module.cms.entity.News;

import net.eulerform.web.core.base.dao.IBaseDao;
import net.eulerform.web.core.base.entity.PageResponse;
import net.eulerform.web.core.base.entity.QueryRequest;

public interface INewsDao extends IBaseDao<News> {

    public PageResponse<News> findNewsByPage(QueryRequest queryRequest, int pageIndex, int pageSize, boolean loadText, boolean enableTop);

}
