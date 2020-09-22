package net.renfei.sdk.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>Title: ExcelUtils</p>
 * <p>Description: Excel工具类</p>
 *
 * @author RenFei
 */
public class ExcelUtils {
    /**
     * 将Map<String, String>数据写入Excel2003，注意Map是无序的
     *
     * @param datas            数据
     * @param fileOutputStream 文件流
     */
    public static void convertExcel2003(List<Map<String, String>> datas, FileOutputStream fileOutputStream) {
        //创建一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个工作薄
        HSSFSheet sheet = workbook.createSheet("Sheet1");
        // ---->有得时候你想设置统一单元格的高度，就用这个方法
        sheet.setDefaultRowHeight((short) 300);
        //--->单元格内容格式
        HSSFDataFormat format = workbook.createDataFormat();
        for (int i = 0; i < datas.size(); i++) {
            //--->创建一行
            HSSFRow row = sheet.createRow(i);
            if (i == 0) {
                // 首行
                int j = 0;
                for (String key : datas.get(i).keySet()
                ) {
                    //--->创建一个单元格
                    HSSFCell cell = row.createCell((short) j);
                    cell.setCellValue(key);
                    j++;
                }
            } else {
                int j = 0;
                for (String value : datas.get(i).values()) {
                    //--->创建一个单元格
                    HSSFCell cell = row.createCell((short) j);
                    cell.setCellValue(value);
                    j++;
                }
            }
        }
        try {
            workbook.write(fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将List<String>数据写入Excel2003，注意List是有序的
     *
     * @param datas            数据
     * @param fileOutputStream 文件流
     */
    public static void convertExcel2003ByList(List<List<String>> datas, FileOutputStream fileOutputStream) {
        //创建一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个工作薄
        HSSFSheet sheet = workbook.createSheet("Sheet1");
        // ---->有得时候你想设置统一单元格的高度，就用这个方法
        sheet.setDefaultRowHeight((short) 300);
        //--->单元格内容格式
        HSSFDataFormat format = workbook.createDataFormat();
        for (int i = 0; i < datas.size(); i++) {
            List<String> data = datas.get(i);
            //--->创建一行
            HSSFRow row = sheet.createRow(i);
            int j = 0;
            for (String value : data) {
                //--->创建一个单元格
                HSSFCell cell = row.createCell((short) j);
                cell.setCellValue(value);
                j++;
            }
        }
        try {
            workbook.write(fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将Map<String, String>数据写入Excel2007，注意Map是无序的
     *
     * @param datas            数据
     * @param fileOutputStream 文件流
     */
    public static void convertExcel2007(List<Map<String, String>> datas, FileOutputStream fileOutputStream) {
        //创建一个excel文件
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建一个工作薄
        XSSFSheet sheet = workbook.createSheet("Sheet1");
        // ---->有得时候你想设置统一单元格的高度，就用这个方法
        sheet.setDefaultRowHeight((short) 300);
        //--->单元格内容格式
        XSSFDataFormat format = workbook.createDataFormat();
        for (int i = 0; i < datas.size(); i++) {
            //--->创建一行
            XSSFRow row = sheet.createRow(i);
            if (i == 0) {
                // 首行
                int j = 0;
                for (String key : datas.get(i).keySet()
                ) {
                    //--->创建一个单元格
                    XSSFCell cell = row.createCell((short) j);
                    cell.setCellValue(key);
                    j++;
                }
            } else {
                int j = 0;
                for (String value : datas.get(i).values()) {
                    //--->创建一个单元格
                    XSSFCell cell = row.createCell((short) j);
                    cell.setCellValue(value);
                    j++;
                }
            }
        }
        try {
            workbook.write(fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将List<String>数据写入Excel2007，注意List是有序的
     *
     * @param datas            数据
     * @param fileOutputStream 文件流
     */
    public static void convertExcel2007ByList(List<List<String>> datas, FileOutputStream fileOutputStream) {
        //创建一个excel文件
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建一个工作薄
        XSSFSheet sheet = workbook.createSheet("Sheet1");
        // ---->有得时候你想设置统一单元格的高度，就用这个方法
        sheet.setDefaultRowHeight((short) 300);
        //--->单元格内容格式
        XSSFDataFormat format = workbook.createDataFormat();
        for (int i = 0; i < datas.size(); i++) {
            List<String> data = datas.get(i);
            //--->创建一行
            XSSFRow row = sheet.createRow(i);
            int j = 0;
            for (String value : data) {
                //--->创建一个单元格
                XSSFCell cell = row.createCell((short) j);
                cell.setCellValue(value);
                j++;
            }
        }
        try {
            workbook.write(fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取Excel2003到List<Map<String, String>>，注意Map是无序的
     *
     * @param fileInputStream 文件流
     * @return List<Map < String, String>>
     * @throws IOException
     */
    public static List<Map<String, String>> readExcel2003(FileInputStream fileInputStream) throws IOException {
        if (BeanUtils.isEmpty(fileInputStream)) {
            throw new RuntimeException("fileInputStream is null");
        }
        HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
        HSSFSheet s = workbook.getSheetAt(0);
        List<Map<String, String>> datas = new ArrayList<>();
        List<String> key = new ArrayList<>();
        //获取总行数
        for (int j = 0; j < s.getPhysicalNumberOfRows(); j++) {
            Map<String, String> data = new HashMap<>();
            // 取出第i行 getRow(index) 获取第(j)行
            Row row = s.getRow(j);
            if (j == 0) {
                // 首行作为key
                for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {
                    //取出第j行第k列的值
                    String value = getCellFormatValue(row.getCell(k));
                    key.add(value);
                }
            } else {
                for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {
                    //取出第j行第k列的值
                    String value = getCellFormatValue(row.getCell(k));
                    data.put(key.get(k), value);
                }
                datas.add(data);
            }
        }
        workbook.close();
        return datas;
    }

    /**
     * 读取Excel2003到List<List<String>>，注意List是有序的
     *
     * @param fileInputStream 文件流
     * @return List<List < String>>
     * @throws IOException
     */
    public static List<List<String>> readExcel2003ByList(FileInputStream fileInputStream) throws IOException {
        if (BeanUtils.isEmpty(fileInputStream)) {
            throw new RuntimeException("fileInputStream is null");
        }
        HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
        HSSFSheet s = workbook.getSheetAt(0);
        List<List<String>> datas = new ArrayList<>();
        //获取总行数
        for (int j = 0; j < s.getPhysicalNumberOfRows(); j++) {
            List<String> data = new ArrayList<>();
            // 取出第i行 getRow(index) 获取第(j)行
            Row row = s.getRow(j);
            for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {
                //取出第j行第k列的值
                data.add(getCellFormatValue(row.getCell(k)));
            }
            datas.add(data);
        }
        workbook.close();
        return datas;
    }

    /**
     * 读取Excel2007到List<Map<String, String>>，注意Map是无序的
     *
     * @param fileInputStream 文件流
     * @return List<Map < String, String>>
     * @throws IOException
     */
    public static List<Map<String, String>> readExcel2007(FileInputStream fileInputStream) throws IOException {
        if (BeanUtils.isEmpty(fileInputStream)) {
            throw new RuntimeException("fileInputStream is null");
        }
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet s = workbook.getSheetAt(0);
        List<Map<String, String>> datas = new ArrayList<>();
        List<String> key = new ArrayList<>();
        //获取总行数
        for (int j = 0; j < s.getPhysicalNumberOfRows(); j++) {
            Map<String, String> data = new HashMap<>();
            // 取出第i行 getRow(index) 获取第(j)行
            Row row = s.getRow(j);
            if (j == 0) {
                // 首行作为key
                for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {
                    //取出第j行第k列的值
                    String value = getCellFormatValue(row.getCell(k));
                    key.add(value);
                }
            } else {
                for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {
                    //取出第j行第k列的值
                    String value = getCellFormatValue(row.getCell(k));
                    data.put(key.get(k), value);
                }
                datas.add(data);
            }
        }
        workbook.close();
        return datas;
    }

    /**
     * 读取Excel2007到List<List<String>>，注意List是有序的
     *
     * @param fileInputStream 文件流
     * @return List<List < String>>
     * @throws IOException
     */
    public static List<List<String>> readExcel2007ByList(FileInputStream fileInputStream) throws IOException {
        if (BeanUtils.isEmpty(fileInputStream)) {
            throw new RuntimeException("fileInputStream is null");
        }
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet s = workbook.getSheetAt(0);
        List<List<String>> datas = new ArrayList<>();
        //获取总行数
        for (int j = 0; j < s.getPhysicalNumberOfRows(); j++) {
            List<String> data = new ArrayList<>();
            // 取出第i行 getRow(index) 获取第(j)行
            Row row = s.getRow(j);
            for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {
                //取出第j行第k列的值
                data.add(getCellFormatValue(row.getCell(k)));
            }
            datas.add(data);
        }
        workbook.close();
        return datas;
    }

    public static String getCellFormatValue(Cell cell) {
        String cellValue = "";
        if (cell != null) {
            // 判断cell类型
            switch (cell.getCellType()) {
                case NUMERIC: {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                case BOOLEAN:
                    cellValue = cell.getBooleanCellValue() + "";
                    break;
                case FORMULA:
                    try {
                        // 如果公式结果为字符串
                        cellValue = String.valueOf(cell.getStringCellValue());
                    } catch (IllegalStateException e) {
                        // 判断是否为日期类型
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            Date date = cell.getDateCellValue();
                            DateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
                            cellValue = formater.format(date);
                        } else {
                            FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper()
                                    .createFormulaEvaluator();
                            evaluator.evaluateFormulaCell(cell);
                            // 有些数字过大，直接输出使用的是科学计数法： 2.67458622E8 要进行处理
                            DecimalFormat df = new DecimalFormat("####.####");
                            cellValue = df.format(cell.getNumericCellValue());
                        }
                    }
                    break;
                case ERROR:
                    cellValue = "非法字符";
                    break;
                default:
                    cellValue = "";
            }
        }
        return cellValue;
    }
}
