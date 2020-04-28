package intercept;

import bean.People;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import java.util.Map;

public class TeacherIntercept extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        Map session = actionInvocation.getInvocationContext().getSession();
        People user = (People)session.get("user");
        if (user.getNature() < 2 && user.getNature()!=0){
            String info = "�Բ�������Ȩ�޲���";
            return "error";
        }else {
            return actionInvocation.invoke();
        }
    }
}
