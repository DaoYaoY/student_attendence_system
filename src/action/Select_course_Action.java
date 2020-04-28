package action;

import bean.Course;
import bean.Page;
import bean.People;
import bean.Tb_attendance;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import dao.Dao;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Select_course_Action extends ActionSupport implements ModelDriven {
    private int std_id;
    private int course_id;
    private Dao dao = new Dao();
    private Page page = new Page();


    public String get_current_time() throws IOException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        HttpURLConnection conn = (HttpURLConnection)new URL("http://www.baidu.com").openConnection();
        conn.setUseCaches(false);
        conn.setInstanceFollowRedirects(false);
        String dateStr = conn.getHeaderField("Date");
        String now = null;
        if (dateStr != null){
            Date date = new Date(dateStr);
            now = simpleDateFormat.format(date);
        }
        return now;
    }


    public int getStd_id() {
        return std_id;
    }

    public void setStd_id(int std_id) {
        this.std_id = std_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String select(){
        System.out.println("stdid="+getStd_id()+"course_id="+getCourse_id());
        String sql = "insert into select_course (stdid,course_id) values("+getStd_id()+","+getCourse_id()+")";
        dao.stu_connetion();
        int rs = dao.executeUpdate(sql);
        if (rs !=0 && rs != -1){
            Map session = ActionContext.getContext().getSession();
            session.put("select","成功");
            return "success";
        }else {
            Map session = ActionContext.getContext().getSession();
            session.put("select","失败");
            return "error";
        }

    }




    public String Show_select_course(){
        String sql = "select into select_course values("+getStd_id()+getCourse_id()+")";
        dao.stu_connetion();
        int rs = dao.executeUpdate(sql);
        if (rs !=0 && rs != -1){
            Map session = ActionContext.getContext().getSession();
            session.put("select","成功");
            return "success";
        }else {
            Map session = ActionContext.getContext().getSession();
            session.put("select","成功");
            return "error";
        }
    }

    public String show() throws Exception {
        page.setPage_name("course");
        Course course = null;
        Map session = ActionContext.getContext().getSession();
//        People user =  (People)session.get("user");
        String sql = "select count(course_id) from tb_course where "+get_current_time()+"<=sch_date";
        int allNum = dao.get_num(sql);
        int pageNum = page.getPageNum();
        int totalPage = (int) Math.ceil((float)allNum/pageNum);//总页数
        page.setAllNum(allNum);
        page.setTotalPage(totalPage);
        if (page.getNowPage()<1){
            page.setNowPage(1);
        }else if (page.getNowPage()>totalPage){
            page.setNowPage(totalPage);
        }
        String sql2 = "select course_id,course_name,time_course,datatime_course,name,college_name " +
                "from tb_course join tb_user where tb_course.teacher_id=tb_user.stdid and "+get_current_time()+"<=sch_date";
        page.setList(dao.getCourse_list(course,sql2));
        if (page.getList().size()==0){
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
            request.setAttribute("info","什么也没有");
            dao.close();
            return "error";
        }
        Collections.reverse(page.getList());
//        course_list = new ArrayList<Course>();
//        String sql = "select course_id,course_name,time_course,datatime_course,name,college_name from tb_course join tb_user where tb_course.teacher_id=tb_user.stdid";
//        dao.stu_connetion();
//        ResultSet rs = dao.executeQuery(sql);
//        while (rs.next()){
//            Course course = new Course();
//            course.setCourse_id(rs.getInt("course_id"));
//            course.setCourse_name(rs.getString("course_name"));
//            course.setTeacher_id(rs.getInt("teacher_id"));
//            course.setTime_course(rs.getString("time_course"));
//            course.setDatetime_course(rs.getString("datatime_course"));
//            course.setTeacher_name(rs.getString("name"));
//            course.setCollege_name(rs.getString("college_name"));
//            course_list.add(course);
//        }
        return "success";
    }


    @Override
    public Page getModel() {
        return page;
    }
}
