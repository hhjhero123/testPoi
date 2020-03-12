package cn.web;


import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * POI创建Excel
 */
public class creExcelController {

    public static void main(String[] args) throws Exception {
        String []title = {"订单ID","流水号","other"};
        //创建HSSF工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个Sheet页
        HSSFSheet sheet = workbook.createSheet();
        //创建第一行（一般是表头）
        HSSFRow row0 = sheet.createRow(0);
        //创建列
        HSSFCell cell = null;
        //设置表头
        for (int i = 0; i <title.length ; i++) {
            cell=row0.createCell(i);
            cell.setCellValue(title[i]);
        }
        //填充20行数据
        for (int i = 1; i < 20; i++) {
            HSSFRow row =sheet.createRow(i);
            HSSFCell cell1 = row.createCell(0);
            cell1.setCellValue(RandomStringUtils.randomNumeric(18));
            HSSFCell cell2 = row.createCell(1);
            cell2.setCellValue(RandomStringUtils.randomNumeric(12));
        }
        //保存到本地
        File file = new File("./date.xls");
        FileOutputStream outputStream = new FileOutputStream(file);
        //将Excel写入输出流中
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

/*
    public static String readWord(String name)
    {
        FileInputStream in;
        String text = null;
        try
        {
            in = new FileInputStream(name);
            WordExtractor extractor = new WordExtractor(in);
            text = extractor.getText();
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return text;
    }
*/


}
