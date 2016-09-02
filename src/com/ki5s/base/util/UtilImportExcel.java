package com.ki5s.base.util;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 兼容03和07版的导入导出excel
 * */
public class UtilImportExcel {
	
	 public static void main(String[] args) {
		 	//做个小小的测试
		 	UtilExportExcel we = new UtilExportExcel();
		    String [] titleStrs = {"部门","城市","日期","金额"};
		    List<String[]> contentList = new ArrayList<String[]>();
		    String [] contents1 = {"财务部","北京","2013-07-25","1000.25"};
		    String [] contents2 = {"销售部","深圳","2013-08-01","0.099"};
		    String [] contents3 = {"产品部","天津","2013-11-17","18888888"};
		    String [] contents4 = {"市场部","上海","2013-12-10","5658978987.135454的"};
		    contentList.add(contents1);
		    contentList.add(contents2);
		    contentList.add(contents3);
		    contentList.add(contents4);
	
		    String filename = "WriteExcel";
		    try {
		    	we.writeExcel03(titleStrs, contentList, filename,"C:\\Users\\Administrator\\Workspaces\\MyEclipse Professional 2014\\.metadata\\.me_tcat\\upload");
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }
		}
	    /** 错误信息 */  
	    private String errorInfo;
	    
	    /**
	     * 验证EXCEL文件是否合法 
	     */
	    public boolean validateExcel(String filePath){ 
	    
	    /**判断文件名是否为空或者文件是否存在 */
	    if(!UtilExportExcel.fileExist(filePath)){
	    errorInfo = "文件不存在";
	    return false; 
	    }
	    
	        /**检查文件是否是Excel格式的文件 */  
	        if (!UtilExportExcel.isExcel(filePath))  {
	            errorInfo = "文件名不是excel格式";  
	            return false;  
	        } 
	        return true;  
	    }
	    
	    /** 
	     * @描述：根据文件名读取excel文件 
	     */  
	    public List<List<String>> read(String filePath){
	        List<List<String>> dataLst = new ArrayList<List<String>>();  
	        InputStream is = null;  
	        try{
	            /** 验证文件是否合法 */  
	            if (!validateExcel(filePath)){ 
	                System.out.println(errorInfo);
	                return null;
	            }  
	            /** 判断文件的类型，是2003还是2007 */  
	            boolean isExcel2003 = true; 
	            if (UtilExportExcel.isExcel2007(filePath)){
	                isExcel2003 = false;  
	            }  
	            /** 调用本类提供的根据流读取的方法 */  
	            is = new FileInputStream(new File(filePath));
	            Workbook wb = null;  
	            if (isExcel2003){  
	                wb = new HSSFWorkbook(is);  
	            }else{  
	                wb = new XSSFWorkbook(is);  
	            }
	            is.close();
	        }catch (IOException e){  
	            e.printStackTrace();  
	        }catch (Exception ex){  
	            ex.printStackTrace();  
	        }finally{  
	            if (is != null){  
	                try{  
	                    is.close();  
	                }catch (IOException e){  
	                    is = null;  
	                    e.printStackTrace();  
	                }  
	            }  
	        }  
	        return dataLst;  
	    }
	    
	    /** 
	     * @描述：读取数据 
	     */  
	    private List<List<String>> read(Workbook wb){  
	        List<List<String>> dataLst = new ArrayList<List<String>>();
	        /**得到总的shell */
	        int sheetAccount = wb.getNumberOfSheets();
	        /** 得到第一个shell */
	        Sheet sheet = wb.getSheetAt(0);  
	        /** 得到Excel的行数 */  
	        int rowCount = sheet.getPhysicalNumberOfRows();
	        /** 也可以通过得到最后一行数*/
	        int lastRowNum = sheet.getLastRowNum();
	        /** 循环Excel的行 */  
	        for (int r = 0; r < rowCount; r++){  
	            Row row = sheet.getRow(r);  
	            if (row == null){  
	                continue;  
	            }  
	            List<String> rowLst = new ArrayList<String>();  
	            /** 循环Excel的列 */  
	            for (int c = 0; c < row.getPhysicalNumberOfCells(); c++){  
	                Cell cell = row.getCell(c);  
	                String cellValue = "";  
	                if (null != cell){  
	                    // 以下是判断数据的类型  
	                    switch (cell.getCellType()){ 
	                    //XSSFCell可以达到相同的效果
	                   case HSSFCell.CELL_TYPE_NUMERIC: // 数字  
	double d = cell.getNumericCellValue();
	if (HSSFDateUtil.isCellDateFormatted(cell)) {//日期类型
	// Date date = cell.getDateCellValue();
	Date date = HSSFDateUtil.getJavaDate(d);
	cellValue =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}else{//数值类型          
	cellValue = cell.getNumericCellValue()+"";
	}
	                       cellValue = cell.getDateCellValue() + "";  
	                       break;  
	                   case HSSFCell.CELL_TYPE_STRING: // 字符串  
	                       cellValue = cell.getStringCellValue();  
	                       break;  
	                   case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean  
	                       cellValue = cell.getBooleanCellValue() + "";  
	                       break;  
	                   case HSSFCell.CELL_TYPE_FORMULA: // 公式  
	                       cellValue = cell.getCellFormula() + "";  
	                       break;  
	                   case HSSFCell.CELL_TYPE_BLANK: // 空值  
	                       cellValue = "";  
	                       break;  
	                   case HSSFCell.CELL_TYPE_ERROR: // 故障  
	                       cellValue = "非法字符";  
	                       break;
	                   default:  
	                       cellValue = "未知类型";  
	                       break;  
	                    }  
	                }  
	                System.out.print(cellValue +"\t");
	                rowLst.add(cellValue);  
	            }
	            System.out.println();
	            dataLst.add(rowLst);  
	        }  
	        return dataLst;  
	    }  
	}
