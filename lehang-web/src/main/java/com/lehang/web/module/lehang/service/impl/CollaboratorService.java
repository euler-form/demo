package com.lehang.web.module.lehang.service.impl;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lehang.web.module.lehang.dao.ICollaboratorDao;
import com.lehang.web.module.lehang.entity.Collaborator;
import com.lehang.web.module.lehang.service.ICollaboratorService;

import net.eulerform.common.FileReader;
import net.eulerform.common.StringTool;
import net.eulerform.web.core.base.entity.PageResponse;
import net.eulerform.web.core.base.entity.QueryRequest;
import net.eulerform.web.core.base.service.impl.BaseService;

@Service
public class CollaboratorService extends BaseService implements ICollaboratorService {

    @Resource ICollaboratorDao collaboratorDao;

    @Override
    public void saveCollaborator(Collaborator collaborator) {
        if(collaborator.getId() != null && StringTool.isNull(collaborator.getLogoFileName())) {
            Collaborator oldCollaborator = this.collaboratorDao.load(collaborator.getId());
            collaborator.setLogoFileName(oldCollaborator.getLogoFileName());
        }
        if(collaborator.getOrder() == null){
            int maxOrder = this.collaboratorDao.findMaxOrder();
            collaborator.setOrder(++maxOrder);
        }
        this.collaboratorDao.saveOrUpdate(collaborator);
    }

    @Override
    public PageResponse<Collaborator> findCollaboratorByPage(QueryRequest queryRequest, int pageIndex, int pageSize) {
        return this.collaboratorDao.findCollaboratorByPage(queryRequest, pageIndex, pageSize);
    }

    @Override
    public void deleteLogo(String collaboratorId) {
        Collaborator collaborator = this.collaboratorDao.load(collaboratorId);
        String filePath = this.getServletContext().getRealPath("/resources/upload")+"/"+collaborator.getLogoFileName();
        FileReader.deleteFile(new File(filePath));
        
    }

    @Override
    public void deleteCollaborators(String[] idArray) {
        if(idArray.length > 0) {
            for(String id : idArray) {
                this.deleteLogo(id);
            }
        }
        this.collaboratorDao.deleteByIds(idArray);
    }
}
