package net.renfei.sdk.test.utils;

import net.renfei.sdk.test.Tests;
import net.renfei.sdk.utils.ExcelUtils;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author RenFei
 */
public class ExcelUtilTest extends Tests {
    @Test
    public void convertExcelTest() throws IOException {
        List<Map<String, String>> datas = new ArrayList<>();
        FileOutputStream fileOutputStream2003 = new FileOutputStream("/tmp/convertExcel2003Test.xls");
        FileOutputStream fileOutputStream2007 = new FileOutputStream("/tmp/convertExcel2007Test.xlsx");
        for (int i = 0; i < 100; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("id", i + "");
            map.put("uuid", UUID.randomUUID().toString());
            map.put("文字测试", "这是convertExcel2003Test的测试");
            datas.add(map);
        }
        ExcelUtils.convertExcel2003(datas, fileOutputStream2003);
        ExcelUtils.convertExcel2007(datas, fileOutputStream2007);
        System.out.println(ExcelUtils.readExcel2003(new FileInputStream("/tmp/convertExcel2003Test.xls")));
        System.out.println(ExcelUtils.readExcel2007(new FileInputStream("/tmp/convertExcel2007Test.xlsx")));
    }

    @Test
    public void convertExcelByListTest() throws IOException {
        List<List<String>> datas = new ArrayList<>();
        FileOutputStream fileOutputStream2003 = new FileOutputStream("/tmp/convertExcel2003Test.xls");
        FileOutputStream fileOutputStream2007 = new FileOutputStream("/tmp/convertExcel2007Test.xlsx");
        List<String> first = new ArrayList<>();
        first.add("id");
        first.add("uuid");
        first.add("文字测试");
        datas.add(first);
        for (int i = 0; i < 100; i++) {
            List<String> data = new ArrayList<>();
            data.add(i + "");
            data.add(UUID.randomUUID().toString());
            data.add("这是convertExcel2003Test的测试");
            datas.add(data);
        }
        ExcelUtils.convertExcel2003ByList(datas, fileOutputStream2003);
        ExcelUtils.convertExcel2007ByList(datas, fileOutputStream2007);
        System.out.println(ExcelUtils.readExcel2003ByList(new FileInputStream("/tmp/convertExcel2003Test.xls")));
        System.out.println(ExcelUtils.readExcel2007ByList(new FileInputStream("/tmp/convertExcel2007Test.xlsx")));
    }
}
