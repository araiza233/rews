/*
 * Created by Jorge Araiza
 * Martes 14 de Enero 2014
 */
package com.publicidad.logic;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
public interface ReportesLogic {
    public Document getDataReport(Date fechaInicial, Date FechaFin, HttpServletResponse response, String user, String items) 
            throws DocumentException, FileNotFoundException, IOException;
    public Document getReporteAbasto(Date fechaInicial, Date fechaFin, HttpServletResponse response, String user, String items)
            throws DocumentException, FileNotFoundException, IOException;
    public Document getReporteInventario(HttpServletResponse response, String items)
            throws DocumentException, FileNotFoundException, IOException;
    public Document getUltimaVenta(HttpServletResponse response) throws DocumentException, FileNotFoundException, IOException;
}
