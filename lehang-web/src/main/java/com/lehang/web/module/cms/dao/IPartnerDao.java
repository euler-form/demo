package com.lehang.web.module.cms.dao;

import com.lehang.web.module.cms.entity.Partner;

import net.eulerform.web.core.base.dao.IBaseDao;
import net.eulerform.web.core.base.entity.PageResponse;
import net.eulerform.web.core.base.entity.QueryRequest;

public interface IPartnerDao extends IBaseDao<Partner> {

    public int findMaxOrder();

    public PageResponse<Partner> findPartnerByPage(QueryRequest queryRequest, int pageIndex, int pageSize);

}
