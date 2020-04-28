package action;


import bean.People;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import dao.Dao;

import org.apache.struts2.ServletActionContext;


import javax.servlet.ServletContext;
import java.io.DataInput;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

public class LoginAction extends ActionSupport implements ModelDriven<People> {

    private File excel;
    private String excelName;
    private People p = new People();

    public People getP() {
        return p;
    }

    public void setP(People p) {
        this.p = p;
    }

    Dao dao = new Dao();

    public void setExcel(File excel) {
        this.excel = excel;
    }

    public File getExcel() {
        return excel;
    }

    public void setExcelName(String excelName) {
        this.excelName = excelName;
    }

    public String getExcelName() {
        return excelName;
    }

    public String login() throws SQLException {
//        System.out.println("password"+p.getPassword()+"stdid"+p.getStdid());
        String sql = "select * from tb_user where stdid='" + p.getStdid()+"' and password ='"+p.getPassword()+"'";
        System.out.println(p.getStdid()+p.getPassword());
        dao.stu_connetion();
        ResultSet rs = dao.executeQuery(sql);
        try {
            if (rs.next()) {
                int nature = rs.getInt("nature");
                String name = rs.getString("name");
                String class_name = rs.getString("class_name");
                String college_name = rs.getString("college_name");
                dao.close();
                Map session = ActionContext.getContext().getSession();
                p.setNature(nature);
                p.setClass_name(class_name);
                p.setCollege_name(college_name);
                p.setName(name);
                session.put("user",p);
                return "success";
                //System.out.println("1"+rs.getInt(1)+"2"+rs.getInt(2)+"3"+rs.getInt(3));
//                System.out.println(nature);
//                switch (nature){
//                    case 0:
//                        return "admin_index";
//                    case 1:
//                        return"stu_index";
//                    case 2:
//                        return "teacher_index";
//                    case 3:
//                        return "master_index";
//                    case 4:
//                        return "sch_leader_index";
//                    default:
//                        return "error_index";
//                }

            }else {
                    System.out.println("rs is null");
                    return "error_login";
                }



        }catch (Exception e){
            System.out.println("rs is null");
            e.printStackTrace();
            return "error_login";
        }

    }

    public String update(){
        String sql = "update tb_user phone="+p.getPhone()+"password='"+p.getPassword()+"' class_name='"+p.getClass_name()+
                "' college_name='"+p.getCollege_name()+"' where stdid="+p.getStdid();
        return NONE;
    }

    @Override
    public People getModel() {
        return p;
    }

//    @Override
//    public String execute() throws Exception {
//        return "stu_index";
//    }
}
