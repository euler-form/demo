package com.lehang.web.module.lehang.service;

import com.lehang.web.module.lehang.entity.Collaborator;

import net.eulerform.web.core.base.entity.PageResponse;
import net.eulerform.web.core.base.entity.QueryRequest;
import net.eulerform.web.core.base.service.IBaseService;

public interface ICollaboratorService extends IBaseService {

    public void saveCollaborator(Collaborator collaborator);

    public PageResponse<Collaborator> findCollaboratorByPage(QueryRequest queryRequest, int pageIndex, int pageSize);

    public void deleteLogo(String collaboratorId);

    public void deleteCollaborators(String[] idArray);

}
