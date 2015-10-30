package com.mkyong.user.action;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.publicidad.logic.SecurityInterceptorLogic;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.StrutsStatics;
public class SecurityInterceptor implements Interceptor, StrutsStatics{
    SecurityInterceptorLogic securityInterceptorLogic;
    @Override
    public void destroy() {
        System.out.println("CustomInterceptor destroy() is called...");
    }

    @Override
    public void init() {
        System.out.println("CustomInterceptor init() is called...");
    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        final ActionContext context = actionInvocation.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) context.get(HTTP_REQUEST);
        String[] urlX = (""+request.getRequestURL()).split("\\?");
        String[] url = urlX[0].split("/");
        String url2 = url[url.length-1];
        System.out.println("URL invocated: "+url2);
        String usuario = (String) request.getSession().getAttribute("usuario");
        String foward = "logon";
        if(usuario!=null){
            boolean continuar = securityInterceptorLogic.getUrlAccess(usuario, url2);
            if(continuar){
                return actionInvocation.invoke();
            }else{
                request.getSession().setAttribute("mensajeError", "No tiene acceso a esta opcion");
                return "error";        
            }
        }
        return foward;
    }
    public SecurityInterceptorLogic getSecurityInterceptorLogic() {
        return securityInterceptorLogic;
    }
    public void setSecurityInterceptorLogic(SecurityInterceptorLogic securityInterceptorLogic) {
        this.securityInterceptorLogic = securityInterceptorLogic;
    }
}
