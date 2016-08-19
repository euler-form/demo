package com.lehang.web.module.lehang.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import net.eulerform.web.core.base.entity.UUIDEntity;

@Entity
@XmlRootElement
@Table(name="LH_NEWS")
@SuppressWarnings("serial")
public class News extends UUIDEntity<News> {

    @NotNull
    @Column(name="TITLE",nullable=false)
    private String title;
    @Column(name="TAGS")
    private String tags;
    @NotNull
    @Column(name="SUMMARY",nullable=false)
    private String summary;
    @Column(name="PUB_DATE",nullable=false)
    private Date pubDate;
    @Column(name="IMG_FILE_NAME")
    private String imageFileName;
    @Column(name="TEXT", columnDefinition="TEXT", nullable=false)
    private String text;
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Date getPubDate() {
        return pubDate;
    }
    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }
    public String getImageFileName() {
        return imageFileName;
    }
    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    
}
