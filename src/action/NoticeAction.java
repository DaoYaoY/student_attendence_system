package action;

import bean.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.deploy.net.HttpRequest;
import dao.Dao;
import org.apache.commons.math3.stat.inference.GTest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONUtil;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRotY;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class NoticeAction extends ActionSupport implements ModelDriven {
    private Dao dao = new Dao();
    private Page page = new Page();

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

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

    private People getUser() {
        Map session = ActionContext.getContext().getSession();
        People user =  (People)session.get("user");
        return user;
    }

    /**
     * 提交的请假表根据天数是否大于3天，提交给班主任或院领导审批；
     * 审批结束后，根据请假时间与当前学生的选课情况修改请假表
     */

    /**
     * 查询请假表开始时间与结束时间，换算为星期，根据查询选课表课程时间当两者星期数一致，将填写出勤表为请假。
     */

    /**
     * 出勤表由老师填写，根据当前时间与已选当前老师课程学
     * 生的请假跨度时间推送给jsp页面显示当前已请假学时名单。
     * 老师只填写未到迟到请假学生情况。
     **/


    public String master_coll_notice() throws Exception{
//        page = new Page();
        page.setPage_name("leaved");
        People user = null;
        dao.stu_connetion();
        Map session = ActionContext.getContext().getSession();
        People p = (People)session.get("user");
        int allNum = dao.get_num("select * from tb_leave" +
                " where stdid=any (select stdid from select_course " +
                        "where course_id=any (select course_id from tb_course where teacher_id="
                        +p.getStdid()+")) and master_statue=1)");
        int pageNum = page.getPageNum();
        int totalPage = (int) Math.ceil((float)allNum/pageNum);//总页数
        page.setAllNum(allNum);
        page.setTotalPage(totalPage);
        if (page.getNowPage()<1){
            page.setNowPage(1);
        }else if (page.getNowPage()>totalPage){
            page.setNowPage(totalPage);
        }
        String sql = "select * from tb_leave" +
                " where stdid=any (select stdid from select_course " +
                "where course_id=any (select course_id from tb_course where teacher_id="
                +p.getStdid()+")) and master_statue=1 limit "+(page.getNowPage()-1)*page.getPageNum()+","+
                page.getNowPage()*page.getPageNum();

        page.setList(dao.getUser_list(user,sql));
        return "考勤页面";
        /**
         * 考勤页面需要填写迟到、缺课学生名单；
         * 自动填写考勤时间
         */

    }

    public String stu_notice() throws Exception {
        LeaveTable leaveTable = null;
        page.setPage_name("leave");
        People user = getUser();
        String sql = "select count(stdid) from tb_leave where stdid="+user.getStdid();
        int allNum = dao.get_num(sql);
        int pageNum = page.getPageNum();
        int totalPage = (int) Math.ceil((float)allNum/pageNum);//总页数
        System.out.println(totalPage);
        page.setAllNum(allNum);
        page.setTotalPage(totalPage);
        System.out.println("之前的页面"+page.getNowPage());
        if (page.getNowPage()<1){
            page.setNowPage(1);
        }else if (page.getNowPage()>totalPage){
            page.setNowPage(totalPage);
        }
        System.out.println("当前页面"+page.getNowPage());
        String sql2 = "select * from tb_leave where stdid="+user.getStdid()+" limit "+
                (page.getNowPage()-1)*pageNum+","+page.getNowPage()*pageNum;
        System.out.println(sql2);
        page.setList(dao.getLeave_list(leaveTable,sql2));

        if (page.getList().size()==0){
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
            request.setAttribute("info","什么也没有");
            dao.close();
            return "error";
        }
        Collections.reverse(page.getList());
        return "success";
    }



    public String show_attendance() throws Exception {
        page.setPage_name("attendance");
        Tb_attendance attendance = null;
        Map session = ActionContext.getContext().getSession();
        People user =  (People)session.get("user");
        String sql = "select count(stdid) from tb_attendance where stdid="+user.getStdid();
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
        String sql2 = "select * from tb_attendance where stdid="+user.getStdid()+" limit "+
                (page.getNowPage()-1)*pageNum+","+page.getNowPage()*pageNum;
        System.out.println(sql2);
        page.setList(dao.getAttendance_list(attendance,sql2));

        if (page.getList().size()==0){
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
            request.setAttribute("info","什么也没有");
            dao.close();
            return "error";
        }
        Collections.reverse(page.getList());
        return "success";

    }

    public String show() throws Exception {
        page.setPage_name("course");
        Course course = null;
        Map session = ActionContext.getContext().getSession();
//        People user =  (People)session.get("user");
        String sql = "select count(course_id) from tb_course where "+get_current_time()+"<=sch_date and course_id not in(select course_id from select_course where select_course.sch_date>="+get_current_time()+")";
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
                "from tb_course,tb_user where tb_course.teacher_id=tb_user.stdid and "+get_current_time()+"<=sch_date and course_id not in(select course_id from select_course where select_course.sch_date>="+get_current_time()+")";
        page.setList(dao.getCourse_list(course,sql2));
        if (page.getList().size()==0){
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
            request.setAttribute("info","什么也没有");
            dao.close();
            return "error";
        }
        Collections.reverse(page.getList());
        return "success";
    }

    public String tea_show() throws Exception {
        page.setPage_name("course");
        Course course = null;
//        Map session = ActionContext.getContext().getSession();
//        People user =  (People)session.get("user");
        String sql = "select count(course_id) from tb_course where "+get_current_time()+"<=sch_date and teacher_id="+getUser().getStdid();
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
                "from tb_course,tb_user where tb_course.teacher_id=tb_user.stdid and "+get_current_time()+"<=sch_date and course_id not in(select course_id from select_course where select_course.sch_date>="+get_current_time()+")";
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

    public String show_selected_course() throws Exception {
        page.setPage_name("selected_course");
        Course course = null;
        Map session = ActionContext.getContext().getSession();
//        People user =  (People)session.get("user");
        String sql = "select count(course_id) from select_course";
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
        String sql2 = "select select_course.course_id,course_name,time_course,datatime_course,name,college_name " +
                "from tb_course,tb_user,select_course where tb_course.teacher_id=tb_user.stdid and select_course.course_id=tb_course.course_id";
        page.setList(dao.getCourse_list(course,sql2));
        if (page.getList().size()==0){
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
            request.setAttribute("info","什么也没有");
            dao.close();
            return "error";
        }
        Collections.reverse(page.getList());

        return "success";
    }

    @Override
    public Page getModel() {
        return page;
    }
}
