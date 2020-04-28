package action;

import bean.Course;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import dao.Dao;

import java.util.Map;

public class Add_CourseAction extends ActionSupport implements ModelDriven {
    private Course course = new Course();
    private Dao dao = new Dao();
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String add(){
        dao.stu_connetion();
        String sql = "insert into `tb_course` values("+course.getCourse_id()+",'"
                +course.getCourse_name()+"',"+course.getTime_course()
                +","+course.getTeacher_id()+",'"+course.getDatetime_course()+"')";
        int res = dao.executeUpdate(sql);
        if (res!=0 || res != -1){
            Map session = ActionContext.getContext().getSession();
            session.put("flag","³É¹¦");
            return "success";
        }else {
            Map session = ActionContext.getContext().getSession();
            session.put("flag","Ê§°Ü");
            return "error";
        }
    }


    @Override
    public Course getModel() {
        return course;
    }
}
