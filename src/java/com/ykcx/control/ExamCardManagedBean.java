/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ykcx.control;

import com.lightshell.comm.BaseLib;
import com.ykcx.ejb.ExamCardBean;
import com.ykcx.entity.ExamCard;
import com.ykcx.lazy.ExamCardModel;
import com.ykcx.web.SuperSingleBean;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "examCardManagedBean")
@SessionScoped
public class ExamCardManagedBean extends SuperSingleBean<ExamCard> {

    @EJB
    private ExamCardBean examCardBean;

    private List<ExamCard> addedList;
    private ExamCard importEntity;
    private String querySpeciality;

    public ExamCardManagedBean() {
        super(ExamCard.class);
    }

    public void batchImport() {
        try {
            getAddedList().stream().forEach((e) -> {
                superEJB.persist(e);
            });
            doAfterPersist();
            showInfoMsg("Info", "导入保存成功");
        } catch (Exception ex) {
            showErrorMsg("Error", ex.toString());
        }
    }

    @Override
    public void delete() {
        if (entityList != null && !entityList.isEmpty()) {
            superEJB.delete(entityList);
            entityList.clear();
            showInfoMsg("Info", "删除成功");
        }
    }

    @Override
    protected boolean doAfterPersist() throws Exception {
        if (addedList != null) {
            addedList.clear();
        }
        fileName = null;
        return super.doAfterPersist();
    }

    @Override
    public void handleFileUploadWhenNew(FileUploadEvent event) {
        super.handleFileUploadWhenNew(event);
        if (this.fileName != null) {
            ExamCard e;
            if (addedList != null) {
                addedList.clear();
            }
            try {
                InputStream is = new FileInputStream(getAppResPath() + "/" + fileName);
                //is = FileMagic.prepareToCheckMagic(is);
                Workbook wb = WorkbookFactory.create(is);
                Sheet sheet = wb.getSheetAt(wb.getActiveSheetIndex());
                Cell c;
                for (Row r : sheet) {
                    if (r.getRowNum() == 0) {
                        continue;
                    }
                    e = new ExamCard();
                    c = r.getCell(0);
                    e.setFormid(BaseLib.convertExcelCell(String.class, c));
                    if (e.getFormid() == null || "".equals(e.getFormid())) {
                        //判断是否为空，最后一行就跳出循环
                        break;
                    }
                    c = r.getCell(1);
                    e.setOrganization(BaseLib.convertExcelCell(String.class, c));
                    c = r.getCell(2);
                    e.setProject(BaseLib.convertExcelCell(String.class, c));
                    c = r.getCell(3);
                    e.setName(BaseLib.convertExcelCell(String.class, c));
                    if (e.getName() == null) {
                        showErrorMsg("Error", "导入失败,姓名不能空白");
                        return;
                    }
                    c = r.getCell(4);
                    c.setCellType(1);
                    e.setBorn(BaseLib.convertExcelCell(String.class, c));
                    if (e.getBorn() == null) {
                        showErrorMsg("Error", "导入失败,出生年月空白");
                        return;
                    }
                    c = r.getCell(5);
                    e.setGender(BaseLib.convertExcelCell(String.class, c));
                    c = r.getCell(6);
                    e.setSpeciality(BaseLib.convertExcelCell(String.class, c));
                    c = r.getCell(7);
                    e.setLvl(BaseLib.convertExcelCell(String.class, c));
                    c = r.getCell(8);
                    e.setAwards(BaseLib.convertExcelCell(String.class, c));
                    c = r.getCell(9);
                    e.setNationality(BaseLib.convertExcelCell(String.class, c));
                    c = r.getCell(10);
                    e.setMinority(BaseLib.convertExcelCell(String.class, c));
                    c = r.getCell(11);
                    e.setPhoto(BaseLib.convertExcelCell(String.class, c));
                    e.setStatusToNew();
                    e.setCreator(this.userManagedBean.getCurrentUser().getUsername());
                    e.setCredateToNow();
                    if (!examCardBean.isExist(e.getFormid(), e.getName(), e.getBorn(), e.getOrganization(), e.getProject())) {
                        addedList.add(e);
                        System.out.println(addedList.size());
                    }
                }
                showInfoMsg("Info", "上传成功");
            } catch (FileNotFoundException ex) {
                showErrorMsg("Error", "上传失败,找不到导入文件");
            } catch (IOException | InvalidFormatException ex) {
                showErrorMsg("Error", "上传失败,文件格式错误");
                Logger.getLogger(this.getClass().toString()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void handlePhotoUpload(FileUploadEvent event) {
        super.handleFileUpload(event);
        this.fileName = "";
    }

    @Override
    public void init() {
        setAddedList(new ArrayList<>());
        superEJB = examCardBean;
        model = new ExamCardModel(examCardBean);
        model.getSortFields().put("id", "DESC");
        super.init();
    }

    @Override
    public void query() {
        if (model != null) {
            if (queryName != null && !"".equals(queryName)) {
                model.getFilterFields().put("name", queryName);
            }
            if (querySpeciality != null && !"".equals(querySpeciality)) {
                model.getFilterFields().put("speciality", querySpeciality);
            }
        }
    }

    @Override
    public void reset() {
        if (addedList != null) {
            addedList.clear();
        }
        fileName = null;
        super.reset();
    }

    @Override
    public void setEntityList(List<ExamCard> entityList) {
        super.setEntityList(entityList);
        if (entityList != null && !entityList.isEmpty()) {
            this.setCurrentEntity(entityList.get(0));
        }
    }

    /**
     * @return the addedList
     */
    public List<ExamCard> getAddedList() {
        return addedList;
    }

    /**
     * @param addedList the addedList to set
     */
    public void setAddedList(List<ExamCard> addedList) {
        this.addedList = addedList;
    }

    /**
     * @return the importEntity
     */
    public ExamCard getImportEntity() {
        return importEntity;
    }

    /**
     * @param importEntity the importEntity to set
     */
    public void setImportEntity(ExamCard importEntity) {
        this.importEntity = importEntity;
    }

    /**
     * @return the querySpeciality
     */
    public String getQuerySpeciality() {
        return querySpeciality;
    }

    /**
     * @param querySpeciality the querySpeciality to set
     */
    public void setQuerySpeciality(String querySpeciality) {
        this.querySpeciality = querySpeciality;
    }

}
