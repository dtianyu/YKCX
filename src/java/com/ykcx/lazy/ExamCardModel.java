/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ykcx.lazy;

import com.ykcx.entity.ExamCard;
import com.lightshell.comm.BaseLazyModel;
import com.lightshell.comm.SuperEJB;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 *
 * @author kevindong
 */
public class ExamCardModel extends BaseLazyModel<ExamCard> {

    public ExamCardModel(SuperEJB superEJB) {
        this.superEJB = superEJB;
        this.sortFields.put("status", "ASC");
        this.sortFields.put("id", "ASC");
    }

    

}
