package com.manage.common.util.excelUtils;

import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

// 解析多sheet页
public class ReadExcelUtil {

    // 解析多个excel的sheet页（通过sheet页的页码）
    public static Map<String, List<Map<String, Object>>> readeMultipartSheetExcelData(InputStream excelInputSteam, Integer headerNumber, int rowStart, Integer... sheetNumber) throws Exception {
        Map<String, List<Map<String, Object>>> resMap = new HashMap<String, List<Map<String, Object>>>(); // 多sheet页返回数据
        DataFormatter dataFormatter = new DataFormatter(); // 定义数据格式

        Workbook workbook = WorkbookFactory.create(excelInputSteam); // 加载当前的excel
        List<Integer> sheetNum_list = Arrays.asList(sheetNumber);

        for (Integer sheetNum : sheetNum_list) {
            Sheet sheet = workbook.getSheetAt(sheetNum);

            Row header = sheet.getRow(headerNumber); // 获取title行
            int rowEnd = sheet.getLastRowNum(); // 最后有效数据的单元格
            // 获取标题信息
            List<String> title_list = new ArrayList<String>();

            for (int i = 0; i < header.getLastCellNum(); ++i) {
                Cell cell = header.getCell(i);
                title_list.add(dataFormatter.formatCellValue(cell));
            }

            // 获取内容信息
            List<Map<String, Object>> data_list = new ArrayList<Map<String, Object>>();

            for (int i = rowStart; i <= rowEnd; ++i) {
                Row currentRow = sheet.getRow(i);
                if (Objects.isNull(currentRow)) {
                    continue;
                }
                Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
                for (int j = 0; j < currentRow.getLastCellNum(); ++j) {
                    // 将null转化为Blank
                    Cell data = currentRow.getCell(j, Row.CREATE_NULL_AS_BLANK);
                    if (Objects.isNull(data)) { // 感觉这个if有点多余
                        dataMap.put(title_list.get(j), null);
                    } else {
                        switch (data.getCellType()) { // 不同的类型分别进行存储
                            case Cell.CELL_TYPE_STRING:
                                dataMap.put(title_list.get(j), data.getRichStringCellValue().getString());
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                if (DateUtil.isCellDateFormatted(data)) {
                                    dataMap.put(title_list.get(j), data.getDateCellValue());
                                } else {
                                    dataMap.put(title_list.get(j), data.getNumericCellValue());
                                }
                                break;
                            case Cell.CELL_TYPE_FORMULA: // 公式类型
                                try {
                                    dataMap.put(title_list.get(j), data.getNumericCellValue());
                                } catch (IllegalStateException e) {
                                    dataMap.put(title_list.get(j), data.getRichStringCellValue().getString());
                                }
                                break;
                            case Cell.CELL_TYPE_BOOLEAN:
                                dataMap.put(title_list.get(j), data.getBooleanCellValue());
                                break;
                            default:
                                dataMap.put(title_list.get(j), null);
                        }
                    }

                }
                data_list.add(dataMap);
            }

            resMap.put(sheet.getSheetName(), data_list);
        }

        return resMap;
    }
    // 解析多个excel的sheet页(通过sheetName读取多sheet页)
    public static Map<String, List<Map<String, Object>>> readeMultipartSheetExcelData(
            InputStream excelInputSteam,
            Integer headerNumber,
            int rowStart,
            List<String> sheetName_list
    ) throws Exception {
        Map<String, List<Map<String, Object>>> resMap = new HashMap<String, List<Map<String, Object>>>(); // 多sheet页返回数据
        DataFormatter dataFormatter = new DataFormatter(); // 定义数据格式

        Workbook workbook = WorkbookFactory.create(excelInputSteam); // 加载当前的excel

        for (String sheetName : sheetName_list) {
            Sheet sheet = workbook.getSheet(sheetName);
            System.out.println("==========" + sheetName);

            if (sheet == null) {
                throw new Exception("此 " + sheetName + " 页未找到");
            }

            Row header = sheet.getRow(headerNumber); // 获取title行
            int rowEnd = sheet.getLastRowNum(); // 最后有效数据的单元格
            // 获取标题信息
            List<String> title_list = new ArrayList<String>();

            for (int i = 0; i < header.getLastCellNum(); ++i) {
                Cell cell = header.getCell(i);
                title_list.add(dataFormatter.formatCellValue(cell));
            }

            // 获取内容信息
            List<Map<String, Object>> data_list = new ArrayList<Map<String, Object>>();

            for (int i = rowStart; i <= rowEnd; ++i) {
                Row currentRow = sheet.getRow(i);
                if (Objects.isNull(currentRow)) {
                    continue;
                }
                Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
                for (int j = 0; j < currentRow.getLastCellNum(); ++j) {
                    // 将null转化为Blank
                    Cell data = currentRow.getCell(j, Row.CREATE_NULL_AS_BLANK);
                    if (Objects.isNull(data)) { // 感觉这个if有点多余
                        dataMap.put(title_list.get(j), null);
                    } else {
                        switch (data.getCellType()) { // 不同的类型分别进行存储
                            case Cell.CELL_TYPE_STRING:
                                dataMap.put(title_list.get(j), data.getRichStringCellValue().getString());
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                if (DateUtil.isCellDateFormatted(data)) {
                                    dataMap.put(title_list.get(j), data.getDateCellValue());
                                } else {
                                    dataMap.put(title_list.get(j), data.getNumericCellValue());
                                }
                                break;
                            case Cell.CELL_TYPE_FORMULA: // 公式类型
                                try {
                                    dataMap.put(title_list.get(j), data.getNumericCellValue());
                                } catch (IllegalStateException e) {
                                    dataMap.put(title_list.get(j), data.getRichStringCellValue().getString());
                                }
                                break;
                            case Cell.CELL_TYPE_BOOLEAN:
                                dataMap.put(title_list.get(j), data.getBooleanCellValue());
                                break;
                            default:
                                dataMap.put(title_list.get(j), null);
                        }
                    }
                }
                data_list.add(dataMap);
            }

            resMap.put(sheet.getSheetName(), data_list);
        }

        return resMap;
    }


