package com.publicidad.dao;
import com.publicidad.entities.Producto;
import com.publicidad.entities.Users;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateTemplate;
public class SecurityDaoImpl implements SecurityDao{
    private HibernateTemplate hibernateTemplate;
    @Override
    public List getUrlAccess(String user, String urlToBeAccess) {
        String sql = "select men.url from users us "
                + "inner join roles rol on us.id_rol = rol.id_rol "
                + "inner join menu men on rol.id_rol = men.id_rol "
                + "where us.id_usuario=:F1 "
                + "and men.url=:F2 "
                + "UNION "
                + "select op.url from users us "
                + "inner join roles rol on us.id_rol = rol.id_rol "
                + "inner join menu men on rol.id_rol = men.id_rol "
                + "left join opciones_menu op on op.id_menu=men.id_menu "
                + "where us.id_usuario=:F1 "
                + "and op.url is not null "
                + "and op.url=:F2";
        SQLQuery sqlQuery = hibernateTemplate.getSessionFactory().openSession().createSQLQuery(sql);
        sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        sqlQuery.setParameter("F1", user);
        sqlQuery.setParameter("F2", urlToBeAccess);
        List list = sqlQuery.list();
        return list;
    }
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
    public boolean getAccesos(String user, String pwd) {
       String queryHQL = "select count(us.id_usuario) from Users us Where us.id_usuario=? and us.password=?";
       //String queryHQL = "select count(pro.codigo) from Producto pro Where pro.codigo='"+codiguito+"'";
       Query query = hibernateTemplate.getSessionFactory().openSession().createQuery(queryHQL);
       query.setParameter(0, user, Hibernate.STRING);
       query.setParameter(1, pwd, Hibernate.STRING);
       List bounds = query.list();
       Long maxId = (Long)bounds.get(0);
       int cuantosCodigos = maxId.intValue();
       if(cuantosCodigos==0){
          return false;
       }else{
           return true;
       }
    }
/*
* 24 de Febrero
* Metodo que obtiene las opciones del menu a las que el usuario tiene permiso de accesar
*/
    public List getMenuOptions(String user) {
       String sql = "select men.url from users us "
                + "inner join roles rol on us.id_rol = rol.id_rol "
                + "inner join menu men on rol.id_rol = men.id_rol "
                + "where us.id_usuario=:F1 order by men.id_menu";
        SQLQuery sqlQuery = hibernateTemplate.getSessionFactory().openSession().createSQLQuery(sql);
        sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        sqlQuery.setParameter("F1", user);
        List list = sqlQuery.list();
        return list;
    }
    public boolean modifyPassword(Users user, String newPasswd){
       List results = hibernateTemplate.find("from Users where id_usuario='"+user.getId_usuario()+"' and password='"+user.getPassword()+"'");
       if(results.size()==1){
           user.setPassword(newPasswd);
           hibernateTemplate.update(user);
           return true;
       }else{
           return false;
       }
    }
/*
* 09 de Marzo 2014
* 
*/
    public Users getUserById(String idUser) {
        Session session = hibernateTemplate.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Users user = (Users) session.get(Users.class, idUser);
        tx.commit(); //update the Student instance
        session.close();
        return user;
    }
}
