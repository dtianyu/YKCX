/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ykcx.ejb;

import com.ykcx.comm.SuperEJBForYKCX;
import com.ykcx.entity.ExamCard;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
public class ExamCardBean extends SuperEJBForYKCX<ExamCard> {

    public ExamCardBean() {
        super(ExamCard.class);
    }

    public List<ExamCard> findByName(String name) {
        Query query = getEntityManager().createNamedQuery("ExamCard.findByName");
        query.setParameter("name", name);
        try {
            return query.getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public ExamCard findByPK(String formid, String name, String born, String organization, String project) {
        Query query = getEntityManager().createNamedQuery("ExamCard.findByPK");
        query.setParameter("formid", formid);
        query.setParameter("name", name);
        query.setParameter("born", born);
        query.setParameter("organization", organization);
        query.setParameter("project", project);
        try {
            Object o = query.getSingleResult();
            return (ExamCard) o;
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean isExist(String formid, String name, String born, String organization, String project) {
        ExamCard e = findByPK(formid, name, born, organization, project);
        if (e != null) {
            return true;
        } else {
            return false;
        }
    }

}
