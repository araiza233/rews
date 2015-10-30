/*
 *Jorge Araiza
 *Martes 14 de Enero 2014
 */
package com.publicidad.logic;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.publicidad.dao.ReportesDao;
import com.publicidad.utilities.Utilities;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.itextpdf.text.Rectangle;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
public class ReportesLogicImplementation implements ReportesLogic{
    ReportesDao reportesDao;
    @Override
    public Document getDataReport(Date fechaInicial, Date fechaFin, HttpServletResponse response, String user, String items)
            throws DocumentException, FileNotFoundException, IOException {
        List reporte =  reportesDao.getDataReport(fechaInicial, fechaFin, user, items);
        int recorrer=0;
        int idActual = 0;
        int numeroDeVentas = 0;
        System.out.println("Tamaño del resultado: "+reporte.size());
        int largo = 17*(reporte.size()+6);
        System.out.println("largo: "+largo);
        Document document = new Document(new Rectangle(164, largo));
	document.setMargins(0, 0, 0, 0);
        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream() );
        document.open();
        writer.addJavaScript("this.print(false);", false);
        
        PdfPTable table = new PdfPTable(4);
        table.setWidths(new float[]{2, 1, 1,1});
        table.setHorizontalAlignment(table.ALIGN_LEFT);
        PdfPCell cell = null;
        Map<String, Object> rep = null;
        float totalisimo = 0;
        float totalXdia=0;
        String fecha = "";
        Font font = new Font( FontFamily.HELVETICA, 6, Font.BOLD, BaseColor.BLACK);
        Font letras = new Font(FontFamily.HELVETICA, 6);
        for(Object object:reporte){
            rep = (Map<String, Object>)object;
            if(recorrer==0){//venta inicial
                numeroDeVentas++;
                idActual = Utilities.String2int(rep.get("idventas").toString());
                recorrer++;
                fecha = rep.get("fechadeventa").toString();
                document.add(new Paragraph("Fecha: "+Utilities.printFecha(fecha)+", Venta "+numeroDeVentas+", id: "+rep.get("idventas").toString()+", Usuario: "+user, font));
                cell = new PdfPCell(new Phrase("Producto", font));  
                table.addCell(cell); 
                cell = new PdfPCell(new Phrase("Precio", font));
                table.addCell(cell); 
                cell = new PdfPCell(new Phrase("Cantidad", font));
                table.addCell(cell); 
                cell = new PdfPCell(new Phrase("Total", font));
                table.addCell(cell); 
                cell = new PdfPCell(new Phrase(rep.get("nombre").toString(), letras));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(rep.get("precio").toString(), letras));
                table.addCell(cell); 
                cell = new PdfPCell(new Phrase(rep.get("cantidad").toString(), letras));
                table.addCell(cell); 
                cell = new PdfPCell(new Phrase(rep.get("total").toString(), letras));
                table.addCell(cell);
                totalisimo = Utilities.String2Float(rep.get("totalventa").toString());
            }else if(idActual != Utilities.String2int(rep.get("idventas").toString())){//nueva venta
                cell = new PdfPCell(new Phrase("Total: ", font)); 
		cell.setColspan(3); 
		table.addCell(cell); 
		table.addCell(new Phrase(Utilities.Float2String(totalisimo), font)); 
                totalXdia +=totalisimo;
                document.add(table); 
                numeroDeVentas++;
                idActual = Utilities.String2int(rep.get("idventas").toString());
                if(fecha.equalsIgnoreCase(rep.get("fechadeventa").toString())){
                    document.add(new Paragraph("Venta "+numeroDeVentas+", idVenta: "+rep.get("idventas").toString(), letras));
                }else{
                   fecha = rep.get("fechadeventa").toString();
                   document.add(new Paragraph("Total vendido dia: "+totalXdia, font));
                   document.add(new Paragraph("Fecha: "+Utilities.printFecha(fecha) +", Venta "+numeroDeVentas+", id: "+rep.get("idventas").toString(), font));
                   totalXdia=0;
                }
                table = new PdfPTable(4);
                table.setHorizontalAlignment(table.ALIGN_LEFT);
                table.setWidths(new float[]{2, 1, 1,1});
                cell = new PdfPCell(new Phrase("Producto", font));  
                table.addCell(cell); 
                cell = new PdfPCell(new Phrase("Precio", font));
                table.addCell(cell); 
                cell = new PdfPCell(new Phrase("Cantidad", font));
                table.addCell(cell); 
                cell = new PdfPCell(new Phrase("Total", font));
                table.addCell(cell); 
                
                cell = new PdfPCell(new Phrase(rep.get("nombre").toString(), letras));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(rep.get("precio").toString(), letras));
                table.addCell(cell); 
                cell = new PdfPCell(new Phrase(rep.get("cantidad").toString(), letras));
                table.addCell(cell); 
                cell = new PdfPCell(new Phrase(rep.get("total").toString(), letras));
                table.addCell(cell);
                totalisimo = Utilities.String2Float(rep.get("totalventa").toString());
            }else{//Datos de la misma venta
                cell = new PdfPCell(new Phrase(rep.get("nombre").toString(), letras));
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(rep.get("precio").toString(), letras));
                table.addCell(cell); 
                cell = new PdfPCell(new Phrase(rep.get("cantidad").toString(), letras));
                table.addCell(cell); 
                cell = new PdfPCell(new Phrase(rep.get("total").toString(), letras));
                table.addCell(cell);
                totalisimo = Utilities.String2Float(rep.get("totalventa").toString());
            }
        }
        if(numeroDeVentas==0){
            document.add(new Paragraph("No hay ventas registradas entre ese periodo de fechas", font));
        }else{
            cell = new PdfPCell(new Phrase("Total: ", font)); 
            cell.setColspan(3); 
            table.addCell(cell); 
            table.addCell(new Phrase(Utilities.Float2String(totalisimo), font));
            document.add(table);
            totalXdia +=totalisimo;
            document.add(new Paragraph("Total vendido dia: "+totalXdia, font));
        }
        document.close();
        return document;
    }
    @Override
    public Document getReporteAbasto(Date fechaInicial, Date fechaFin, HttpServletResponse response, String user, String items) 
            throws DocumentException, FileNotFoundException, IOException {
        List reporte =  reportesDao.getReporteAbasto(fechaInicial, fechaFin, user, items);
        int largo = 17*(reporte.size()+6);
        System.out.println("largo: "+largo);
        Document document = new Document(new Rectangle(164, largo));
	document.setMargins(0, 0, 0, 0);
        PdfWriter writer;
       
            writer = PdfWriter.getInstance(document, response.getOutputStream());
       
        document.open();
        writer.addJavaScript("this.print(false);", false);
        PdfPTable table = null;
        PdfPCell cell = null;
        Map<String, Object> rep = null;
        int totalArticulos = 0;
        String articulo = "";
        String numeroVentas = "";
        Font font = new Font( FontFamily.HELVETICA, 6, Font.BOLD, BaseColor.BLACK);
        Font letras = new Font(FontFamily.HELVETICA, 6);
        for(Object object:reporte){
            rep = (Map<String, Object>)object;
            if(totalArticulos==0){
                document.add(new Paragraph("REPORTE DE ABASTO---Fecha de inicio: "+fechaInicial.toString()+", Fecha final: "+fechaFin.toString()+", Usuario: "+user, font));
                table = new PdfPTable(2);
                table.setWidths(new float[]{2, 1});
                cell = new PdfPCell(new Phrase("Producto", font));  
                table.addCell(cell); 
                cell = new PdfPCell(new Phrase("Cantidad", font));
                table.addCell(cell); 
            }
            totalArticulos++;
            articulo = rep.get("nombre").toString();
            cell = new PdfPCell(new Phrase(articulo,letras ));
            table.addCell(cell); 
            numeroVentas = rep.get("vendidos").toString();
            cell = new PdfPCell(new Phrase(numeroVentas, letras));
            table.addCell(cell);     
        }
        if(reporte.size()==0){
            document.add(new Paragraph("No hay articulos entre ese rango de fechas", font));
        }else{
            document.add(table);
            document.add(new Paragraph("Total articulos: "+totalArticulos, font));
        }
        document.close();
        return document;
    }
    @Override
    public Document getReporteInventario(HttpServletResponse response, String items) throws DocumentException, FileNotFoundException, IOException {
        List reporte =  reportesDao.getReporteInventario(items);
        int largo = 17*(reporte.size()+6);
        System.out.println("largo: "+largo);
        if(largo>14400){
            largo = 14000;
        }
        Document document = new Document(new Rectangle(164, largo));
	document.setMargins(0, 0, 0, 0);
        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        //writer.addJavaScript("this.print(false);", false);
        PdfPTable table = null;
        PdfPCell cell = null;
        Map<String, Object> rep = null;
        int totalArticulos = 0;
        String articulo = "";
        String numeroVentas = "";
        Font font = new Font( FontFamily.HELVETICA, 6, Font.BOLD, BaseColor.BLACK);
        Font letras = new Font(FontFamily.HELVETICA, 6);
        for(Object object:reporte){
            rep = (Map<String, Object>)object;
            if(totalArticulos==0){
                document.add(new Paragraph("Reporte de inventario"));
                table = new PdfPTable(3); 
                table.setWidths(new float[]{0.6f, 2, 0.6f});
                cell = new PdfPCell(new Phrase(" " , font));  
                table.addCell(cell); 
                cell = new PdfPCell(new Phrase("Producto", font));  
                table.addCell(cell); 
                cell = new PdfPCell(new Phrase("Cantidad", font));
                table.addCell(cell); 
            }
            totalArticulos++;
            cell = new PdfPCell(new Phrase(totalArticulos+"" , font));  
            table.addCell(cell); 
            articulo = rep.get("nombre").toString();
            cell = new PdfPCell(new Phrase(articulo, letras));
            table.addCell(cell); 
            numeroVentas = rep.get("piezas")==null?"0":rep.get("piezas").toString();
            cell = new PdfPCell(new Phrase(numeroVentas, letras));
            table.addCell(cell);     
        }
        document.add(table);
        document.add(new Paragraph("Total articulos: "+totalArticulos, font));
        document.close();
        return document;
    }
    public ReportesDao getReportesDao() {
        return reportesDao;
    }
    
    public void setReportesDao(ReportesDao reportesDao) {
        this.reportesDao = reportesDao;
    }
    public Document getUltimaVenta(HttpServletResponse response) throws DocumentException, FileNotFoundException, IOException {
        List reporte =  reportesDao.getUltimaVenta();
        ArrayList articulos = new ArrayList();
        Map<String, Object> rep = null;
        String nombre = "";
        String cantidad = "";
        String pu = "";
        String total = "";
        String totalVenta = "";
        String pago="";
        String cambio="";
        int arti= 0;
        Font font = new Font( FontFamily.COURIER, 8, Font.BOLD, BaseColor.BLACK);
        int limite =10;
        for(Object object:reporte){
            rep = (Map<String, Object>)object;
            nombre = rep.get("nombre").toString();
            if(nombre.length()>=limite){
                nombre = nombre.substring(0,limite);
            }else{
                int faltan = limite-nombre.length();
                String agregar="";
                System.out.println("Se van a agregar a: "+nombre+", "+faltan);
                for(int i=0; i<faltan; i++){
                    agregar = agregar+" ";
                }
                nombre = nombre + agregar;
            }
            cantidad = rep.get("cantidad").toString();
            total = rep.get("total").toString();
            pu = rep.get("preciounitario").toString();
            String art = formatoCantidad(cantidad)+"-"+nombre+""+"$"+formatoPrecio(pu)+"$"+formatoPrecio(total);
            articulos.add(art);
            if(arti==0){
                arti++;
                totalVenta = rep.get("totalventa").toString();
                pago = rep.get("pago").toString();
                cambio = rep.get("cambio").toString();
            }
            System.out.println("Articulo: "+nombre+", longitud: "+nombre.length());
        }
        int largo = 17*(articulos.size()+6);
        System.out.println("largo: "+largo);
        Document document = new Document(new Rectangle(164, largo));
	document.setMargins(0, 0, 0, 0);
        PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        writer.addJavaScript("this.print(false);", false);
        if(reporte.size()==0){
            document.add(new Paragraph("No hay articulos registrados", font));
        }else{
            document.add(new Paragraph("ABARROTES EL GUERO", font));
            document.add(new Paragraph("CANT PRODUCTO PRECIO SUBT", font));
            document.add(new Paragraph("-------------------------------", font));
            for(int i=0; i<articulos.size();i++){
                document.add(new Paragraph((String)articulos.get(i), font));
            }
        }
        document.add(new Paragraph("                ----------", font));
        document.add(new Paragraph("             TOTAL:$"+totalVenta, font));
        document.add(new Paragraph("              PAGO:$"+pago, font));
        document.add(new Paragraph("            CAMBIO:$"+cambio, font));
        document.close();
        return document;
    }
    public String getPrecioUnitario(String cantidad, String total){
        float cant = Utilities.String2Float(cantidad);
        float tota = Utilities.String2Float(total);
        float pu = tota/cant;
        return Utilities.Float2String(pu);
    }
    public String formatoCantidad(String cant){
        try{
            int canti = Utilities.String2int(cant);
            if (canti<10){
                cant = "   "+cant;
            }else if(canti>=10 && canti <100){
                cant = "  "+cant;
            }
        }catch(Exception e ){
            float cantidad = Utilities.String2Float(cant);
            if(cantidad>=10 && cantidad <100){
                return cant.substring(0, 4);
            }
        }
        
        return llegarA4(cant);
    }
    public String llegarA4(String cant){
        int longitud = cant.length();
        if(longitud>4){
        	cant = cant.substring(0, 4);
        }else{
            while(longitud<4){
            	cant = cant+" ";
            	longitud = cant.length();	
            }
        }
        return cant;
    }
    public String formatoPrecio(String pre){
        float precio = Utilities.String2Float(pre);
        if (precio<10){
            return "  "+precio;
        }else if(precio>=10 && precio <100){
            return " "+precio;
        }else{
            return ""+precio;
        }
    }
}