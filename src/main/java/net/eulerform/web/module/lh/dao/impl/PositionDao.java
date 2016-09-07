package net.eulerform.web.module.lh.dao.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import net.eulerform.common.util.CalendarTool;
import net.eulerform.common.util.StringTool;
import net.eulerform.web.core.base.dao.impl.hibernate5.BaseDao;
import net.eulerform.web.core.base.request.QueryRequest;
import net.eulerform.web.core.base.response.PageResponse;
import net.eulerform.web.core.extend.hibernate5.RestrictionsX;
import net.eulerform.web.module.cms.basic.entity.Partner;
import net.eulerform.web.module.cms.basic.service.IPartnerService;
import net.eulerform.web.module.lh.dao.IPositionDao;
import net.eulerform.web.module.lh.entity.Position;

public class PositionDao extends BaseDao<Position> implements IPositionDao {
    
    private IPartnerService partnerService;

    public void setPartnerService(IPartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @Override
    public PageResponse<Position> findPositionByPage(QueryRequest queryRequest, int pageIndex, int pageSize) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(this.entityClass);
        
        try {
            String queryValue = null;
            queryValue = queryRequest.getQueryValue("name");
            if (!StringTool.isNull(queryValue)) {
                detachedCriteria.add(RestrictionsX.like("name", queryValue, MatchMode.ANYWHERE).ignoreCase());
            }
            queryValue = queryRequest.getQueryValue("summary");
            if (!StringTool.isNull(queryValue)) {
                detachedCriteria.add(RestrictionsX.like("summary", queryValue, MatchMode.ANYWHERE).ignoreCase());
            }
            queryValue = queryRequest.getQueryValue("companyId");
            if (!StringTool.isNull(queryValue)) {
                detachedCriteria.add(Restrictions.eq("companyId", queryValue));
            }
            queryValue = queryRequest.getQueryValue("companyName");
            if (!StringTool.isNull(queryValue)) {
                List<Partner> partnerList = this.partnerService.findPartnerByNameFuzzy(queryValue);
                if(partnerList != null && !partnerList.isEmpty()) {
                    Set<String> partnerIdSet = new HashSet<>();
                    for(Partner each : partnerList) {
                        partnerIdSet.add(each.getId());
                    }
                    detachedCriteria.add(RestrictionsX.in("companyId", partnerIdSet));
                }
            }
            queryValue = queryRequest.getQueryValue("hot");
            if (!StringTool.isNull(queryValue)) {
                boolean hot = Boolean.parseBoolean(queryValue);
                if(hot)
                    detachedCriteria.add(Restrictions.eq("hot", true));
                else
                    detachedCriteria.add(Restrictions.or(
                                Restrictions.eq("hot", false),
                                Restrictions.isNull("hot")
                                ));
            }
            queryValue = queryRequest.getQueryValue("acco");
            if (!StringTool.isNull(queryValue)) {
                detachedCriteria.add(Restrictions.eq("acco", queryValue));
            }
            queryValue = queryRequest.getQueryValue("acType");
            if (!StringTool.isNull(queryValue)) {
                detachedCriteria.add(RestrictionsX.like("acType", queryValue, MatchMode.ANYWHERE).ignoreCase());
            }
            queryValue = queryRequest.getQueryValue("salary");
            if (!StringTool.isNull(queryValue)) {
                detachedCriteria.add(RestrictionsX.like("salary", queryValue, MatchMode.ANYWHERE).ignoreCase());
            }
            queryValue = queryRequest.getQueryValue("pubDate");
            if (!StringTool.isNull(queryValue)) {
                Date pubDate = CalendarTool.parseDate(queryValue, "yyyy-MM-dd");
                Date begin = CalendarTool.beginningOfTheDay(pubDate).getTime();
                Date end = CalendarTool.endingOfTheDay(pubDate).getTime();
                detachedCriteria.add(Restrictions.between("pubDate", begin, end));
            }
            detachedCriteria.addOrder(Order.desc("pubDate"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return this.findPageBy(detachedCriteria, pageIndex, pageSize);
    }

    @Override
    public List<Position> findHotPositon() {

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(this.entityClass);
        detachedCriteria.add(Restrictions.eq("hot", true));

        detachedCriteria.addOrder(Order.desc("pubDate"));
        return this.findBy(detachedCriteria);
    }

}
