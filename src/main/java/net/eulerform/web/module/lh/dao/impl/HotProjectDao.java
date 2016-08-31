package net.eulerform.web.module.lh.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import net.eulerform.web.core.base.dao.impl.hibernate5.BaseDao;
import net.eulerform.web.module.lh.dao.IHotProjectDao;
import net.eulerform.web.module.lh.entity.HotProject;

public class HotProjectDao extends BaseDao<HotProject> implements IHotProjectDao {

    @Override
    public HotProject findHotProjectByOrder(int order) {
        HotProject tmp = new HotProject();
        tmp.setOrder(order);
        List<HotProject> result = this.findBy(tmp);
        
        if(result == null || result.isEmpty()){
            return null;
        }
        
        return result.get(0);
    }

    @Override
    public List<HotProject> loadHotProject() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(this.entityClass);        
        detachedCriteria.addOrder(Order.asc("order"));
        return this.findBy(detachedCriteria);
    }

}
