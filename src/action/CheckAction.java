package action;

import bean.LeaveTable;
import bean.People;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.Dao;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CheckAction extends ActionSupport {
    private LeaveTable lt = new LeaveTable();
    private Dao dao = new Dao();
    private List list = new ArrayList();
    Map session = ActionContext.getContext().getSession();
    People p = (People)session.get("user");

    public List getList() {
        return list;
    }

    public String read_noCheck() throws SQLException, IOException, ParseException {
        /**
         * 班主任审核
         * 将本班学生请假未审核的学号和名字map(学号，名字)添加至list中
         *
         * 大于三天的需班主任审核过后，交由院领导审核；
         *
         * */
        ResultSet rs = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        HttpURLConnection conn = (HttpURLConnection)new URL("http://www.baidu.com").openConnection();
        conn.setUseCaches(false);
        conn.setInstanceFollowRedirects(false);
        String dateStr = conn.getHeaderField("Date");
        String now = null;
        if (dateStr != null){
            Date date = new Date(dateStr);
            now = simpleDateFormat.format(date);
        }
        String master_sql = "select * from tb_leave where stdid=any " +
                "(select stdid from tb_user where class_name='"+p.getClass_name()+"') and master_statue=0 and '"+now+"'between apply_date and end_time";
        String college_sql = "select * from tb_leave where stdid=any " +
                "(select stdid from tb_user where class_name='"+p.getClass_name()+"') and master_statue=1 and '"+now+"'between apply_date and end_time and college_statue=0";
        dao.stu_connetion();
        if (p.getNature()==4){
             rs = dao.executeQuery(college_sql);
        }else {
             rs = dao.executeQuery(master_sql);
        }
        while (rs.next()){
            LeaveTable leaveTable = new LeaveTable();
            leaveTable.setStdid(rs.getInt("stdid"));
            leaveTable.setApply_time(rs.getTimestamp("apply_date"));
            leaveTable.setStart_time(rs.getTimestamp("start_time"));
            leaveTable.setEnd_time(rs.getTimestamp("end_time"));
            leaveTable.setNum_leave(rs.getInt("num_date_leave"));
            leaveTable.setLeave_reason(rs.getString("res_leave"));
            leaveTable.setName(rs.getString("std_name"));
            list.add(leaveTable);//使用ognl表达式在jsp页面获取list对象，显示list内容。
        }

        return NONE;
    }
    public String check() throws SQLException {
        int rs = 0;
        String mater_sql = "update tb_leave master_statue=1 where stdid="+lt.getStdid();
        String college_sql = "update tb_leave college_statue=1 where stdid="+lt.getStdid();
        dao.stu_connetion();
        if (p.getNature()==4){
            rs = dao.executeUpdate(college_sql);
        }else {
            rs = dao.executeUpdate(mater_sql);
        }

        if (rs != 0 || rs != -1){
            return "success";
        }else {
            Map session = ActionContext.getContext().getSession();
            session.put("check_info","失败");
            return "error";
        }
    }
}
