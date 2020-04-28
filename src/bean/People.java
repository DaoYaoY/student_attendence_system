package bean;

public class People {
    private int stdid;//学号或职工号
    private int phone;//电话号码
    private int nature;//人员属性，0管理员，1为学生、2为老师，3为院系领导 、4为校领导
    private String password;//密码
    private String sex;
    private String name;
    private String class_name;//只有班主任和学生才需填写
    private String college_name;//所有人员必须填写，包括管理员；

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getCollege_name() {
        return college_name;
    }

    public void setCollege_name(String college_name) {
        this.college_name = college_name;
    }

    public People() {
        super();
//        this.setPassword("111111");
    }
    People(int stdid,String password){
        this.stdid=stdid;
        this.password=password;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }



    public void setNature(int nature) {
        this.nature = nature;
    }
    public int getNature() {
        return nature;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public int getStdid() {
        return stdid;
    }
    public void setStdid(int stdid) {
        this.stdid = stdid;
    }
    public int getPhone() {
        return phone;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }

}
