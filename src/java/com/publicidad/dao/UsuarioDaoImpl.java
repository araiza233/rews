package com.publicidad.dao;
import com.publicidad.entities.Users;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateTemplate;
public class UsuarioDaoImpl implements UsuarioDao{
    private HibernateTemplate hibernateTemplate;
    @Override
    public String saveUser(Users user) {
       String queryHQL = "select count(us.id_usuario) from Users us Where us.id_usuario=?";
       Query query = hibernateTemplate.getSessionFactory().openSession().createQuery(queryHQL);
       query.setParameter(0, user.getId_usuario(), Hibernate.STRING);
       List bounds = query.list();
       Long maxId = (Long)bounds.get(0);
       int cuantosCodigos = maxId.intValue();
       if(cuantosCodigos==0){
          hibernateTemplate.save(user);
          return "resultado";
       }else{
           return "Ya existe un usuario con ese Alias, por favor asigne otro Alias";
       }
    }
    public List getUsers() {
       List results = hibernateTemplate.find("from Users where id_rol=2");
       return results;
    }
    public List getAllUsers(){
        List results = hibernateTemplate.find("from Users");
       return results;
    }
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
    public boolean editar(Users usu) {
       hibernateTemplate.update(usu);
       return true;
   }
   public Users getUsuarioById(String idUsuario) {
        Session session = hibernateTemplate.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Users user = (Users) session.get(Users.class, idUsuario);
        tx.commit(); //update the Student instance
        session.close();
        return user;
    }    
}
