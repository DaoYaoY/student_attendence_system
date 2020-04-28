package bean;

public class Course {
    private Integer course_id;
    private String course_name;
    private String time_course;
    private Integer teacher_id;
    private String datetime_course;
    private String teacher_name;
    private String college_name;

    public String getCollege_name() {
        return college_name;
    }

    public void setCollege_name(String college_name) {
        this.college_name = college_name;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getTime_course() {
        return time_course;
    }

    public void setTime_course(String time_course) {
        this.time_course = time_course;
    }

    public Integer getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getDatetime_course() {
        return datetime_course;
    }

    public void setDatetime_course(String datetime_course) {
        this.datetime_course = datetime_course;
    }
}
