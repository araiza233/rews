package com.publicidad.dao;
import com.publicidad.entities.Users;
import java.util.List;
public interface UsuarioDao {
    public String saveUser(Users user);
    public List getUsers();
    public boolean editar(Users usu);
    public Users getUsuarioById(String idUsuario);
    public List getAllUsers();
}
