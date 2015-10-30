package com.publicidad.dao;
import com.publicidad.entities.DetalleVentas;
import com.publicidad.entities.Producto;
import com.publicidad.entities.Ventas;
import com.publicidad.utilities.Utilities;
import java.sql.CallableStatement;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class NuevoProductoDaoImp implements NuevoProductoDao{
   HibernateTemplate hibernateTemplate;
   public String guardar(Producto producto) {
       if(producto.getCodigo().equalsIgnoreCase("0000")){
           return guardarRapido(producto);
       }else{
           return guardarReal(producto);
       }
   }
   public String editar(Producto producto)  {
       List results = hibernateTemplate.find("from Producto where codigo='"+producto.getCodigo()+"' or idProducto="+producto.getIdProducto());
       if(results.size()==1){
           hibernateTemplate.update(producto);
           return "Registro guardado exitosamente";
       }else{
           return "Ya existe un producto guardado con ese mismo codigo de barras, por favor verifique";
       }
   }
   public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
   }
   public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
   }
   public List buscarProducto(String text, int op){
        //List results = hibernateTemplate.find("from Producto where upper(nombre) like '%"+text.toUpperCase()+"%' or codigo ='"+text+"'");
       String queryHQL = "SELECT count(DISTINCT vta.idVentas) FROM Ventas vta";
       Query query = hibernateTemplate.getSessionFactory().openSession().createQuery(queryHQL);
       List bounds = query.list();
       Long maxId = (Long)bounds.get(0);
       int ventasAlMomento = maxId.intValue();
       if(ventasAlMomento<=7000){
           if(op==1){
               List results = hibernateTemplate.find("from Producto where upper(nombre) like '%"+text.toUpperCase()+"%'");
               return results;
           }else if(op==2){
               List results = hibernateTemplate.find("from Producto where codigo ='"+text+"'");
               return results;
           }
       }else{
           return null;
       }
       return null;
   }
   public String guardarVenta(Ventas venta) {//guardarVenta(Ventas ventas)
        hibernateTemplate.save(venta);
        return "Registro guardado exitosamente";
   }
   public String guardarDetalleVentas(String detalles){
        String queryHQL = "SELECT nueva(?) ";
        SQLQuery query = hibernateTemplate.getSessionFactory().openSession().createSQLQuery(queryHQL);
        query.setParameter(0, detalles, Hibernate.STRING);
        //query.executeUpdate();
        List bounds = query.list();
        Integer maxId = (Integer)bounds.get(0);
        return "Registro guardado exitosamente";
   }
   public String guardarRapido(Producto producto ){
       try{
            String queryHQL = "SELECT guardaragranel(?,?,?) ";
            SQLQuery query = hibernateTemplate.getSessionFactory().openSession().createSQLQuery(queryHQL);
            query.setParameter(0, producto.getNombre(), Hibernate.STRING);
            query.setParameter(1, producto.getPrecio(), Hibernate.FLOAT);
            query.setParameter(2, producto.getCreadoPor(), Hibernate.STRING);
            List bounds = query.list();
            Integer maxId = (Integer)bounds.get(0);
            producto.setCodigo(maxId.toString());
            return "Ok";
       }catch(Exception e){
           System.out.println(Utilities.stack2string(e));
           return "Error: "+Utilities.stack2string(e);
       }
   }//fin metodo guardarRapido
   public String guardarReal(Producto producto){
       List results = hibernateTemplate.find("from Producto where codigo='"+producto.getCodigo().trim()+"'");
       if(results.isEmpty()){
           producto.setIdProducto(getNuevoIdProducto());
           hibernateTemplate.save(producto);
           return "Ok";
       }else{
           return "Ya existe un producto guardado con ese mismo codigo de barras, por favor verifique";
       }
   }//fin metodo guardarReal

    @Override
    public Producto getProductoById(int idProducto) {
        Session session = hibernateTemplate.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Producto pro = (Producto) session.get(Producto.class, idProducto);
        pro.setIdProducto(idProducto);
        tx.commit(); //update the Student instance
        session.close();
        return pro;
    }
    public int getNuevoIdProducto(){
       String queryHQL = "select max(pro.idProducto) from Producto pro";
       Query query = hibernateTemplate.getSessionFactory().openSession().createQuery(queryHQL);
       List bounds = query.list();
       Integer maxId = (Integer)bounds.get(0);
       int nuevoId = maxId.intValue();
       nuevoId=nuevoId+1;
       return nuevoId;
    }
    public String editarVenta(Producto producto)  {
       List results = hibernateTemplate.find("from Producto where codigo='"+producto.getCodigo()+"' or idProducto="+producto.getIdProducto());
       if(results.size()==1){
           Producto produ = (Producto) results.get(0);
           Float piezas = (produ.getPiezas()==null?0:produ.getPiezas());
           produ.setPiezas(producto.getPiezas()+piezas);
           produ.setNombre(producto.getNombre());
           produ.setPrecio(producto.getPrecio());
           hibernateTemplate.update(produ);
           producto.setCodigo(produ.getCodigo());
           return "Registro guardado exitosamente";
       }else{
           return "Ya existe un producto guardado con ese mismo codigo de barras, por favor verifique";
       }
   }
}