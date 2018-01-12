package com.nmg.teach.core.util;

import com.nmg.teach.core.exception.TeachException;
import com.nmg.teach.core.exception.TeachExceptionEnum;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * POI工具
 *
 * @author jianbingbing
 * @create 2018-01-09
 **/
public class POIUtil {

    /**
     * 解析输入文件为指定的类列表
     *
     * @param suffix      文件后缀名，如doc、xls、xlsx
     * @param inputStream 待解析的输入流
     * @param clazz       要解析成的结果的类
     * @return
     */
    public static <T> List<T> parseToList(String suffix, InputStream inputStream, Class<T> clazz, String[] fieldNames) {
        try {
            // 得到工作簿
            Workbook wb = null;
            try {
                if ("xlsx".equals(suffix)) {
                    wb = new XSSFWorkbook(inputStream);
                } else if ("xls".equals(suffix)) {
                    wb = new HSSFWorkbook(inputStream);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new TeachException(TeachExceptionEnum.SERVER_ERROR);
            }

            // 得到工作表，只处理第一张表
            Sheet sheet = wb.getSheetAt(0);
            if (sheet == null) {
                return null;
            }

            List<T> result = new ArrayList<>();

            // 从第二行开始获取数据（第一行为标题）
            int minRow = sheet.getFirstRowNum() + 1;
            int maxRow = sheet.getLastRowNum();
            for (int curRow = minRow; curRow <= maxRow; curRow++) {
                // 获取当前行数据
                Row row = sheet.getRow(curRow);
                if (row == null) {
                    continue;
                }

                int minColumn = row.getFirstCellNum();
                int maxColumn = row.getLastCellNum();

                if (minColumn > maxColumn || row.getCell(minColumn) == null) {
                    continue;
                }

                try {
                    // 创建一个新的实例
                    T obj = clazz.newInstance();
                    // 通过反射，设置属性值
                    Field[] fs = clazz.getDeclaredFields();
                    for (int curColumn = minColumn; curColumn < maxColumn; curColumn++) {
                        PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(clazz, fieldNames[curColumn]);
                        Method method = pd.getWriteMethod();
                        Object[] value = new Object[]{converStringToSpecType(row.getCell(curColumn).getStringCellValue(), pd.getPropertyType())};
                        method.invoke(obj, value);
                    }
                    result.add(obj);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            return result;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("输入流关闭异常");
                    e.printStackTrace();
                }
            }
        }
    }

    public static Object converStringToSpecType(String data, Class clazz) {
        if (String.class.equals(clazz)) {
            return data;
        }

        if (Integer.class.equals(clazz)) {
            return Integer.parseInt(data);
        }

        if (Long.class.equals(clazz)) {
            return Long.parseLong(data);
        }

        if (Double.class.equals(clazz)) {
            return Double.valueOf(data);
        }

        if (Boolean.class.equals(clazz)) {
            return Boolean.valueOf(data);
        }

        if (BigDecimal.class.equals(clazz)) {
            return new BigDecimal(data);
        }

        if (Date.class.equals(clazz)) {
            return DateUtil.parse(data, "yyyy-MM-dd HH:mm:ss");
        }

        return null;
    }

}
