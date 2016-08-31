package net.eulerform.web.module.lh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import net.eulerform.web.core.base.entity.UUIDEntity;

@Entity
@XmlRootElement
@Table(name="LH_HOT_PROJECT")
@SuppressWarnings("serial")
public class HotProject extends UUIDEntity<HotProject> {

    @Column(name="NAME", nullable=false)
    private String name;
    @Column(name="SUMMARY")
    private String summary;
    @Column(name="IMG_FILE_NAME")
    private String imgFileName;
    @Column(name="SHOW_ORDER",nullable=false, unique=true)
    private Integer order;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getImgFileName() {
        return imgFileName;
    }
    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }
    public Integer getOrder() {
        return order;
    }
    public void setOrder(Integer order) {
        this.order = order;
    }
}
