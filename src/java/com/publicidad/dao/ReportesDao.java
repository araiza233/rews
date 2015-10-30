/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.publicidad.dao;
import java.util.Date;
import java.util.List;
public interface ReportesDao {
    public List getDataReport(Date fechaInicial, Date fechaFinal, String user, String items);
    public List getReporteAbasto(Date fechaInicial, Date fechaFinal, String user, String items);
    public List getReporteInventario(String items);
    public List getUltimaVenta();
}
