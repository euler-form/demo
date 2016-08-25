package com.lehang.web.module.cms.service;

import java.util.List;

import com.lehang.web.module.cms.entity.Partner;

import net.eulerform.web.core.base.entity.PageResponse;
import net.eulerform.web.core.base.entity.QueryRequest;
import net.eulerform.web.core.base.service.IBaseService;

public interface IPartnerService extends IBaseService {

    public void savePartner(Partner partner);

    public PageResponse<Partner> findPartnerByPage(QueryRequest queryRequest, int pageIndex, int pageSize);

    public List<Partner> loadPartners();

    public void deleteLogo(String partnerId);

    public void deletePartners(String[] idArray);

}
