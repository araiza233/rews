package com.mkyong.user.action;
 
import com.mkyong.user.bo.UserBo;
import com.publicidad.entities.Empresa;
import org.springframework.orm.hibernate3.HibernateTemplate;
public class UserSpringAction{
 
	//DI via Spring
	UserBo userBo;
        HibernateTemplate hibernateTemplate;
        
	public UserBo getUserBo() {
		return userBo;
	}
 
	public void setUserBo(UserBo userBo) {
		this.userBo = userBo;
	}
 
	public String execute() throws Exception {
 
		userBo.printUser();
                hibernateTemplate.save(new Empresa());
		return "success";
 
	}

    /**
     * @param hibernateTemplate the hibernateTemplate to set
     */
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
}