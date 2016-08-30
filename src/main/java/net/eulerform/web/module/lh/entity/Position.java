package net.eulerform.web.module.lh.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import net.eulerform.web.core.base.entity.UUIDEntity;

@Entity
@XmlRootElement
@Table(name="LH_POSITION")
@SuppressWarnings("serial")
public class Position extends UUIDEntity<Position> {
    
    public final static String ACCO_BOEING = "BOEING";
    public final static String ACCO_AIRBUS = "AIRBUS";
    public final static String ACCO_OTHER = "OTHER";

    @NotNull
    @Column(name="NAME", nullable=false)
    private String name;
    @NotNull
    @Column(name="SUMMARY", nullable=false)
    private String summary;
    @NotNull
    @Column(name="COMPANY_ID", nullable=false)
    private String companyId;
    @Column(name="HOT", nullable=true)
    private Boolean hot;
    @NotNull
    @Column(name="ACCO", nullable=false)
    private String acco;
    @NotNull
    @Column(name="AC_TYPE", nullable=false)
    private String acType;
    @NotNull
    @Column(name="SALARY", nullable=false)
    private String salary;
    @Column(name="PUB_DATE", nullable=false)
    private Date pubDate;
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
    public String getCompanyId() {
        return companyId;
    }
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    public Boolean getHot() {
        return hot;
    }
    public void setHot(Boolean hot) {
        this.hot = hot;
    }
    public String getAcco() {
        return acco;
    }
    public void setAcco(String acco) {
        this.acco = acco;
    }
    public String getAcType() {
        return acType;
    }
    public void setAcType(String acType) {
        this.acType = acType;
    }
    public String getSalary() {
        return salary;
    }
    public void setSalary(String salary) {
        this.salary = salary;
    }
    public Date getPubDate() {
        return pubDate;
    }
    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }
    
    @Transient
    private String companyName;
    @Transient
    private String companyLogoFileName;
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getCompanyLogoFileName() {
        return companyLogoFileName;
    }
    public void setCompanyLogoFileName(String companyLogoFileName) {
        this.companyLogoFileName = companyLogoFileName;
    }
    
    
}
