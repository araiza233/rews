/*
 Creado por Jorge Araiza
 * Martes 12 de Mayo 2014
 */
package org.apache.struts.publicidad.action;
import com.itextpdf.text.DocumentException;
import com.opensymphony.xwork2.ActionSupport;
import com.publicidad.logic.ReportesLogic;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.publicidad.entities.Users;
import com.publicidad.logic.UsuarioLogic;
import java.util.Date;
import java.util.List;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
public class TicketsAction extends ActionSupport implements ServletRequestAware, ServletResponseAware{
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ReportesLogic reportesLogic;
    @Override
    public String execute() {
        String archivoReporte = request.getRealPath("/")+"/reportes/ticket.pdf";//reportes en UNIX
        String resultado = SUCCESS;
        try {
            Document doc = reportesLogic.getUltimaVenta(response);
            response.setContentType("application/pdf");
            PdfWriter.getInstance(doc, response.getOutputStream());
        } catch (DocumentException ex) {
            Logger.getLogger(TicketsAction.class.getName()).log(Level.SEVERE, null, ex);
            resultado = "error";
            request.getSession().setAttribute("mensajeError", ""+ex.getMessage());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TicketsAction.class.getName()).log(Level.SEVERE, null, ex);
            resultado = "error";
            request.getSession().setAttribute("mensajeError", ""+ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(TicketsAction.class.getName()).log(Level.SEVERE, null, ex);
            resultado = "error";
            request.getSession().setAttribute("mensajeError", ""+ex.getMessage());    
        }
        return resultado;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        request = hsr;
    }

    @Override
    public void setServletResponse(HttpServletResponse hsr) {
        response = hsr;
    }
    public ReportesLogic getReportesLogic() {
        return reportesLogic;
    }
    public void setReportesLogic(ReportesLogic reportesLogic) {
        this.reportesLogic = reportesLogic;
    }
}