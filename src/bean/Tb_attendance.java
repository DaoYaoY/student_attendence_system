package bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tb_attendance {
    private int id;
    private  int stdid;
    private int attendance;//0��ʾ��٣�1��ʾ���Σ�2��ʾ�ٵ�
    private String att_time;
    private SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");

    public String getAttendance(int attendace){
        switch (attendace){
            case 0:
                return "���";
            case 1:
                return "����";
            case 2:
                return "�ٵ�";
            default:
                return "����";
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStdid() {
        return stdid;
    }

    public void setStdid(int stdid) {
        this.stdid = stdid;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public String getAtt_time() {
        return att_time;
    }

    public void setAtt_time(Date att_time) {
        this.att_time = sim.format(att_time);
    }
}
