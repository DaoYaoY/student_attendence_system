package dao;

import bean.Course;
import bean.LeaveTable;
import bean.People;
import bean.Tb_attendance;
import com.mysql.jdbc.Driver;
import org.apache.poi.ss.formula.functions.T;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Dao {
    private Connection conn = null;
    private Statement stat = null;
    private ResultSet rs = null;
    public Dao(){
        super();
    }
    public void stu_connetion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_attendance_system","root","111");
            stat = conn.createStatement();
            System.out.println("connection success");
        }catch (Exception e){
            System.out.println("connection error");
            e.printStackTrace();
            conn = null;
        }
    }
//    public void not_stu_connetion(){
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_attendance_system","root","111");
//            stat = conn.createStatement();
//        }catch (Exception e){
//            conn = null;
//        }
//    }
    public ResultSet executeQuery(String sql) throws SQLException {
        try {
            rs = stat.executeQuery(sql);

        }catch (Exception e){
            System.out.println("error");
            e.printStackTrace();
            rs = null;
        }
        return rs;
    }
    public boolean execute(String sql) throws SQLException {
        try{
            boolean flage = stat.execute(sql);
            return flage;
        }catch (Exception e) {
            e.printStackTrace();
            rs = null;
            return false;
        }
    }
    public int executeUpdate(String sql) {
        try {
            int num = stat.executeUpdate(sql);
            return num;

        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }

    }
    public void close(){
        try {
            if (stat!= null){stat.close();}
            if ( conn!=null){conn.close();
            }
            if (rs!=null){
            rs.close();}
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int get_num(String sql) throws Exception{
        stu_connetion();
        ResultSet rs = executeQuery(sql);
        if (rs!=null && rs.next()){
            int num = rs.getInt(1);
            close();
            return num;
        }else {
            close();
            return 0;
        }
    }

    public int leave_page_All_num() throws Exception{
        String sql = "select count(stdid) from tb_leave";
        return get_num(sql);
    }

    public int leave_page_num(int stdid) throws Exception{
        String sql = "select count(stdid) from tb_leave where stdid="+stdid;
        return get_num(sql);
    }

    public int user_page_num() throws Exception{
        String sql = "select count(stdid) from tb_user";
        return get_num(sql);
    }

    public int course_page_num() throws Exception{
        String sql = "select count(course_id) from tb_course";
        return  get_num(sql);
    }

    public List getLeave_list(LeaveTable lt, String sql) throws SQLException{
        List list = new ArrayList();
        stu_connetion();
        ResultSet rs = executeQuery(sql);
        if (rs!=null) {
            while (rs.next()) {
                lt = new LeaveTable();
                lt.setStdid(rs.getInt("stdid"));
                lt.setStart_time(rs.getTimestamp("start_time"));
                lt.setEnd_time(rs.getTimestamp("end_time"));
                lt.setApply_time(rs.getTimestamp("apply_date"));
                lt.setLeave_reason(rs.getString("res_leave"));
                lt.setMaster_statue(rs.getInt("master_statue"));
                lt.setCollege_statue(rs.getInt("college_statue"));
                list.add(lt);
//            System.out.println(lt.getApply_time());
//            System.out.println(lt.getStdid());
            }
        }
        close();
        return list;
    }

    public List getUser_list(People user, String sql) throws SQLException{
        List list = new ArrayList();
        stu_connetion();
        ResultSet rs = executeQuery(sql);
        while (rs.next()){
            user = new People();
            user.setName(rs.getString("std_name"));
            user.setStdid(rs.getInt("stdid"));
            list.add(user);
        }
        close();
        return list;
    }
    public List getCourse_list(Course course, String sql) throws SQLException{
        List list = new ArrayList();
        stu_connetion();
        ResultSet rs = executeQuery(sql);
        if (rs!=null) {
            while (rs.next()) {
                course = new Course();
                course.setCourse_id(rs.getInt("course_id"));
                course.setCourse_name(rs.getString("course_name"));
//            course.setTeacher_id(rs.getInt("teacher_id"));
                course.setTime_course(rs.getString("time_course"));
                course.setDatetime_course(rs.getString("datatime_course"));
                course.setTeacher_name(rs.getString("name"));
                course.setCollege_name(rs.getString("college_name"));
                list.add(course);
            }
        }else {
            close();
        }
        return list;
    }

    public List getAttendance_list(Tb_attendance attendance, String sql) throws SQLException{
        List list = new ArrayList();
        stu_connetion();
        ResultSet rs = executeQuery(sql);
        if (rs!=null){
        while (rs.next()){
            attendance = new Tb_attendance();
            attendance.setId(rs.getInt("id"));
            attendance.setStdid(rs.getInt("stdid"));
            attendance.setAttendance(rs.getInt("attendancd"));
            attendance.setAtt_time(rs.getTimestamp("attendance_time"));
            list.add(attendance);
        }
        }else {
        close();}
        return list;
    }

}
