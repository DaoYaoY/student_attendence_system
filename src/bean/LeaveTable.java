package bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LeaveTable {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Integer id;
    private int stdid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String leave_reason;
    private String start_time;
    private String end_time;
    private int num_leave;
    private String apply_time;
    private int master_statue;
    private int college_statue;

    public LeaveTable() {
        this.master_statue = 0;
        this.college_statue = 0;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getStdid() {
        return stdid;
    }

    public void setStdid(int stdid) {
        this.stdid = stdid;
    }

    public String getLeave_reason() {
        return leave_reason;
    }

    public void setLeave_reason(String leave_reason) {
        this.leave_reason = leave_reason;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = simpleDateFormat.format(start_time);
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = simpleDateFormat.format(end_time);
    }

    public int getNum_leave() {
        return num_leave;
    }

    public void setNum_leave(int num_leave) {
        this.num_leave = num_leave;
    }

    public String getApply_time() {
        return apply_time;
    }

    public void setApply_time(Date apply_time) {
        this.apply_time = simpleDateFormat.format(apply_time);
    }

    public int getMaster_statue() {
        return master_statue;
    }

    public void setMaster_statue(int master_statue) {
        this.master_statue = master_statue;
    }

    public int getCollege_statue() {
        return college_statue;
    }

    public void setCollege_statue(int college_statue) {
        this.college_statue = college_statue;
    }
}
