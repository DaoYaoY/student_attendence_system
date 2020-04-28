package action;

import bean.LeaveTable;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import dao.Dao;
import org.apache.struts2.ServletActionContext;

import javax.jms.Session;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Map;

public class LeaveAction extends ActionSupport implements ModelDriven {
    private LeaveTable leaveTable = new LeaveTable();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public String apply() throws Exception {
        System.out.println("Ω· ¯ ±º‰"+leaveTable.getEnd_time());
        Dao dao = new Dao();
        String sql = "insert into `tb_leave` values (null,'"
                +leaveTable.getLeave_reason()+"',"+leaveTable.getStdid()+",'"+leaveTable.getName()+"','"+simpleDateFormat.format(leaveTable.getApply_time())+
                "','"+simpleDateFormat.format(leaveTable.getStart_time())+"','"+simpleDateFormat.format(leaveTable.getEnd_time())+"',"+leaveTable.getNum_leave()+","
                +leaveTable.getMaster_statue()+","+leaveTable.getCollege_statue()+")";
        dao.stu_connetion();
        int res = dao.executeUpdate(sql);
//        System.out.println(res);
        if (res != 0 || res != -1){
            Map session = ActionContext.getContext().getSession();
            session.put("info","success");
            return "apply_success";
        }else{
            Map session = ActionContext.getContext().getSession();
            session.put("info","error");
            return "apply_error";
        }
    }

    public LeaveTable getLeaveTable() {
        return leaveTable;
    }

    public void setLeaveTable(LeaveTable leaveTable) {
        this.leaveTable = leaveTable;
    }

    @Override
    public LeaveTable getModel() {
        return leaveTable;
    }


}
