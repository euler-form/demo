package com.lehang.web.module.cms.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lehang.web.module.cms.dao.IPartnerDao;
import com.lehang.web.module.cms.entity.Partner;
import com.lehang.web.module.cms.service.IPartnerService;

import net.eulerform.common.FileReader;
import net.eulerform.common.GlobalProperties;
import net.eulerform.common.GlobalPropertyReadException;
import net.eulerform.common.StringTool;
import net.eulerform.web.core.base.entity.PageResponse;
import net.eulerform.web.core.base.entity.QueryRequest;
import net.eulerform.web.core.base.service.impl.BaseService;

@Service
public class PartnerService extends BaseService implements IPartnerService {

    @Resource IPartnerDao partnerDao;

    @Override
    public void savePartner(Partner partner) {
        if(partner.getId() != null && StringTool.isNull(partner.getLogoFileName())) {
            Partner oldPartner = this.partnerDao.load(partner.getId());
            partner.setLogoFileName(oldPartner.getLogoFileName());
        }
        if(partner.getOrder() == null){
            int maxOrder = this.partnerDao.findMaxOrder();
            partner.setOrder(++maxOrder);
        }
        this.partnerDao.saveOrUpdate(partner);
    }

    @Override
    public PageResponse<Partner> findPartnerByPage(QueryRequest queryRequest, int pageIndex, int pageSize) {
        return this.partnerDao.findPartnerByPage(queryRequest, pageIndex, pageSize);
    }

    @Override
    public void deleteLogo(String partnerId) {
        Partner partner = this.partnerDao.load(partnerId);
        String filePath;
        try {
            filePath = this.getServletContext().getRealPath(GlobalProperties.get(GlobalProperties.UPLOAD_PATH))+"/"+partner.getLogoFileName();
        } catch (GlobalPropertyReadException e) {
            throw new RuntimeException(e);
        }
        FileReader.deleteFile(new File(filePath));
        
    }

    @Override
    public void deletePartners(String[] idArray) {
        if(idArray.length > 0) {
            for(String id : idArray) {
                this.deleteLogo(id);
            }
        }
        this.partnerDao.deleteByIds(idArray);
    }

    @Override
    public List<Partner> loadPartners() {
        // TODO Auto-generated method stub
        return null;
    }
}
