/*
 Creado por Jorge Araiza
 * Martes 14 de Enero 2014
 */
package org.apache.struts.publicidad.action;
import com.opensymphony.xwork2.ActionSupport;
import com.publicidad.logic.ReportesLogic;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.publicidad.entities.Users;
import com.publicidad.logic.UsuarioLogic;
import java.util.Date;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
public class ReportesAction extends ActionSupport implements ServletRequestAware, ServletResponseAware{
    private String fechaFinal;
    private String fechaInicial;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String selectReporte;
    private ReportesLogic reportesLogic;
    private int REPORTE_DETALLADO_VENTAS = 1;
    private int REPORTE_ABASTO = 2;
    private int REPORTE_INVENTARIO = 3;
    private int MOSTRAR_PAGINA = 4;
    private List usuariosList;
    private UsuarioLogic usuarioLogic;
    private String usuario;
    private String items;
    @Override
    public String execute() {
        String resultado = SUCCESS;
        try{
            //request = ServletActionContext.getRequest();
            String archivoReporte = request.getRealPath("/")+"/reportes/reportes.pdf";//reportes en UNIX
            //String archivoReporte = request.getRealPath("/")+"reportes\\reportes.pdf";//Reportes en WINDOWS
            //response = ServletActionContext.getResponse();
            System.out.println("Reporte creado en: "+archivoReporte);
            /*setFechaInicial((String)request.getAttribute("fechaInicial"));
            setFechaFinal((String)request.getAttribute("fechaFinal"));*/
            Document doc = null;
            if(REPORTE_DETALLADO_VENTAS==Integer.parseInt(selectReporte)){
                doc = reportesLogic.getDataReport(new Date(fechaInicial), new Date(fechaFinal),response, usuario, items);
                response.setContentType("application/pdf");
                PdfWriter.getInstance(doc, response.getOutputStream());
            }else if (REPORTE_ABASTO==Integer.parseInt(selectReporte)){
                doc = reportesLogic.getReporteAbasto(new Date(fechaInicial), new Date(fechaFinal),response, usuario, items);
                response.setContentType("application/pdf");
                PdfWriter.getInstance(doc, response.getOutputStream());
            }else if (REPORTE_INVENTARIO==Integer.parseInt(selectReporte)){
                doc = reportesLogic.getReporteInventario(response, items);
                response.setContentType("application/pdf");
                PdfWriter.getInstance(doc, response.getOutputStream());
            }else if (MOSTRAR_PAGINA==Integer.parseInt(selectReporte)){
                usuariosList = usuarioLogic.getAllUsers();
                Users us = new Users();
                us.setId_usuario("Todos");
                us.setNombre("Todos");
                usuariosList.add(0, us);
                resultado = "mostrar";
            }
        }catch(Exception e){
            request.getSession().setAttribute("mensajeError", ""+e.getMessage());
            e.printStackTrace();
            resultado = "error";
        }
        return resultado;
    }
    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public ReportesLogic getReportesLogic() {
        return reportesLogic;
    }

    public void setReportesLogic(ReportesLogic reportesLogic) {
        this.reportesLogic = reportesLogic;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        request = hsr;
    }

    @Override
    public void setServletResponse(HttpServletResponse hsr) {
        response = hsr;
    }

    public String getSelectReporte() {
        return selectReporte;
    }

    public void setSelectReporte(String selectReporte) {
        this.selectReporte = selectReporte;
    }
    public List getUsuariosList() {
        return usuariosList;
    }
    public void setUsuariosList(List usuariosList) {
        this.usuariosList = usuariosList;
    }
    public UsuarioLogic getUsuarioLogic() {
        return usuarioLogic;
    }
    public void setUsuarioLogic(UsuarioLogic usuarioLogic) {
        this.usuarioLogic = usuarioLogic;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }
    
}
