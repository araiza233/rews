package com.publicidad.logic;
import com.publicidad.dao.UsuarioDao;
import com.publicidad.entities.Users;
import java.util.List;
public class UsuarioLogicImpl implements UsuarioLogic{
    private UsuarioDao usuarioDao;
    @Override
    public String saveUser(Users user) {
        return usuarioDao.saveUser(user);
    }
    public List getUsers(){
        return usuarioDao.getUsers();
    }
    public List getAllUsers(){
        return usuarioDao.getAllUsers();
    }
    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }
    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }
    public boolean editar(Users usu){
        return this.usuarioDao.editar(usu);
    }
    public Users getUsuarioById(String idUsuario){
        return this.usuarioDao.getUsuarioById(idUsuario);
    }
}