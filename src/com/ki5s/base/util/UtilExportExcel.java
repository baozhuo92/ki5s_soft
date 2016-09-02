package com.ki5s.base.util;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.List;

/**
 * 工具类：判断是否为Excel文件，并检查Excel版本
 *
 * @author javaloveiphone
 */
public class UtilExportExcel {
    /**
     * 依据后缀名判断读取的是否为Excel文件
     *
     * @param filePath
     * @return
     */
    public static boolean isExcel(String filePath) {
        if (filePath.matches("^.+\\.(?i)(xls)$") || filePath.matches("^.+\\.(?i)(xlsx)$")) {
            return true;
        }
        return false;
    }

    /**
     * 检查文件是否存在
     */
    public static boolean fileExist(String filePath) {
        if (filePath == null || filePath.trim().equals("")) return false;
        File file = new File(filePath);
        if (file == null || !file.exists()) {
            return false;
        }
        return true;
    }

    /**
     * 依据内容判断是否为excel2003及以下
     */
    public static boolean isExcel2003(String filePath) {
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
            if (POIFSFileSystem.hasPOIFSHeader(bis)) {
                System.out.println("Excel版本为excel2003及以下");
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * 依据内容判断是否为excel2007及以上
     */
    public static boolean isExcel2007(String filePath) {
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
            if (POIXMLDocument.hasOOXMLHeader(bis)) {
                System.out.println("Excel版本为excel2007及以上");
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    // 标题字体
    private HSSFFont titleFont03 = null;

    // 标题样式
    private HSSFCellStyle titleStyle03 = null;

    // 行信息内容样式
    private HSSFCellStyle contentStyle03 = null;

    /**
     * 写excel文件
     *
     * @throws IOException
     */
    public void writeExcel03(String[] titleStrs, List<String[]> contentList, String filename, String path) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(path + filename + ".xls");

        HSSFWorkbook wb = new HSSFWorkbook();// 创建新HSSFWorkbook对象

        setExcelStyle03(wb);//执行样式初始化

        HSSFSheet sheet = wb.createSheet(filename);// 创建新的sheet对象

        HSSFRow titleRow = sheet.createRow((short) 0);//创建第一行

        titleRow.setHeightInPoints(20);//20像素
        int titleCount = titleStrs.length;// 列数
        // 写标题
        for (int k = 0; k < titleCount; k++) {
            HSSFCell cell = titleRow.createCell((short) k); // 新建一个单元格

            // cell.setEncoding(HSSFCell.ENCODING_UTF_16); // 中文字符集转换
            cell.setCellStyle(titleStyle03);//设置标题样式
            // cell.setCellValue(new HSSFRichTextString(titleStrs[k])); // 为单元格赋值
            // cell.setCellValue(wb.getCreationHelper().createRichTextString(""));
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(titleStrs[k]);
            sheet.setColumnWidth((short) k, (short) 5000);//设置列宽
        }

        int contentCount = contentList.size();//总的记录数
        // 写内容
        for (int i = 0; i < contentCount; i++) {
            String[] contents = contentList.get(i);
            HSSFRow row = sheet.createRow((short) (i + 1)); // 新建一行

            for (int j = 0; j < titleCount; j++) {
                HSSFCell cell = row.createCell((short) j); // 新建一个单元格

                cell.setCellStyle(contentStyle03);//设置内容样式
                if (contents[j] == null || contents[j].equals("null")) {
                    contents[j] = "";
                }
                //格式化日期
                if (j == 2) {
                    HSSFCellStyle style = wb.createCellStyle();
                    style.setDataFormat(wb.getCreationHelper().createDataFormat().getFormat("yyyy-mm-dd hh:mm:ss"));
                    // cell.setCellValue(new Date());
                    // cell.setCellValue(Calendar.getInstance());
                    cell.setCellValue(contents[j]);
                    cell.setCellStyle(style);
                } else {
                    cell.setCellValue(new HSSFRichTextString(contents[j]));
                }
            }
        }
        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }

    /**
     * 样式初始化
     */
    public void setExcelStyle03(HSSFWorkbook workBook) {
        // 设置列标题字体，样式
        titleFont03 = workBook.createFont();
        titleFont03.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 标题列样式
        titleStyle03 = workBook.createCellStyle();
        titleStyle03.setBorderTop(HSSFCellStyle.BORDER_THIN); // 设置边框
        titleStyle03.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        titleStyle03.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        titleStyle03.setBorderRight(HSSFCellStyle.BORDER_THIN);
        titleStyle03.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        titleStyle03.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleStyle03.setFont(titleFont03);
        // 内容列样式
        contentStyle03 = workBook.createCellStyle();
        contentStyle03.setBorderTop(HSSFCellStyle.BORDER_THIN);
        contentStyle03.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        contentStyle03.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        contentStyle03.setBorderRight(HSSFCellStyle.BORDER_THIN);
        contentStyle03.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        contentStyle03.setAlignment(HSSFCellStyle.ALIGN_LEFT);
    }

    // 标题字体
    private XSSFFont titleFont07 = null; //2007格式
    // 标题样式
    private XSSFCellStyle titleStyle07 = null;//2007格式
    // 行信息内容样式
    private XSSFCellStyle contentStyle07 = null;//2007格式

    /**
     * 写excel文件
     *
     * @throws IOException
     */
    public void writeExcel07(String[] titleStrs, List<String[]> contentList, String filename, String path) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(path + filename + ".xlsx");
        XSSFWorkbook wb = new XSSFWorkbook();//2007格式
        setExcelStyle07(wb);//执行样式初始化
        XSSFSheet sheet = wb.createSheet(filename);//2007格式
        XSSFRow titleRow = sheet.createRow((short) 0);//2007格式
        titleRow.setHeightInPoints(20);//20像素
        int titleCount = titleStrs.length;// 列数
        // 写标题
        for (int k = 0; k < titleCount; k++) {
            XSSFCell cell = titleRow.createCell((short) k); //2007格式
            cell.setCellStyle(titleStyle07);//设置标题样式
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(titleStrs[k]);
            sheet.setColumnWidth((short) k, (short) 5000);//设置列宽
        }
        int contentCount = contentList.size();//总的记录数
        // 写内容
        for (int i = 0; i < contentCount; i++) {
            String[] contents = contentList.get(i);
            XSSFRow row = sheet.createRow((short) (i + 1)); // //2007格式
            for (int j = 0; j < titleCount; j++) {
                XSSFCell cell = row.createCell((short) j); // //2007格式
                cell.setCellStyle(contentStyle07);//设置内容样式
                if (contents[j] == null || contents[j].equals("null")) {
                    contents[j] = "";
                }
                //格式化日期
                if (j == 2) {
                    XSSFCellStyle style = wb.createCellStyle();//2007格式
                    style.setDataFormat(wb.getCreationHelper().createDataFormat().getFormat("yyyy-mm-dd hh:mm:ss"));
                    cell.setCellValue(contents[j]);
                    cell.setCellStyle(style);
                } else {
                    cell.setCellValue(new XSSFRichTextString(contents[j]));
                }
            }
        }
        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }

    /**
     * 样式初始化
     */
    public void setExcelStyle07(XSSFWorkbook wb) {
        // 设置列标题字体，样式
        titleFont07 = wb.createFont();
        titleFont07.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        // 标题列样式
        titleStyle07 = wb.createCellStyle();
        titleStyle07.setBorderTop(XSSFCellStyle.BORDER_THIN); // 设置边框
        titleStyle07.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        titleStyle07.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        titleStyle07.setBorderRight(XSSFCellStyle.BORDER_THIN);
        titleStyle07.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        titleStyle07.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        titleStyle07.setFont(titleFont07);
        // 内容列样式
        contentStyle07 = wb.createCellStyle();
        contentStyle07.setBorderTop(XSSFCellStyle.BORDER_THIN);
        contentStyle07.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        contentStyle07.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        contentStyle07.setBorderRight(XSSFCellStyle.BORDER_THIN);
        contentStyle07.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        contentStyle07.setAlignment(XSSFCellStyle.ALIGN_LEFT);
    }

}