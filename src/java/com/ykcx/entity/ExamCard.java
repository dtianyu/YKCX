/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ykcx.entity;

import com.lightshell.comm.SuperEntity;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevindong
 */
@Entity
@Table(name = "examcard")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExamCard.findAll", query = "SELECT e FROM ExamCard e")
    , @NamedQuery(name = "ExamCard.findById", query = "SELECT e FROM ExamCard e WHERE e.id = :id")
    , @NamedQuery(name = "ExamCard.findByFormid", query = "SELECT e FROM ExamCard e WHERE e.formid = :formid")
    , @NamedQuery(name = "ExamCard.findByFormdate", query = "SELECT e FROM ExamCard e WHERE e.formdate = :formdate")
    , @NamedQuery(name = "ExamCard.findByName", query = "SELECT e FROM ExamCard e WHERE e.name = :name")
    , @NamedQuery(name = "ExamCard.findByBorn", query = "SELECT e FROM ExamCard e WHERE e.born = :born")
    , @NamedQuery(name = "ExamCard.findByGender", query = "SELECT e FROM ExamCard e WHERE e.gender = :gender")
    , @NamedQuery(name = "ExamCard.findByIdcard", query = "SELECT e FROM ExamCard e WHERE e.idcard = :idcard")
    , @NamedQuery(name = "ExamCard.findByPhone", query = "SELECT e FROM ExamCard e WHERE e.phone = :phone")
    , @NamedQuery(name = "ExamCard.findByPK", query = "SELECT e FROM ExamCard e WHERE e.formid = :formid AND e.name=:name AND e.born=:born AND e.organization=:organization AND e.project=:project")
    , @NamedQuery(name = "ExamCard.findByStatus", query = "SELECT e FROM ExamCard e WHERE e.status = :status")})
public class ExamCard extends SuperEntity {

    @Size(max = 20)
    @Column(name = "formid")
    private String formid;
    @Column(name = "formdate")
    @Temporal(TemporalType.DATE)
    private Date formdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "name")
    private String name;
    @Size(max = 20)
    @Column(name = "born")
    private String born;
    @Size(max = 10)
    @Column(name = "gender")
    private String gender;
    @Size(max = 45)
    @Column(name = "idcard")
    private String idcard;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="电话/传真格式无效, 应为 xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "phone")
    private String phone;
    @Size(max = 20)
    @Column(name = "nationality")
    private String nationality;
    @Size(max = 20)
    @Column(name = "minority")
    private String minority;
    @Size(max = 45)
    @Column(name = "speciality")
    private String speciality;
    @Size(max = 20)
    @Column(name = "lvl")
    private String lvl;
    @Size(max = 20)
    @Column(name = "awards")
    private String awards;
    @Size(max = 45)
    @Column(name = "certificate")
    private String certificate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "mark")
    private BigDecimal mark;
    @Size(max = 100)
    @Column(name = "project")
    private String project;
    @Size(max = 45)
    @Column(name = "organization")
    private String organization;
    @Size(max = 100)
    @Column(name = "remark")
    private String remark;
    @Size(max = 45)
    @Column(name = "photo")
    private String photo;

    public ExamCard() {
    }

    public String getFormid() {
        return formid;
    }

    public void setFormid(String formid) {
        this.formid = formid;
    }

    public Date getFormdate() {
        return formdate;
    }

    public void setFormdate(Date formdate) {
        this.formdate = formdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMinority() {
        return minority;
    }

    public void setMinority(String minority) {
        this.minority = minority;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getLvl() {
        return lvl;
    }

    public void setLvl(String lvl) {
        this.lvl = lvl;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public BigDecimal getMark() {
        return mark;
    }

    public void setMark(BigDecimal mark) {
        this.mark = mark;
    }

    /**
     * @return the project
     */
    public String getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(String project) {
        this.project = project;
    }

    /**
     * @return the organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * @param organization the organization to set
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * @param photo the photo to set
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamCard)) {
            return false;
        }
        ExamCard other = (ExamCard) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ykcx.entity.ExamCard[ id=" + id + " ]";
    }

}
