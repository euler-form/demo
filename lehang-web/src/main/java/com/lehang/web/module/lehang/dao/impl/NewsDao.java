package com.lehang.web.module.lehang.dao.impl;

import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lehang.web.module.lehang.dao.INewsDao;
import com.lehang.web.module.lehang.entity.News;

import net.eulerform.common.CalendarTool;
import net.eulerform.common.StringTool;
import net.eulerform.web.core.base.dao.impl.hibernate5.BaseDao;
import net.eulerform.web.core.base.entity.PageResponse;
import net.eulerform.web.core.base.entity.QueryRequest;
import net.eulerform.web.core.extend.hibernate5.RestrictionsX;

public class NewsDao extends BaseDao<News> implements INewsDao {

    @Override
    public PageResponse<News> findNewsByPage(QueryRequest queryRequest, int pageIndex, int pageSize) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(this.entityClass);
        try {
            String queryValue = null;
            queryValue = queryRequest.getQueryValue("title");
            if (!StringTool.isNull(queryValue)) {
                detachedCriteria.add(RestrictionsX.like("title", queryValue, MatchMode.ANYWHERE).ignoreCase());
            }
            queryValue = queryRequest.getQueryValue("author");
            if (!StringTool.isNull(queryValue)) {
                detachedCriteria.add(RestrictionsX.like("author", queryValue, MatchMode.ANYWHERE).ignoreCase());
            }
            queryValue = queryRequest.getQueryValue("summary");
            if (!StringTool.isNull(queryValue)) {
                detachedCriteria.add(RestrictionsX.like("summary", queryValue, MatchMode.ANYWHERE).ignoreCase());
            }
            queryValue = queryRequest.getQueryValue("text");
            if (!StringTool.isNull(queryValue)) {
                detachedCriteria.add(RestrictionsX.like("text", queryValue, MatchMode.ANYWHERE).ignoreCase());
            }
            queryValue = queryRequest.getQueryValue("pubDate");
            if (!StringTool.isNull(queryValue)) {
                Date pubDate = CalendarTool.parseDate(queryValue, "yyyy-MM-dd");
                Date begin = CalendarTool.beginningOfTheDay(pubDate).getTime();
                Date end = CalendarTool.endingOfTheDay(pubDate).getTime();
                detachedCriteria.add(Restrictions.between("pubDate", begin, end));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        detachedCriteria.addOrder(Order.desc("top"));
        detachedCriteria.addOrder(Order.desc("pubDate"));
        
        PageResponse<News> result = this.findPageBy(detachedCriteria, pageIndex, pageSize);
        
        return result;
    }

}
