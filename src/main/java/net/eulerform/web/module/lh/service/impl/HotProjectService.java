package net.eulerform.web.module.lh.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.eulerform.common.util.FileReader;
import net.eulerform.common.util.StringTool;
import net.eulerform.web.core.base.service.impl.BaseService;
import net.eulerform.web.core.exception.MultipartFileSaveException;
import net.eulerform.web.core.util.WebConfig;
import net.eulerform.web.core.util.WebFileTool;
import net.eulerform.web.module.lh.dao.IHotProjectDao;
import net.eulerform.web.module.lh.entity.HotProject;
import net.eulerform.web.module.lh.service.IHotProjectService;

@Service
public class HotProjectService extends BaseService implements IHotProjectService {
    
    @Resource private IHotProjectDao hotProjectDao;

    @Override
    public List<HotProject> loadHotProject() {
        List<HotProject> data = new ArrayList<>();
        HotProject hotProject0 = this.hotProjectDao.findHotProjectByOrder(0);
        data.add(hotProject0 == null ? new HotProject() : hotProject0);
        HotProject hotProject1 = this.hotProjectDao.findHotProjectByOrder(1);
        data.add(hotProject1 == null ? new HotProject() : hotProject1);
        HotProject hotProject2 = this.hotProjectDao.findHotProjectByOrder(2);
        data.add(hotProject2 == null ? new HotProject() : hotProject2);
        return data;
    }

    @Override
    public void saveHotProject(List<MultipartFile> img, List<HotProject> hotProjectes) {
        for(int i = 0; i < img.size(); i++) {
            HotProject hotProject = hotProjectes.get(i);
            String name = hotProject.getName();
            String summary = hotProject.getSummary();
            this.saveHotProjectByOrder(img.get(i), name, summary, i);
            
        }
    }
    
    private void saveHotProjectByOrder(MultipartFile img, String name, String summary, int order){
        HotProject hotProject = this.hotProjectDao.findHotProjectByOrder(order);
        
        if(hotProject == null) {
            hotProject = new HotProject();
            hotProject.setOrder(order);
        }
        hotProject.setName(name);
        hotProject.setSummary(summary);
        
        if(img != null && img.getSize() > 0){            
            try {
                
                File savedFile = WebFileTool.saveMultipartFile(img);

                //删除旧图片
                String uploadPath = this.getServletContext().getRealPath(WebConfig.getUploadPath());                
                if(!StringTool.isNull(hotProject.getId())) {
                    String filePath = uploadPath+"/"+hotProject.getImgFileName();
                    FileReader.deleteFile(new File(filePath));
                }
                
                hotProject.setImgFileName(savedFile.getName());
                
            } catch (MultipartFileSaveException e) {
                throw new RuntimeException(e);
            }
        }

        this.hotProjectDao.saveOrUpdate(hotProject);
    }

    @Override
    public List<HotProject> loadHotProjectByOrder() {
        return this.hotProjectDao.loadHotProject();
    }
}
