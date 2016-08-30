package net.eulerform.web.module.lh.dao;

import java.util.List;

import net.eulerform.web.core.base.dao.IBaseDao;
import net.eulerform.web.core.base.request.QueryRequest;
import net.eulerform.web.core.base.response.PageResponse;
import net.eulerform.web.module.lh.entity.Position;

public interface IPositionDao extends IBaseDao<Position> {

    PageResponse<Position> findPositionByPage(QueryRequest queryRequest, int pageIndex, int pageSize);

    List<Position> findHotPositon();

}
