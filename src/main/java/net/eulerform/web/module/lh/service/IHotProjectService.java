package net.eulerform.web.module.lh.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;

import net.eulerform.web.core.base.service.IBaseService;
import net.eulerform.web.module.lh.entity.HotProject;

@PreAuthorize("isFullyAuthenticated() and hasAnyAuthority('CMS_ADMIN','ADMIN','SYSTEM')")
public interface IHotProjectService extends IBaseService {

    public List<HotProject> loadHotProject();

    public void saveHotProject(List<MultipartFile> img, List<HotProject> hotProjectes);

    @PreAuthorize("permitAll")
    public List<HotProject> loadHotProjectByOrder();

}
