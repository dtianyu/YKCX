package com.ykcx.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-14T22:25:16")
@StaticMetamodel(SystemUser.class)
public class SystemUser_ { 

    public static volatile SingularAttribute<SystemUser, String> password;
    public static volatile SingularAttribute<SystemUser, String> phone;
    public static volatile SingularAttribute<SystemUser, Integer> failure;
    public static volatile SingularAttribute<SystemUser, Date> lastlogin;
    public static volatile SingularAttribute<SystemUser, String> tel;
    public static volatile SingularAttribute<SystemUser, Date> newestlogin;
    public static volatile SingularAttribute<SystemUser, Boolean> locked;
    public static volatile SingularAttribute<SystemUser, String> userid;
    public static volatile SingularAttribute<SystemUser, String> email;
    public static volatile SingularAttribute<SystemUser, Boolean> superuser;
    public static volatile SingularAttribute<SystemUser, String> username;

}