package com.lehang.web.module.lehang.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import com.lehang.web.module.lehang.dao.ISlideshowDao;
import com.lehang.web.module.lehang.entity.Slideshow;

import net.eulerform.web.core.base.dao.impl.hibernate5.BaseDao;

public class SlideshowDao extends BaseDao<Slideshow> implements ISlideshowDao {

    @Override
    public Slideshow findSlideshowByOrder(int order) {
        Slideshow tmp = new Slideshow();
        tmp.setOrder(order);
        List<Slideshow> result = this.findBy(tmp);
        
        if(result == null || result.isEmpty()){
            return null;
        }
        
        return result.get(0);
    }

    @Override
    public List<Slideshow> loadSlideshow() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(this.entityClass);        
        detachedCriteria.addOrder(Order.asc("order"));
        return this.findBy(detachedCriteria);
    }

}
