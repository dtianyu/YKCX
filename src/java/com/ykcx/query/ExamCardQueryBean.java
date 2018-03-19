/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ykcx.query;

import com.ykcx.ejb.ExamCardBean;
import com.ykcx.entity.ExamCard;
import com.ykcx.lazy.ExamCardModel;
import com.ykcx.web.SuperQueryBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "examCardQueryBean")
@SessionScoped
public class ExamCardQueryBean extends SuperQueryBean<ExamCard> {

    @EJB
    private ExamCardBean examCardBean;

    public ExamCardQueryBean() {
        super(ExamCard.class);
    }

    @Override
    public void init() {
        superEJB = examCardBean;
        model = new ExamCardModel(examCardBean);
        super.init();
    }

    public String query(String path) {
        if (queryName != null && !"".equals(queryName)) {
            entityList = examCardBean.findByName(queryName);
        }
        if (entityList == null || entityList.isEmpty()) {
            return "404";
        } else {
            return path;
        }
    }

    @Override
    public void setCurrentEntity(ExamCard currentEntity) {
        this.currentEntity = currentEntity;
    }

}
