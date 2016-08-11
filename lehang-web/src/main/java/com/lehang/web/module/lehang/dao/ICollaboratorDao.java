package com.lehang.web.module.lehang.dao;

import com.lehang.web.module.lehang.entity.Collaborator;

import net.eulerform.web.core.base.dao.IBaseDao;
import net.eulerform.web.core.base.entity.PageResponse;
import net.eulerform.web.core.base.entity.QueryRequest;

public interface ICollaboratorDao extends IBaseDao<Collaborator> {

    public int findMaxOrder();

    public PageResponse<Collaborator> findCollaboratorByPage(QueryRequest queryRequest, int pageIndex, int pageSize);

}
