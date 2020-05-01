package action;

import bean.College_Class;
import com.opensymphony.xwork2.ActionSupport;
import dao.Dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Select_college_class extends ActionSupport {
    private College_Class college_class;
    private List<College_Class> list;

    public List<College_Class> getList() {
        return list;
    }

    public void setList(List<College_Class> list) {
        this.list = list;
    }

    @Override
    public String execute() throws Exception {
        list = new ArrayList<College_Class>();
        String sql = "select * from tb_college";
        String sql2 = "select * from tb_class";
        Dao dao = new Dao();
        Dao dao2 = new Dao();
        dao.stu_connetion();
        dao2.stu_connetion();
        ResultSet rs = dao.executeQuery(sql);
        ResultSet rs2 = dao2.executeQuery(sql2);
        while (rs!=null && rs.next()){
            college_class = new College_Class();
            college_class.setCollege_id(rs.getString("college_id"));
            college_class.setCollege_name(rs.getString("college_name"));
            college_class.setCollege_desc(rs.getString("college_desc"));
            while (rs2!=null && rs2.next()){
                college_class.setClass_id(rs2.getString("class_id"));
                college_class.setClass_name(rs2.getString("class_name"));
                college_class.setClass_desc(rs2.getString("class_desc"));
            }
            list.add(college_class);
        }
        dao.close();
        dao2.close();
        return "success";
    }
}
