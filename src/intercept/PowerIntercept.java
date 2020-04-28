package intercept;

import bean.People;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import java.util.Map;

public class PowerIntercept extends MethodFilterInterceptor {
    /**
     * 实现权限拦截
     * @param actionInvocation
     * @return
     * @throws Exception
     */
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        Map session = actionInvocation.getInvocationContext().getSession();
        People user = (People)session.get("user");
        switch (user.getNature()){
            case 0:
                return "admin";
            case 1:
                return "stu_index";
            case 2:
                return "teacher_index";
            case 3:
                return "master_index";
            case 4:
                return "sch_leader_index";
            default:
                return "login";
        }

    }
}
