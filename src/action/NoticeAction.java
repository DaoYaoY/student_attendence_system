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

    /**
     * �ύ����ٱ���������Ƿ����3�죬�ύ�������λ�Ժ�쵼������
     * ���������󣬸������ʱ���뵱ǰѧ����ѡ������޸���ٱ�
     */

    /**
     * ��ѯ��ٱ�ʼʱ�������ʱ�䣬����Ϊ���ڣ����ݲ�ѯѡ�α�γ�ʱ�䵱����������һ�£�����д���ڱ�Ϊ��١�
     */

    /**
     * ���ڱ�����ʦ��д�����ݵ�ǰʱ������ѡ��ǰ��ʦ�γ�ѧ
     * ������ٿ��ʱ�����͸�jspҳ����ʾ��ǰ�����ѧʱ������
     * ��ʦֻ��дδ���ٵ����ѧ�������
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
        int totalPage = (int) Math.ceil((float)allNum/pageNum);//��ҳ��
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

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        HttpURLConnection conn = (HttpURLConnection)new URL("http://www.baidu.com").openConnection();
//        conn.setUseCaches(false);
//        conn.setInstanceFollowRedirects(false);
//        String dateStr = conn.getHeaderField("Date");
//        String now = null;
//        if (dateStr != null){
//            Date date = new Date(dateStr);
//            now = simpleDateFormat.format(date);
//        }
//
//
//
//        ResultSet res = dao.executeQuery(sql);
//        Date current = simpleDateFormat.parse(now);
//        Date time = res.getDate(1);
//        while (res.next()){
//            Date time1=new Date(res.getTimestamp("end_time").getTime());//java.util.Date
//            int rs = current.compareTo(time1);
//            if (rs != 1){//����1��ʾ��ǰʱ��������ʱ�䣬����Ҫ����
//                People stu = new People();
//                stu.setName(res.getString("std_name"));
//                stu.setStdid(res.getInt("stdid"));
//                page.getList().add(stu);//�������ѧ����ӵ�list����
//            }
//        }

        return "����ҳ��";
        /**
         * ����ҳ����Ҫ��д�ٵ���ȱ��ѧ��������
         * �Զ���д����ʱ��
         */

    }

    public String stu_notice() throws Exception {
        LeaveTable leaveTable = null;
//        page = new Page();
        page.setPage_name("leave");
        Map session = ActionContext.getContext().getSession();
        People user =  (People)session.get("user");
        String sql = "select count(stdid) from tb_leave where stdid="+user.getStdid();
        int allNum = dao.get_num(sql);
        int pageNum = page.getPageNum();
        int totalPage = (int) Math.ceil((float)allNum/pageNum);//��ҳ��
        System.out.println(totalPage);
        page.setAllNum(allNum);
        page.setTotalPage(totalPage);
        System.out.println("֮ǰ��ҳ��"+page.getNowPage());
        if (page.getNowPage()<1){
            page.setNowPage(1);
        }else if (page.getNowPage()>totalPage){
            page.setNowPage(totalPage);
        }
        System.out.println("��ǰҳ��"+page.getNowPage());
        String sql2 = "select * from tb_leave where stdid="+user.getStdid()+" limit "+
                (page.getNowPage()-1)*pageNum+","+page.getNowPage()*pageNum;
        System.out.println(sql2);
        page.setList(dao.getLeave_list(leaveTable,sql2));
//        List list = page.getList();
//        Iterator<LeaveTable> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            LeaveTable leaveTable1 = iterator.next();
//            System.out.println(leaveTable1.getApply_time());
//        }

//        ResultSet res = dao.executeQuery(sql);
//        while (res.next()){
//            System.out.println("youjieguo");
//            LeaveTable lt = new LeaveTable();
//            lt.setStdid(res.getInt("stdid"));
//            lt.setStart_time(res.getTimestamp("start_time"));
//            lt.setEnd_time(res.getTimestamp("end_time"));
//            lt.setApply_time(res.getTimestamp("apply_date"));
//            lt.setLeave_reason(res.getString("res_leave"));
//            lt.setMaster_statue(res.getInt("master_statue"));
//            lt.setCollege_statue(res.getInt("college_statue"));
//            page.getList().add(lt);
//        }
        if (page.getList().size()==0){
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
            request.setAttribute("info","ʲôҲû��");
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
        int totalPage = (int) Math.ceil((float)allNum/pageNum);//��ҳ��
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

//        dao.stu_connetion();
//        ResultSet res = dao.executeQuery(sql);
//        while (res.next()){
//            System.out.println("youjieguo");
//            attendance.setId(res.getInt("id"));
//            attendance.setStdid(res.getInt("stdid"));
//            attendance.setAttendance(res.getInt("attendancd"));
//            attendance.setAtt_time(res.getTimestamp("attendance_time"));
//            System.out.println(res.getDate("attendance_time"));
//            page.getList().add(attendance);
//        }
        if (page.getList().size()==0){
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
            request.setAttribute("info","ʲôҲû��");
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
        String sql = "select count(course_id) from tb_course where "+get_current_time()+"<=sch_date";
        int allNum = dao.get_num(sql);
        int pageNum = page.getPageNum();
        int totalPage = (int) Math.ceil((float)allNum/pageNum);//��ҳ��
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
            request.setAttribute("info","ʲôҲû��");
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
