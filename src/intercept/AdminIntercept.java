package intercept;

import bean.People;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Map;

public class AdminIntercept extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        ServletRequest request =  ServletActionContext.getRequest();
        ServletResponse response =  ServletActionContext.getResponse();
        Map session = actionInvocation.getInvocationContext().getSession();
        String path = (String) session.get("path");
        if(path!=null){
        path =  path.substring(path.lastIndexOf('/') + 1);}
        People user = (People)session.get("user");
        if (user.getNature()!=0){
            String info = "对不起！您的权限不够";
            request.setAttribute("info",info);
            request.getRequestDispatcher(path).forward(request,response);
            return null;
        }else {
            return actionInvocation.invoke();
        }
    }
}
