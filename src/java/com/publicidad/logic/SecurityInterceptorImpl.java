package com.publicidad.logic;
import com.publicidad.dao.SecurityDao;
import java.util.List;
import java.util.Map;
public class SecurityInterceptorImpl implements SecurityInterceptorLogic{
    private SecurityDao securityDao;
    @Override
    public boolean getUrlAccess(String user, String urlToBeAccess) {
        List accessos = securityDao.getUrlAccess(user, urlToBeAccess);
        Map<String, Object> rep = null;
        String accesoPermitido = null;
        for(Object object:accessos){
            rep = (Map<String, Object>)object;
            accesoPermitido = rep.get("url").toString();
        }
        if(accesoPermitido==null)
            return false;
        if(accesoPermitido.equalsIgnoreCase(urlToBeAccess))
            return true;
        else
            return false;
    }
    public SecurityDao getSecurityDao() {
        return securityDao;
    }
    public void setSecurityDao(SecurityDao securityDao) {
        this.securityDao = securityDao;
    }
}