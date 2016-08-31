package net.eulerform.web.module.lh.dao;

import java.util.List;

import net.eulerform.web.core.base.dao.IBaseDao;
import net.eulerform.web.module.lh.entity.HotProject;

public interface IHotProjectDao extends IBaseDao<HotProject> {

    public HotProject findHotProjectByOrder(int order);

    public List<HotProject> loadHotProject();

}
