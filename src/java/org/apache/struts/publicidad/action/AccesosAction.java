package org.apache.struts.publicidad.action;
import com.opensymphony.xwork2.ActionSupport;
import com.publicidad.logic.Accesos;
import com.publicidad.utilities.Utilities;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
public class AccesosAction extends ActionSupport implements ServletRequestAware, ServletResponseAware  {
    private boolean result;
    private Accesos acceso;
    private String user;
    private String password;
    private HttpServletRequest request;
    private HttpServletResponse response;
    /*Variables de pagina que actualiza*/
    private String actual;
    private String nueva1;
    private String nueva2;
    public String getAccesos(){
        String forward = ActionSupport.SUCCESS;
        try{
            setResult(acceso.getAccesos(getUser(), getPassword()));
            if(isResult()){
                request.getSession().setAttribute("usuario", getUser());
                List  accesosUserList = acceso.getMenuOptions(getUser());
                request.getSession().setAttribute("accesosList", accesosUserList);
                request.getSession().setAttribute("precios", "publico");//variable global que va a indicar el precio que mostrara en los productos
            }else{
                request.getSession().setAttribute("usuario", null);
                forward="loguearse";
            }
        }catch(Exception e){
            request.getSession().setAttribute("mensajeError", ""+e.getMessage());
            return "error";
        }
        return forward;
    }
    
    public String modificarPass(){
        String forward = ActionSupport.SUCCESS;
        try{
            String usuario = (String)request.getSession().getAttribute("usuario");
            if(usuario==null){
                forward = "logon";
            }else{
                setResult(acceso.getAccesos(usuario, getActual()));
                if(isResult()){
                    acceso.modifyPassword(usuario, getNueva1());
                }else{
                    forward = "error";
                    request.getSession().setAttribute("mensajeError", "Contraseña invalida");
                }
            }
        }catch(Exception e){
            forward = "error";
            System.out.println(Utilities.stack2string(e));
            request.getSession().setAttribute("mensajeError", Utilities.stack2string(e));
        }
        return forward;
    }
    
    public String execute(){
        request.getSession().setAttribute("usuario", null);
        request.getSession().setAttribute("accesosList", null);
        return SUCCESS;
    }
    public Accesos getAcceso() {
        return acceso;
    }
    public void setAcceso(Accesos acceso) {
        this.acceso = acceso;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        request = hsr;
    }

    @Override
    public void setServletResponse(HttpServletResponse hsr) {
        response = hsr;
    }
    public HttpServletResponse getResponse() {
        return response;
    }
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
    /*Metodos de las nuevas variables*/

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public String getNueva1() {
        return nueva1;
    }

    public void setNueva1(String nueva1) {
        this.nueva1 = nueva1;
    }

    public String getNueva2() {
        return nueva2;
    }

    public void setNueva2(String nueva2) {
        this.nueva2 = nueva2;
    }
    
}