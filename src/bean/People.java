package bean;

public class People {
    private int stdid;//ѧ�Ż�ְ����
    private int phone;//�绰����
    private int nature;//��Ա���ԣ�0����Ա��1Ϊѧ����2Ϊ��ʦ��3ΪԺϵ�쵼 ��4ΪУ�쵼
    private String password;//����
    private String sex;
    private String name;
    private String class_name;//ֻ�а����κ�ѧ��������д
    private String college_name;//������Ա������д����������Ա��

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
