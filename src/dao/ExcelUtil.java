package dao;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import static org.apache.poi.ss.usermodel.CellType.*;


public class ExcelUtil {
    private static final String Excel_xls = "xls";
    private static final String Excel_xlsx = "xlsx";

    //获取wb对象
    public static Workbook getworkbook (InputStream is, File file) throws IOException{
        Workbook wb = null;
        if (file.getName().endsWith(Excel_xls)){
            wb = new HSSFWorkbook(is);
        }
        else if (file.getName().endsWith(Excel_xlsx)){
            wb = new XSSFWorkbook(is);
        }
        return wb;
    }

    //指定sheet对象存入到list对象
    public static List<List<String>> exportList(File file) throws IOException{
        SimpleDateFormat sdm = new SimpleDateFormat("yyyy-mm-dd");
        List<List<String>> rowvalue = new ArrayList<List<String>>();
        List<String> cellvalue = new ArrayList<String>();
        try {
            InputStream is = new FileInputStream(file);
            Workbook wb = getworkbook(is,file);
            int sheetCount = wb.getNumberOfSheets();
            Sheet sheet = wb.getSheetAt(0);//wb.getSheet(String s)根据sheet名称获取sheet

            int count = 0;
            for (Row row:sheet) {
                if (count==0){
                    continue;
                }
                if (row.getCell(0).toString().equals("")){//默认单元格第一项必须有内容，否则结束循环
                    System.out.println("结束循环");
                    break;
                }
                for (Cell cell:row){
                    if (cell == null){
                        continue;
                    }
                    CellType celltype = cell.getCellType();
                    switch (celltype){
                        case STRING://文本格式
                            cellvalue.add(cell.getRichStringCellValue().getString());
                            break;
                        case NUMERIC:
//                            cell.setCellType(STRING);
                            cellvalue.add(cell.getRichStringCellValue().getString());
                            break;
                        case BOOLEAN:
                            cellvalue.add(cell.getRichStringCellValue().getString());
                            break;
                        case BLANK://空白
                            cellvalue.add(cell.getRichStringCellValue().getString());
                        case ERROR:
                            cellvalue.add(cell.getRichStringCellValue().getString());
                            break;
                        default:
                            System.out.println("没有对应类型");
                    }

                }
                rowvalue.add(cellvalue);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return rowvalue;
    }


    public static List<List<String>> readXlsx(String path){
        List<List<String>> result=new ArrayList<List<String>>();
        try {
            InputStream input=new FileInputStream(path);
            XSSFWorkbook workbook=new XSSFWorkbook(input);

            for(Sheet xssfSheet:workbook){
                if(xssfSheet==null){
                    continue;
                }
                for(int rowNum=1;rowNum<=xssfSheet.getLastRowNum();rowNum++){
                    XSSFRow row= (XSSFRow) xssfSheet.getRow(rowNum);
                    int minCellNum=row.getFirstCellNum();
                    int maxCellNum=row.getLastCellNum();
                    List<String>rowList=new ArrayList<String>();
                    for(int i=minCellNum;i<maxCellNum;i++){
                        XSSFCell cell=row.getCell(i);
                        if(cell==null){
                            System.out.println("kongde");
                            continue;
                        }
                        System.out.println(cell.toString());
                        rowList.add(cell.toString());
                    }
                    result.add(rowList);
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (List<String> i : result ){
            System.out.println(i);
        }
        return result;

    }

    public static List<List<String>> readXls(String path){
        List<List<String>> result=new ArrayList<List<String>>();
        try {
            InputStream input=new FileInputStream(path);
            HSSFWorkbook workbook=new HSSFWorkbook(input);
            for(int numSheet=0;numSheet<workbook.getNumberOfSheets();numSheet++){
                HSSFSheet sheet=workbook.getSheetAt(numSheet);
                if(sheet==null){
                    continue;
                }
                for(int rowNum=1;rowNum<=sheet.getLastRowNum();rowNum++){
                    HSSFRow row=sheet.getRow(rowNum);
                    int minCellNum=row.getFirstCellNum();
                    int maxCellNum=row.getLastCellNum();
                    List<String> rowList=new ArrayList<String>();
                    for(int i=minCellNum;i<maxCellNum;i++){
                        HSSFCell cell=row.getCell(i);
                        if(cell==null){
                            continue;
                        }
                        rowList.add(getStringVal(cell));
                    }
                    result.add(rowList);
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    private static String getStringVal(HSSFCell cell) {
        switch (cell.getCellType()) {

            case BOOLEAN:
                return cell.getBooleanCellValue() ? "TRUE" : "FALSE";
            case FORMULA:
                return cell.getCellFormula();
            case NUMERIC:
                cell.setCellType(STRING);
                return cell.getStringCellValue();
            case STRING:
                return cell.getStringCellValue();
            default:
                return null;
        }
    }


}