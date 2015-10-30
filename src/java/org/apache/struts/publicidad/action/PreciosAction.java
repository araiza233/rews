package org.apache.struts.publicidad.action;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
public class PreciosAction extends ActionSupport implements ServletRequestAware, ServletResponseAware  {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private List<String> precioOpciones;
    private String precioSeleccionado;
    private String opcion;
    private static String OPCION_RECARGAR = "2";
    public PreciosAction(){
        precioOpciones = new ArrayList<String>();
	precioOpciones.add("publico");
	precioOpciones.add("mayoreo");
	precioOpciones.add("especial");
        //precioSeleccionado = (String)request.getSession().getAttribute("precios");
    }
    public String getDefaultPrecioValue(){
        return (String)request.getSession().getAttribute("precios");
    }
    public String execute(){
        if(opcion.equalsIgnoreCase(OPCION_RECARGAR)){
            request.getSession().setAttribute("precios", precioSeleccionado);
        }else{
            precioSeleccionado = (String)request.getSession().getAttribute("precios");
        }
        return SUCCESS;
    }
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        request = hsr;
    }

    @Override
    public void setServletResponse(HttpServletResponse hsr) {
        response = hsr;
    }

    public String getPrecioSeleccionado() {
        return precioSeleccionado;
    }

    public void setPrecioSeleccionado(String precioSeleccionado) {
        this.precioSeleccionado = precioSeleccionado;
    }

    public List<String> getPrecioOpciones() {
        return precioOpciones;
    }

    public void setPrecioOpciones(List<String> precioOpciones) {
        this.precioOpciones = precioOpciones;
    }
    
    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }
}