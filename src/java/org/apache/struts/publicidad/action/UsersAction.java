package org.apache.struts.publicidad.action;
import com.opensymphony.xwork2.ActionSupport;
import com.publicidad.entities.Users;
import com.publicidad.logic.Accesos;
import com.publicidad.logic.UsuarioLogic;
import com.publicidad.utilities.Utilities;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
public class UsersAction extends ActionSupport implements ServletRequestAware, ServletResponseAware  {
    private String mostrar;
    final private String CREAR_NUEVO_USUARIO = "1";
    final private String GUARDAR_NUEVO_USUARIO = "2";
    final private String MODIFICAR_USUARIO_PASSWORD = "3";
    final private String MODIFICAR_USUARIO_PASSWORD2 = "4";
    private HttpServletRequest request;
    private HttpServletResponse response;
    /*Variables de pagina que actualiza*/
    private String id_usuario;
    private String nombre;
    private String direccion;
    private String password;
    private String password2;
    private String telefono;
    private String id_rol;
    /*variables que hacen que el usuario pueda guardar el nuevo usuario*/
    private UsuarioLogic usuarioLogic;
    public String execute(){
        String forward = "error";
        try{
            String opcion = getMostrar();
            if(CREAR_NUEVO_USUARIO.equalsIgnoreCase(opcion)){
                forward = ActionSupport.SUCCESS;
            }else if (GUARDAR_NUEVO_USUARIO.equalsIgnoreCase(opcion)){
                Users user =  new Users();
                user.setDireccion(direccion);
                user.setId_rol(Integer.parseInt(id_rol));
                user.setId_usuario(id_usuario);
                user.setNombre(nombre);
                user.setPassword(password);
                user.setTelefono(telefono);
                forward= usuarioLogic.saveUser(user);
                if(!forward.equalsIgnoreCase("resultado")){
                    request.getSession().setAttribute("mensajeError", forward);
                    forward = "error";
                }
            }else if(MODIFICAR_USUARIO_PASSWORD.equalsIgnoreCase(opcion)){
                List listaUsuarios = usuarioLogic.getUsers();
                request.getSession().setAttribute("listaUsuarios", listaUsuarios);
                forward = "modificarUsers";
            }else if(MODIFICAR_USUARIO_PASSWORD2.equalsIgnoreCase(opcion)){
                Users user = usuarioLogic.getUsuarioById(id_usuario);
                if(!password.equalsIgnoreCase(""))
                    user.setPassword(getPassword());
                user.setDireccion(direccion);
                user.setNombre(nombre);
                user.setTelefono(telefono);
                usuarioLogic.editar(user);
                List listaUsuarios = usuarioLogic.getUsers();
                request.getSession().setAttribute("listaUsuarios", listaUsuarios);
                forward = "modificarUsers";
            }
        }catch(Exception e){
            request.getSession().setAttribute("mensajeError", ""+e.getMessage());
            return "error";
        }
        return forward;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    @Override
    public void setServletResponse(HttpServletResponse hsr) {
        this.response = hsr;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getId_rol() {
        return id_rol;
    }

    public void setId_rol(String id_rol) {
        this.id_rol = id_rol;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getMostrar() {
        return mostrar;
    }

    public void setMostrar(String mostrar) {
        this.mostrar = mostrar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getPassword2() {
        return password2;
    }
    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public UsuarioLogic getUsuarioLogic() {
        return usuarioLogic;
    }

    public void setUsuarioLogic(UsuarioLogic usuarioLogic) {
        this.usuarioLogic = usuarioLogic;
    }
}