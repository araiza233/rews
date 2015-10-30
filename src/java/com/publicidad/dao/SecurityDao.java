package com.publicidad.dao;
import com.publicidad.entities.Producto;
import com.publicidad.entities.Users;
import java.util.List;
public interface SecurityDao {
    public List getUrlAccess(String user, String urlToBeAccess);
    public boolean getAccesos(String user, String pwd);
    public List getMenuOptions(String user);
    public boolean modifyPassword(Users user, String newPasswd);
    public Users getUserById(String idUser);
}