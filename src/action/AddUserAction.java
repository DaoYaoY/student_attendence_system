package action;

import bean.People;
import com.opensymphony.xwork2.ActionSupport;
import dao.Dao;
import dao.ExcelUtil;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;


import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddUserAction extends ActionSupport {
    private Dao dao= new Dao();
    private File excel;
    private static String filename = "excel.xlsx";

    public void setExcel(File excel) {
        this.excel = excel;
    }

    public File getExcel() {
        return excel;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public String addUserByExcel() throws IOException{
        List<List<String>> list=new ArrayList<List<String>>();
        String dire = "/upload";
        String despath = ServletActionContext.getServletContext().getRealPath(dire);
        File desExcel = new File(despath,filename);
        System.out.println(desExcel.getCanonicalPath());

//        if (desExcel.exists()){
//            desExcel.delete();
//        }
        try {
            FileUtils.copyFile(excel,desExcel);
        }catch (Exception e){
            e.printStackTrace();
        }
        list = ExcelUtil.readXlsx(desExcel.getCanonicalPath());
        dao.stu_connetion();
        for (List<String> row : list) {
            String sql = "insert into tb_user values('"+row.get(0)+"','"+row.get(1)+"','"
                    +row.get(2)+"','"+row.get(3)+"','"+row.get(4)+"','"+row.get(5)
                    +"','"+row.get(6)+"','"+row.get(7)+"')";
            if (dao.executeUpdate(sql)==0){
                continue;
            }

        }
        return "success";


    }

}
