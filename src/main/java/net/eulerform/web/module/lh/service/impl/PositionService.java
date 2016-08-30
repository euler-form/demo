package net.eulerform.web.module.lh.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.eulerform.common.BeanTool;
import net.eulerform.common.StringTool;
import net.eulerform.web.core.base.request.QueryRequest;
import net.eulerform.web.core.base.response.PageResponse;
import net.eulerform.web.core.base.service.impl.BaseService;
import net.eulerform.web.module.cms.basic.entity.Partner;
import net.eulerform.web.module.cms.basic.service.IPartnerService;
import net.eulerform.web.module.lh.dao.IPositionDao;
import net.eulerform.web.module.lh.entity.Position;
import net.eulerform.web.module.lh.service.IPositionService;

@Service
public class PositionService extends BaseService implements IPositionService {
    
    @Resource private IPositionDao positionDao;
    
    @Resource private IPartnerService partnerService;

    @Override
    public PageResponse<Position> findPositionByPage(QueryRequest queryRequest, int pageIndex, int pageSize) {
        PageResponse<Position> ret = this.positionDao.findPositionByPage(queryRequest, pageIndex, pageSize);
        
        this.addCompanyInfo(ret.getRows());
        return ret;
    }

    @Override
    public void savePosition(Position position) {
        BeanTool.clearEmptyProperty(position);
        
        if(position.getId() != null && position.getCompanyId() == null) {
            Position tmp = this.positionDao.load(position.getId());
            
            if(tmp != null) {
                position.setCompanyId(tmp.getCompanyId());
            }
        }
        
        if(position.getPubDate() == null)
            position.setPubDate(new Date());
        
        this.positionDao.saveOrUpdate(position);
    }

    @Override
    public Position findPosition(String positionId) {
        Position position = this.positionDao.load(positionId);
        
        if(position == null)
            return null;

        if(!StringTool.isNull(position.getCompanyId())) {
            Partner partner = this.partnerService.findPartner(position.getCompanyId());
            if(partner != null) {
                position.setCompanyName(partner.getName());
                position.setCompanyLogoFileName(partner.getLogoFileName());
            }
        }
        
        return position;
    }

    @Override
    public List<Position> findHotPositon() {
        List<Position> ret = this.positionDao.findHotPositon();
        this.addCompanyInfo(ret);
        return ret;
    }
    
    private void addCompanyInfo(List<Position> positionList){
        Set<String> companyIdSet = new HashSet<>();
        for(Position each : positionList) {
            if(!StringTool.isNull(each.getCompanyId()))
                companyIdSet.add(each.getCompanyId());
        }
        
        List<Partner> partnerList = this.partnerService.findPartners(companyIdSet);
        
        if(partnerList != null && !partnerList.isEmpty()) {
            for(Position each : positionList) {
                if(StringTool.isNull(each.getCompanyId()))
                    continue;
                
                String id = each.getCompanyId();
                
                for(Partner partner : partnerList) {
                    if(id.equals(partner.getId())) {
                        each.setCompanyName(partner.getName());
                        each.setCompanyLogoFileName(partner.getLogoFileName());
                    }
                }
            }
        }
    }
}
