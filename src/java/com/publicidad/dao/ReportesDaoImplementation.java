package com.publicidad.dao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.HibernateTemplate;
public class ReportesDaoImplementation implements ReportesDao{
    HibernateTemplate hibernateTemplate;
    public String getDataReport2(String items){
        String jpaQL = "";
        jpaQL = "SELECT vtas.idventas, vtas.fechadeventa, vtas.totalventa, detvtas.cantidad, detvtas.total,"
            +"pro.nombre, pro.unidaddeventa, pro.codigo, pro.precio FROM ventas vtas JOIN detalleventas detvtas ON detvtas.idventas = vtas.idventas"
            +" JOIN producto pro ON detvtas.idproducto = pro.idproducto WHERE vtas.fechadeventa between :F1 AND :F2 and pro.codigo IN ( "+items+")"
            + "ORDER BY vtas.idventas, vtas.fechadeventa ASC;";
        return jpaQL;
    }
    public String getDataReport3(String items){
        String jpaQL = "";
        jpaQL = "SELECT vtas.idventas, vtas.fechadeventa, vtas.totalventa, detvtas.cantidad, detvtas.total,"
                +"pro.nombre, pro.unidaddeventa, pro.codigo, pro.precio FROM ventas vtas JOIN detalleventas detvtas ON detvtas.idventas = vtas.idventas"
                +" JOIN producto pro ON detvtas.idproducto = pro.idproducto WHERE vtas.fechadeventa between :F1 AND :F2 "
                +"and vtas.vendidopor=:User and pro.codigo IN ("+items+")  "
                + "ORDER BY vtas.idventas, vtas.fechadeventa ASC;";
        return jpaQL;
    }
    public List getDataReport(Date fechaInicial, Date fechaFinal, String user, String items) {
        String jpaQL = "";
        SQLQuery sqlQuery = null;
        if(items.equals("")){
            if (user.equalsIgnoreCase("todos")){
                jpaQL = "SELECT vtas.idventas, vtas.fechadeventa, vtas.totalventa, detvtas.cantidad, detvtas.total,"
                +"pro.nombre, pro.unidaddeventa, pro.codigo, pro.precio FROM ventas vtas JOIN detalleventas detvtas ON detvtas.idventas = vtas.idventas"
                +" JOIN producto pro ON detvtas.idproducto = pro.idproducto WHERE vtas.fechadeventa between :F1 AND :F2 "
                + "ORDER BY vtas.idventas, vtas.fechadeventa ASC;";
                sqlQuery = hibernateTemplate.getSessionFactory().openSession().createSQLQuery(jpaQL);
                sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                sqlQuery.setTimestamp("F1", fechaInicial);
                sqlQuery.setTimestamp("F2", fechaFinal);
            }else{
                jpaQL = "SELECT vtas.idventas, vtas.fechadeventa, vtas.totalventa, detvtas.cantidad, detvtas.total,"
                +"pro.nombre, pro.unidaddeventa, pro.codigo, pro.precio FROM ventas vtas JOIN detalleventas detvtas ON detvtas.idventas = vtas.idventas"
                +" JOIN producto pro ON detvtas.idproducto = pro.idproducto WHERE vtas.fechadeventa between :F1 AND :F2 "
                +"and vtas.vendidopor=:User "
                + "ORDER BY vtas.idventas, vtas.fechadeventa ASC;";
                sqlQuery = hibernateTemplate.getSessionFactory().openSession().createSQLQuery(jpaQL);
                sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                sqlQuery.setTimestamp("F1", fechaInicial);
                sqlQuery.setTimestamp("F2", fechaFinal);
                sqlQuery.setParameter("User", user);
            }
        }else{
             if (user.equalsIgnoreCase("todos")){
                jpaQL = getDataReport2(items);
                sqlQuery = hibernateTemplate.getSessionFactory().openSession().createSQLQuery(jpaQL);
                sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                sqlQuery.setTimestamp("F1", fechaInicial);
                sqlQuery.setTimestamp("F2", fechaFinal);
                
            }else{
                jpaQL = getDataReport3(items);
                sqlQuery = hibernateTemplate.getSessionFactory().openSession().createSQLQuery(jpaQL);
                sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                sqlQuery.setTimestamp("F1", fechaInicial);
                sqlQuery.setTimestamp("F2", fechaFinal);
                sqlQuery.setParameter("User", user);
            }
        }
        List list = sqlQuery.list();
        return list;
    }
    public List getReporteAbasto(Date fechaInicial, Date fechaFinal, String user, String items) {
        String jpaQL = "";
        SQLQuery sqlQuery = null;
        if(items.equals("")){
            if (user.equalsIgnoreCase("todos")){
                jpaQL = "select rep.nombre, sum(rep.cantidad) as vendidos from view_reportes rep "
                    + "where  rep.fechadeventa between :F1 AND :F2 "
                    + "group by rep.codigo, rep.nombre order by vendidos desc;";
                sqlQuery = hibernateTemplate.getSessionFactory().openSession().createSQLQuery(jpaQL);
                sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                sqlQuery.setTimestamp("F1", fechaInicial);
                sqlQuery.setTimestamp("F2", fechaFinal);
            }else{
                jpaQL = "select rep.nombre, sum(rep.cantidad) as vendidos from view_reportes rep "
                    + "where  rep.fechadeventa between :F1 AND :F2 and vendidopor=:user "
                    + "group by rep.codigo, rep.nombre order by vendidos desc;";
                sqlQuery = hibernateTemplate.getSessionFactory().openSession().createSQLQuery(jpaQL);
                sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                sqlQuery.setTimestamp("F1", fechaInicial);
                sqlQuery.setTimestamp("F2", fechaFinal);
                sqlQuery.setParameter("user", user);
            }
        }else{
            if (user.equalsIgnoreCase("todos")){
                jpaQL = "select rep.nombre, sum(rep.cantidad) as vendidos from view_reportes rep "
                    + "where  rep.fechadeventa between :F1 AND :F2 and rep.codigo in ("+items+") "
                    + "group by rep.codigo, rep.nombre order by vendidos desc;";
                sqlQuery = hibernateTemplate.getSessionFactory().openSession().createSQLQuery(jpaQL);
                sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                sqlQuery.setTimestamp("F1", fechaInicial);
                sqlQuery.setTimestamp("F2", fechaFinal);
            }else{
                jpaQL = "select rep.nombre, sum(rep.cantidad) as vendidos from view_reportes rep "
                    + "where  rep.fechadeventa between :F1 AND :F2 and vendidopor=:user and rep.codigo in ("+items+") "
                    + "group by rep.codigo, rep.nombre order by vendidos desc;";
                sqlQuery = hibernateTemplate.getSessionFactory().openSession().createSQLQuery(jpaQL);
                sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                sqlQuery.setTimestamp("F1", fechaInicial);
                sqlQuery.setTimestamp("F2", fechaFinal);
                sqlQuery.setParameter("user", user);
            }
        }
        List list = sqlQuery.list();
        return list;
    }//fin metodo getReporteAbasto
    public List getReporteInventario(String items) {
        String jpaQL = "";
        if(items.equals("")){
            jpaQL = "select pro.nombre, pro.piezas from producto pro order by pro.descripcion;";
            SQLQuery sqlQuery = hibernateTemplate.getSessionFactory().openSession().createSQLQuery(jpaQL);
            sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List list = sqlQuery.list();
            return list;
        }else{
            jpaQL = "select pro.nombre, pro.piezas from producto pro where pro.codigo in("+items+") order by pro.descripcion;";
            SQLQuery sqlQuery = hibernateTemplate.getSessionFactory().openSession().createSQLQuery(jpaQL);
            sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List list = sqlQuery.list();
            return list;
        }
    }//fin metodo getReporteInventario
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
/*Metodo que obtiene informacion de la ultima venta*/    
    public List getUltimaVenta() {
        String jpaQL = "";
        SQLQuery sqlQuery = null;
        jpaQL = "select det.*, pro.nombre, vta.fechadeventa, vta.totalventa,vta.pago, vta.cambio "+
            "from detalleVentas det inner join producto pro on pro.idProducto = det.idProducto "+
            "inner join ventas vta on det.idVentas = vta.idVentas "+
            "where det.idVentas=(select max(vtas.idVentas) from ventas vtas)";
        sqlQuery = hibernateTemplate.getSessionFactory().openSession().createSQLQuery(jpaQL);
        sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        return list;
    }//fin metodo getUltimaVenta
}