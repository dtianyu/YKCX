/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ykcx.comm;

import com.lightshell.comm.SuperEJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kevindong
 * @param <T>
 */
public abstract class SuperEJBForYKCX<T> extends SuperEJB<T> {

    @PersistenceContext(unitName = "YKCXPU")
    private EntityManager em;

    public SuperEJBForYKCX(Class<T> clazz) {
        super(clazz);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

}
