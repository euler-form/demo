package com.lehang.web.module.lehang.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import com.lehang.web.module.lehang.dao.ICollaboratorDao;
import com.lehang.web.module.lehang.entity.Collaborator;

import net.eulerform.common.StringTool;
import net.eulerform.web.core.base.dao.impl.hibernate5.BaseDao;
import net.eulerform.web.core.base.entity.PageResponse;
import net.eulerform.web.core.base.entity.QueryRequest;
import net.eulerform.web.core.extend.hibernate5.RestrictionsX;

public class CollaboratorDao extends BaseDao<Collaborator> implements ICollaboratorDao {

    @Override
    public int findMaxOrder() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(this.entityClass);        
        detachedCriteria.addOrder(Order.desc("order"));
        List<Collaborator> result = this.findByWithMaxResults(detachedCriteria, 1);
        if(result == null || result.isEmpty())
            return 0;
        
        return result.get(0).getOrder();
    }

    @Override
    public PageResponse<Collaborator> findCollaboratorByPage(QueryRequest queryRequest, int pageIndex, int pageSize) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(this.entityClass);
        try {
            String queryValue = null;
            queryValue = queryRequest.getQueryValue("name");
            if (!StringTool.isNull(queryValue)) {
                detachedCriteria.add(RestrictionsX.like("name", queryValue, MatchMode.ANYWHERE).ignoreCase());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        detachedCriteria.addOrder(Order.asc("order"));
        
        PageResponse<Collaborator> result = this.findPageBy(detachedCriteria, pageIndex, pageSize);
        
        return result;
    }

}
