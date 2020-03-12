package cn.web;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class poiController {

    public void read() {
        File file = new File("../poi1.xlsx");
        InputStream inputStream = null;
        Workbook workbook = null;
        try {
            inputStream = new FileInputStream(file);
            workbook = WorkbookFactory.create(inputStream);
            inputStream.close();
            //工作表对象
            Sheet sheet = workbook.getSheetAt(0);
            //总行数
            int rowLength = sheet.getLastRowNum()+1;
            //工作表的列
            Row row = sheet.getRow(0);
            //总列数
            int colLength = row.getLastCellNum();
            //得到指定的单元格
            Cell cell = row.getCell(0);
            //得到单元格样式
            CellStyle cellStyle = cell.getCellStyle();
            System.out.println("行数：" + rowLength + ",列数：" + colLength);
            for (int i = 0; i < rowLength; i++) {
                row = sheet.getRow(i);
                for (int j = 0; j < colLength; j++) {
                    cell = row.getCell(j);
                    //Excel数据Cell有不同的类型，当我们试图从一个数字类型的Cell读取出一个字符串时就有可能报异常：
                    //Cannot get a STRING value from a NUMERIC cell
                    //将所有的需要读的Cell表格设置为String格式
                    if (cell != null)
                        cell.setCellType(CellType.STRING);

                    //对Excel进行修改
                    if (i > 0 && j == 1)
                        cell.setCellValue("1000");
                    System.out.print(cell.getStringCellValue() + "\t");
                }
                System.out.println();
            }

            //将修改好的数据保存
            OutputStream out = new FileOutputStream(file);
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new poiController().read();
    }


    /**
     * 获取单元格内的数据,并进行格式转换
     * @param cell
     * @return
     */
    private Object getValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case NUMERIC:// 数值和日期均是此类型,需进一步判断
                if (DateUtil.isCellDateFormatted(cell)) {
                    //是日期类型
                    return cell.getDateCellValue();
                } else {
                    //是数值类型
                    return cell.getNumericCellValue();
                }
            default:
                return null;
        }
    }



    public void testrun1() {
        //1.创建Excel对象
        XSSFWorkbook wb = new XSSFWorkbook();
//2.创建Sheet对象
        Sheet sheet = wb.createSheet();
//3.创建行对象(索引从0开始)
        Row nRow = sheet.createRow(0);
//4.设置行高和列宽
        nRow.setHeightInPoints(26.25f);
        sheet.setColumnWidth(1,26*256); //(列的索引,列宽*256(理解为固定写法))
//5.创建单元格对象(索引从0开始)
        Cell nCell = nRow.createCell(0);
//6.设置单元格内容
        nCell.setCellValue("dinTalk");
//7.创建单元格样式对象
        CellStyle style = wb.createCellStyle();
//8.创建字体对象
        Font font = wb.createFont();
//9.设置字体和其大小及效果
        font.setFontName("黑体");
        font.setFontHeightInPoints((short)12);
        font.setBold(true); //加粗
//10.设置样式
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);        //横向居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);//纵向居中
        style.setBorderTop(BorderStyle.THIN);                //上细线
        style.setBorderBottom(BorderStyle.THIN);            //下细线
        style.setBorderLeft(BorderStyle.THIN);                //左细线
        style.setBorderRight(BorderStyle.THIN);                //右细线
//11.为单元格应用样式
        nCell.setCellStyle(style);
        System.out.println(123);
    }

}