    // =================================================================================================================
    // 处理字符串
    public static String customExcelString(Object value) {
        if(value == null){
            return "";
        }
        return (String)value;
    }

    // 处理数字类型的日期+公募内部基金代码
    public static String customExcelNumberByDate(Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof String) {
            return (String) value;
        }
        if (value instanceof Double) {
            Double d_value = (Double) value;
            String str_value = d_value.toString();

            if (str_value.indexOf(".") > 0) {
                return str_value = str_value.substring(0, str_value.indexOf("."));
            } else {
                return str_value;
            }
        }
        return "";
    }

    // 处理double类型
    public static BigDecimal customExcelDouble(Object value){
        if(value == null){
            return null;
        }
        if (value instanceof Double) {
            Double dou = (Double)value;
            return new BigDecimal(dou);
        } else if (value instanceof String && !"".equals((String) value)) {
            Double dou = Double.valueOf((String) value);
            return new BigDecimal(dou);
        }
        return null;
    }
    // =================================================================================================================
    public static void customCellDataType(String key, Map<String, Object> dataMap, Cell data) {
        if (data != null) {
            switch (data.getCellType()) { // 不同的类型分别进行存储
                case Cell.CELL_TYPE_STRING:
                    dataMap.put(key, data.getRichStringCellValue().getString());
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(data)) {
                        dataMap.put(key, data.getDateCellValue());
                    } else {
                        dataMap.put(key, data.getNumericCellValue());
                    }
                    break;
                case Cell.CELL_TYPE_FORMULA: // 公式类型
                    try {
                        if (DateUtil.isCellDateFormatted(data)) {
                            dataMap.put(key, data.getDateCellValue());
                        } else {
                            dataMap.put(key, data.getNumericCellValue());
                        }
                    } catch (IllegalStateException e) {
                        dataMap.put(key, data.getRichStringCellValue().getString());
                    }
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    dataMap.put(key, data.getBooleanCellValue());
                    break;
                default:
                    dataMap.put(key, null);
            }
        }
    }
}
