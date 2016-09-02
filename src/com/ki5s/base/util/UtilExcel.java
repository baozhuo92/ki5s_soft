package com.ki5s.base.util;
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.ss.usermodel.Sheet;  
import org.apache.poi.ss.usermodel.Workbook;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  
  
/** 
 * excel工具类
 *  
 * @author ki5s
 *  
 */  
public class UtilExcel {  
      
    public static void read(String filePath) throws IOException {  
        String fileType = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());  
        InputStream stream = new FileInputStream(filePath);  
        Workbook wb = null;  
        if (fileType.equals("xls")) {  
            wb = new HSSFWorkbook(stream);  
        } else if (fileType.equals("xlsx")) {  
            wb = new XSSFWorkbook(stream);  
        } else {  
            System.out.println("您的excel格式不正确");  
        }  
        Sheet sheet1 = wb.getSheetAt(0);  
        for (Row row : sheet1) {  
            for (Cell cell : row) {  
                System.out.print(cell.getStringCellValue() + "  ");  
            }  
            System.out.println();  
        }  
    }  
  
    public static boolean write(String outPath) throws Exception {  
        String fileType = outPath.substring(outPath.lastIndexOf(".") + 1, outPath.length());  
        System.out.println(fileType);  
        // 创建工作文档对象  
        Workbook wb = null;  
        if (fileType.equals("xls")) {  
            wb = new HSSFWorkbook();  
        } else if (fileType.equals("xlsx")) {  
            wb = new XSSFWorkbook();  
        } else {  
            System.out.println("您的文档格式不正确！");  
            return false;  
        }  
        // 创建sheet对象  
        Sheet sheet1 = (Sheet) wb.createSheet("sheet1");  
        // 循环写入行
        for (int i = 0; i < 5; i++) {  
            Row row = (Row) sheet1.createRow(i);  
            // 循环写入列 
            for (int j = 0; j < 8; j++) {  
                Cell cell = row.createCell(j);  
                cell.setCellValue("测试" + j);  
            }  
        }  
        // 创建文件流 
        OutputStream stream = new FileOutputStream(outPath);  
        // 写入数据  
        wb.write(stream);  
        // 关闭文件流
        stream.close();  
        return true;  
    }  
  
  
    public static void main(String[] args) {  
        try {  
            write("D:\\test\\out.xlsx");  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        try {  
            read("D:\\test\\out.xlsx");  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
}  