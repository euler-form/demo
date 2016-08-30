package net.eulerform.web.module.lh.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import net.eulerform.web.core.base.request.QueryRequest;
import net.eulerform.web.core.base.response.PageResponse;
import net.eulerform.web.core.base.service.IBaseService;
import net.eulerform.web.module.lh.entity.Position;

@PreAuthorize("isFullyAuthenticated() and hasAnyAuthority('CMS_ADMIN','ADMIN','SYSTEM')")
public interface IPositionService extends IBaseService {

    @PreAuthorize("permitAll")
    public PageResponse<Position> findPositionByPage(QueryRequest queryRequest, int pageIndex, int pageSize);

    public void savePosition(Position position);

    @PreAuthorize("permitAll")
    public Position findPosition(String positionId);

    @PreAuthorize("permitAll")
    public List<Position> findHotPositon();

}
