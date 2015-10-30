  /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.publicidad.dao;
import com.publicidad.entities.Empresa;
import org.springframework.orm.hibernate3.HibernateTemplate;
/**
 *
 * @author Jorge
 */
public class EmpresaDaoImpl implements EmpresaDao{
    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
            this.hibernateTemplate = hibernateTemplate;
    }
    @Override
    public void persist() {
        Empresa u1= new Empresa();
        hibernateTemplate.save(u1);
    }
    
}
