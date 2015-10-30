package com.publicidad.logic;
import com.publicidad.dao.SecurityDao;
import com.publicidad.entities.Users;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
public class AccesosImpl implements Accesos{
    SecurityDao securityDao;
    @Override
    public boolean getAccesos(String user, String passwd) {
        return securityDao.getAccesos(user, passwd);
    }
    public List getMenuOptions(String user){
        return securityDao.getMenuOptions(user);
    }
    public SecurityDao getSecurityDao() {
        return securityDao;
    }
    public void setSecurityDao(SecurityDao securityDao) {
        this.securityDao = securityDao;
    }

    @Override
    public boolean modifyPassword(String user, String newPasswd) {
        Users usuario = securityDao.getUserById(user);
        return securityDao.modifyPassword(usuario, newPasswd);
    }
}