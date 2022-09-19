package com.manage.common.util.excelUtils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

// Excel导出工具类
@Slf4j
public class ExcelExportUtils {

    private Integer row_index = 0;

    public void setRowIndex(Integer row_index) {
        this.row_index = row_index;
    }

    // 导出excel简化（2007版本）
    public void exportCommomData2007(XSSFWorkbook workbook, int sheetNum, String sheetName,
                                     List<List<RealTimeWebExcelTitleVO>> table_data_list,
                                     Integer[] widths,
                                     Boolean isFreezen,
                                     ExcelFreezenBean freezenBean,
                                     String ref
    ) {

        this.row_index = 0;

        XSSFSheet sheet = workbook.createSheet(); // 生成一个表格
        sheet.setDefaultColumnWidth((short) 13); // 设置表格默认列宽度为12个字节
        workbook.setSheetName(sheetNum, sheetName);

        ExcelCellStyles cellStyle = new ExcelCellStyles(workbook);
        Map<String, XSSFCellStyle> cellStyles2007 = cellStyle.getCellStyles2007();

        // 处理data行
        if (table_data_list != null) {
            this.iteratorAllSumTitleDataRow2007(sheet, table_data_list, workbook, cellStyles2007);
        }

        if (widths != null) { // 列宽
            this.adjustWidth2007(widths, sheet);
        }

        if(isFreezen) { // 冻结
            if (freezenBean != null) {
                sheet.createFreezePane(freezenBean.getColSplit(), freezenBean.getRowSplit(),
                        freezenBean.getLeftmostColumn(), freezenBean.getTopRow());
            }

        }

        if (StringUtils.isNotBlank(ref)) { // 添加筛选列
            CellRangeAddress c = CellRangeAddress.valueOf(ref);
            sheet.setAutoFilter(c);
        }

    }

    // 导出excel简化（2003版本）
    public void exportCommomData2(HSSFWorkbook workbook, int sheetNum, String sheetName,
                                  List<List<RealTimeWebExcelTitleVO>> table_data_list,
                                  Integer[] widths,
                                  Boolean isFreezen,
                                  ExcelFreezenBean freezenBean,
                                  String ref
    ) {

        this.row_index = 0;

        HSSFSheet sheet = workbook.createSheet(); // 生成一个表格
        sheet.setDefaultColumnWidth((short) 13); // 设置表格默认列宽度为12个字节
        workbook.setSheetName(sheetNum, sheetName);
        sheet.setActive(true);

        ExcelCellStyles cellStyle = new ExcelCellStyles(workbook);
        Map<String, HSSFCellStyle> cellStyles2003 = cellStyle.getCellStyles2003();

        // 处理data行
        if (table_data_list != null) {
            this.iteratorAllSumTitleDataRow(sheet, table_data_list, workbook, cellStyles2003);
        }

        if (widths != null) { // 列宽
            this.adjustWidth(widths, sheet);
        }

        if(isFreezen) { // 冻结
            if (freezenBean != null) {
                sheet.createFreezePane(freezenBean.getColSplit(), freezenBean.getRowSplit(),
                        freezenBean.getLeftmostColumn(), freezenBean.getTopRow()
                );
            }

        }

        if (StringUtils.isNotBlank(ref)) { // 添加筛选列
            CellRangeAddress c = CellRangeAddress.valueOf(ref);
            sheet.setAutoFilter(c);
        }
    }

    // 导出excel简化（2003版本） 带列分组版本
    public void exportCommomData2GroupColumn(HSSFWorkbook workbook, int sheetNum, String sheetName,
                                             List<List<RealTimeWebExcelTitleVO>> table_data_list,
                                             Integer[] widths,
                                             Boolean isFreezen,
                                             ExcelFreezenBean freezenBean,
                                             Integer[] ref,
                                             List<ExcelGroupColumn> groupColumn
    ) {

        this.row_index = 0;

        HSSFSheet sheet = workbook.createSheet(); // 生成一个表格
        sheet.setDefaultColumnWidth((short) 6); // 设置表格默认列宽度为6个字节
        workbook.setSheetName(sheetNum, sheetName);
        sheet.setActive(true);
        sheet.setZoom(85, 100); // 设置页面缩放比例 2021年9月7日 15:10:34

        ExcelCellStyles cellStyle = new ExcelCellStyles(workbook);
        Map<String, HSSFCellStyle> cellStyles2003 = cellStyle.getCellStyles2003();

        // 处理data行
        if (table_data_list != null) {
            this.iteratorAllSumTitleDataRow(sheet, table_data_list, workbook, cellStyles2003);
        }

        if (widths != null) { // 列宽
            this.adjustWidth(widths, sheet);
        }

        if(isFreezen) { // 冻结
            if (freezenBean != null) {
                sheet.createFreezePane(freezenBean.getColSplit(), freezenBean.getRowSplit(),
                        freezenBean.getLeftmostColumn(), freezenBean.getTopRow()
                );
            }
        }

        if (ref.length > 0) { // 添加筛选列
            Integer integer1 = ref[0];
            Integer integer2 = ref[1];
            String firstCon = CellReference.convertNumToColString(integer1) + "2";
            String lastCon = CellReference.convertNumToColString(integer2) + "2";
            String r = firstCon + ":" + lastCon;
            CellRangeAddress c = CellRangeAddress.valueOf(r);
            sheet.setAutoFilter(c);
        }

        // 列分组
        if(!CollectionUtils.isEmpty(groupColumn)) {
            for(ExcelGroupColumn column : groupColumn) {
                sheet.groupColumn(column.getFirstColl(), column.getLastColl());
                sheet.setColumnGroupCollapsed(column.getFirstColl(), true);
            }
        }
    }

    // 导出excel通用
    public void exportCommomData(HSSFWorkbook workbook, int sheetNum, String sheetName,
                                 List<RealTimeWebExcelTitleVO> table_title_list,
                                 List<List<RealTimeWebExcelTitleVO>> table_data_list,
                                 List<RealTimeWebExcelTitleVO> table_title_list2,
                                 List<List<RealTimeWebExcelTitleVO>> table_data_list2,
                                 Integer[] widths,
                                 Boolean isFreezen,
                                 ExcelFreezenBean freezenBean
    ) {

        this.row_index = 0;

        HSSFSheet sheet = workbook.createSheet(); // 生成一个表格
        sheet.setDefaultColumnWidth((short) 13); // 设置表格默认列宽度为12个字节
        workbook.setSheetName(sheetNum, sheetName);
        sheet.setActive(true);

        ExcelCellStyles cellStyle = new ExcelCellStyles(workbook);
        Map<String, HSSFCellStyle> cellStyles2003 = cellStyle.getCellStyles2003();

        // 处理title行
        if (table_title_list != null) {
            this.iteratorAllSumTitle(sheet, table_title_list, cellStyles2003.get(CellStyleConts.TITLE));
        }

        // 处理data行
        if (table_data_list != null) {
            this.iteratorAllSumTitleDataRow(sheet, table_data_list, workbook, cellStyles2003);
        }

        // 处理title行
        if (table_title_list2 != null) {
            this.iteratorAllSumTitle(sheet, table_title_list2, cellStyles2003.get(CellStyleConts.TITLE));
        }

        // 处理data行
        if (table_data_list2 != null) {
            this.iteratorAllSumTitleDataRow(sheet, table_data_list2, workbook, cellStyles2003);
        }

        if (widths != null) { // 列宽
            this.adjustWidth(widths, sheet);
        }

        if(isFreezen) { // 冻结
            if (freezenBean != null) {
                sheet.createFreezePane(freezenBean.getColSplit(), freezenBean.getRowSplit(),
                        freezenBean.getLeftmostColumn(), freezenBean.getTopRow());
            }

        }

    }

    // 调整每列列宽
    private void adjustWidth2007(Integer[] widths, XSSFSheet sheet) {
        for (int i = 0; i < widths.length; i++) {
            sheet.setColumnWidth(i, widths[i] * 256); // 设置宽度
        }
    }

    // 调整每列列宽
    private void adjustWidth(Integer[] widths, HSSFSheet sheet) {
        for (int i = 0; i < widths.length; i++) {
            if(widths[i]!=null){
                sheet.setColumnWidth(i, widths[i] * 256); // 设置宽度
            }
        }
    }

    // 生成所有汇总行的data(2007版)
    private void iteratorAllSumTitleDataRow2007(XSSFSheet sheet, List<List<RealTimeWebExcelTitleVO>> table_data_list,
                                                XSSFWorkbook workbook, Map<String, XSSFCellStyle> cellStyles2007) {
        XSSFRow table_data_row = null;
        for (List<RealTimeWebExcelTitleVO> data_row : table_data_list) {
            table_data_row = sheet.createRow(this.row_index);
            Integer data_cell_index = 0;
            this.iteratorDataCell2007(workbook, sheet, table_data_row, data_row, data_cell_index, cellStyles2007);
            this.row_index = this.row_index + 1; // 行自增
        }
    }

    // 生成所有汇总行的data
    private void iteratorAllSumTitleDataRow(HSSFSheet sheet, List<List<RealTimeWebExcelTitleVO>> table_data_list,
                                            HSSFWorkbook workbook, Map<String, HSSFCellStyle> cellStyles2003) {
        HSSFRow table_data_row = null;
        for (List<RealTimeWebExcelTitleVO> data_row : table_data_list) {
            table_data_row = sheet.createRow(this.row_index);
            Integer data_cell_index = 0;
            this.iteratorDataCell(workbook, sheet, table_data_row, data_row, data_cell_index, cellStyles2003);
            this.row_index = this.row_index + 1; // 行自增
        }
    }

    // 处理数据行的单元格(2007版)
    private void iteratorDataCell2007(XSSFWorkbook workbook, XSSFSheet sheet, XSSFRow table_data_row, List<RealTimeWebExcelTitleVO> data_row,
                                      Integer data_cell_index, Map<String, XSSFCellStyle> cellStyles2007) {
        XSSFCell cell = null;
        for (RealTimeWebExcelTitleVO cellPojo : data_row) {
            cell = table_data_row.createCell(data_cell_index);
            if (cellPojo.getDataValue() == null) {
                cellPojo.setDataValue("");
                cellPojo.setDataType(CellStyleConts.STRING);
            }
            this.customDataType2007(workbook, sheet, cellPojo, cell, cellStyles2007);

            if (cellPojo.getComment()) { // 插入批注
                XSSFDrawing p = sheet.createDrawingPatriarch();
                XSSFComment comment = p.createCellComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
                comment.setString(new XSSFRichTextString(cellPojo.getCommentContent()));
                cell.setCellComment(comment);
            }

            if (cellPojo.getFirstRow() != null) {
                CellRangeAddress address = new CellRangeAddress(cellPojo.getFirstRow(), cellPojo.getLastRow(), cellPojo.getFirstCol(), cellPojo.getLastCol());
                sheet.addMergedRegion(address);
                // 使用RegionUtil类为合并后的单元格添加边框
                RegionUtil.setBorderBottom(BorderStyle.THIN, address, sheet); // 下边框
                RegionUtil.setBorderLeft(BorderStyle.THIN, address, sheet); // 左边框
                RegionUtil.setBorderRight(BorderStyle.THIN, address, sheet); // 有边框
                RegionUtil.setBorderTop(BorderStyle.THIN, address, sheet); // 上边框
                data_cell_index = cellPojo.getLastCol() + 1; // 单元格自增
            } else {
                data_cell_index = data_cell_index + 1; // 单元格自增
            }

        }
    }

    // 处理数据行的单元格
    private void iteratorDataCell(HSSFWorkbook workbook, HSSFSheet sheet, HSSFRow table_data_row, List<RealTimeWebExcelTitleVO> data_row,
                                  Integer data_cell_index, Map<String, HSSFCellStyle> cellStyles2003) {
        HSSFCell cell = null;
        for (RealTimeWebExcelTitleVO cellPojo : data_row) {
            cell = table_data_row.createCell(data_cell_index);
            if (cellPojo.getDataValue() == null) {
                cellPojo.setDataValue("");
                cellPojo.setDataType(CellStyleConts.STRING);
            }
            this.customDataType(workbook, sheet, cellPojo, cell, cellStyles2003);

            if (cellPojo.getComment()) { // 插入批注
                HSSFPatriarch p = sheet.createDrawingPatriarch();
                HSSFComment comment = p.createCellComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 3, 3, (short) 5, 6));
                comment.setString(new HSSFRichTextString(cellPojo.getCommentContent()));
                cell.setCellComment(comment);
            }

            if (cellPojo.getFirstRow() != null) {

                CellRangeAddress address = new CellRangeAddress(cellPojo.getFirstRow(), cellPojo.getLastRow(), cellPojo.getFirstCol(), cellPojo.getLastCol());
                if(!cellPojo.getFirstRow().equals(cellPojo.getLastRow()) || !cellPojo.getFirstCol().equals(cellPojo.getLastCol())){
                    sheet.addMergedRegion(address);
                }
                // 使用RegionUtil类为合并后的单元格添加边框
                RegionUtil.setBorderBottom(BorderStyle.THIN, address, sheet); // 下边框
                RegionUtil.setBorderLeft(BorderStyle.THIN, address, sheet); // 左边框
                RegionUtil.setBorderRight(BorderStyle.THIN, address, sheet); // 有边框
                RegionUtil.setBorderTop(BorderStyle.THIN, address, sheet); // 上边框
                data_cell_index = cellPojo.getLastCol() + 1; // 单元格自增
            } else {
                data_cell_index = data_cell_index + 1; // 单元格自增
            }

        }
    }
    // 处理单元格展示格式(2007版)
    private void customDataType2007(XSSFWorkbook workbook, XSSFSheet sheet, RealTimeWebExcelTitleVO cellPojo, XSSFCell cell,
                                    Map<String, XSSFCellStyle> cellStyles2007) {

        Object value = cellPojo.getDataValue();
        String dataType = cellPojo.getDataType();

        if (dataType != null) {
            if (dataType.equals(CellStyleConts.TITLE)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.TITLE));
                }
            } else if (dataType.equals(CellStyleConts.TITLE_GREY_40_PERCENT_BG)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.TITLE_GREY_40_PERCENT_BG));
                }
            } else if (dataType.equals(CellStyleConts.TITLE_218_238_243_BG)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.TITLE_218_238_243_BG));
                }
            } else if (dataType.equals(CellStyleConts.STRING_242_220_219_BG)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.STRING_242_220_219_BG));
                }
            } else if (dataType.equals(CellStyleConts.STRING_235_241_222_BG)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.STRING_235_241_222_BG));
                }
            } else if (dataType.equals(CellStyleConts.STRING_183_219_255_BG)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.STRING_183_219_255_BG));
                }
            } else if (dataType.equals(CellStyleConts.STRING)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.STRING));
                }
            } else if (dataType.equals(CellStyleConts.STRING_DEEP_RED)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.STRING_DEEP_RED));
                }
            }else if (dataType.equals(CellStyleConts.STRING_LEFT)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.STRING_LEFT));
                }
            }else if (dataType.equals(CellStyleConts.STRING_RIGHT)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.STRING_RIGHT));
                }
            } else if (dataType.equals(CellStyleConts.STRING_RED)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.STRING_RED));
                }
            } else if (dataType.equals(CellStyleConts.STRING_GREEN)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.STRING_GREEN));
                }
            } else if (dataType.equals(CellStyleConts.STRING_RED_FONT)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.STRING_RED_FONT));
                }
            } else if (dataType.equals(CellStyleConts.STRING_YELLOW_FONT)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.STRING_YELLOW_FONT));
                }
            }  else if (dataType.equals(CellStyleConts.STRING_242_242_242_BG)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.STRING_242_242_242_BG));
                }
            } else if (dataType.equals(CellStyleConts.QIAN)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN));
                }
            } else if (dataType.equals(CellStyleConts.QIAN_ZERO)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN_ZERO));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN_ZERO));
                }
            } else if (dataType.equals(CellStyleConts.QIAN_FOUR)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN_FOUR));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN_FOUR));
                }
            } else if (dataType.equals(CellStyleConts.QIAN_DEEP_RED)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN_DEEP_RED));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN_DEEP_RED));
                }
            } else if (dataType.equals(CellStyleConts.QIAN_RED)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN_RED));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN_RED));
                }
            } else if (dataType.equals(CellStyleConts.QIAN_GREEN)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN_GREEN));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN_GREEN));
                }
            } else if (dataType.equals(CellStyleConts.QIAN_RED_FONT)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN_RED_FONT));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN_RED_FONT));
                }
            } else if (dataType.equals(CellStyleConts.QIAN_GREEN_FONT)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN_GREEN_FONT));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN_GREEN_FONT));
                }
            } else if (dataType.equals(CellStyleConts.QIAN_YELLOW_FONT)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN_YELLOW_FONT));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN_YELLOW_FONT));
                }
            } else if (dataType.equals(CellStyleConts.QIAN_242_242_242_BG)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN_242_242_242_BG));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN_242_242_242_BG));
                }
            } else if (dataType.equals(CellStyleConts.QIAN_242_220_219_BG)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN_242_220_219_BG));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN_242_220_219_BG));
                }
            } else if (dataType.equals(CellStyleConts.QIAN_235_241_222_BG)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN_235_241_222_BG));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN_235_241_222_BG));
                }
            } else if (dataType.equals(CellStyleConts.QIAN_183_219_255_BG)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN_183_219_255_BG));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN_183_219_255_BG));
                }
            } else if (dataType.equals(CellStyleConts.BAI)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.BAI));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.BAI));
                }
            } else if (dataType.equals(CellStyleConts.BAI_DEEP_RED)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.BAI_DEEP_RED));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.BAI_DEEP_RED));
                }
            } else if (dataType.equals(CellStyleConts.BAI_255_199_206_BG)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.BAI_255_199_206_BG));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.BAI_255_199_206_BG));
                }
            } else if (dataType.equals(CellStyleConts.BAI_198_239_206_BG)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.BAI_198_239_206_BG));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.BAI_198_239_206_BG));
                }
            } else if (dataType.equals(CellStyleConts.BAI_RED_FONT)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.BAI_RED_FONT));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.BAI_RED_FONT));
                }
            } else if (dataType.equals(CellStyleConts.BAI_YELLOW_FONT)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.BAI_YELLOW_FONT));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.BAI_YELLOW_FONT));
                }
            } else if (dataType.equals(CellStyleConts.BAI_242_242_242_BG)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.BAI_242_242_242_BG));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.BAI_242_242_242_BG));
                }
            } else if (dataType.equals(CellStyleConts.DATE)) {
                if (value instanceof Date) {
                    cell.setCellValue((Date) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.DATE));
                }
                // 可自行新增样式判断
            } else {
                if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN));
                } else if (value instanceof Date) {
                    cell.setCellValue((Date) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.DATE));
                } else if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.STRING));
                } else if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN));
                } else if (value instanceof Integer) {
                    Integer integerValue = (Integer) value;
                    cell.setCellValue(integerValue.doubleValue());
                    cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN));
                }
            }

        } else {
            if (value instanceof Double) {
                cell.setCellValue((Double) value);
                cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN));
            } else if (value instanceof Date) {
                cell.setCellValue((Date) value);
                cell.setCellStyle(cellStyles2007.get(CellStyleConts.DATE));
            } else if (value instanceof String) {
                cell.setCellValue((String) value);
                cell.setCellStyle(cellStyles2007.get(CellStyleConts.STRING));
            } else if (value instanceof BigDecimal) {
                Double doubleValue = ((BigDecimal) value).doubleValue();
                cell.setCellValue(doubleValue);
                cell.setCellStyle(cellStyles2007.get(CellStyleConts.QIAN));
            }
        }
    }

    // 处理单元格展示格式
    private void customDataType(HSSFWorkbook workbook, HSSFSheet sheet, RealTimeWebExcelTitleVO cellPojo,
                                HSSFCell cell, Map<String, HSSFCellStyle> cellStyles2003) {

        Object value = cellPojo.getDataValue();
        String dataType = cellPojo.getDataType();

        if (dataType != null) {
            if (dataType.equals(CellStyleConts.TITLE)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.TITLE));
                }
            } else if (dataType.equals(CellStyleConts.TITLE_RED_FONT)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.TITLE_RED_FONT));
                }
            } else if (dataType.equals(CellStyleConts.TITLE_ROYAL_BLUE_FONT)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.TITLE_ROYAL_BLUE_FONT));
                }
            } else if (dataType.equals(CellStyleConts.TITLE_253_233_217_BG)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.TITLE_253_233_217_BG));
                }
            } else if (dataType.equals(CellStyleConts.TITLE_253_233_217_BG_FONTRED)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.TITLE_253_233_217_BG_FONTRED));
                }
            } else if (dataType.equals(CellStyleConts.TITLE_218_238_243_BG)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.TITLE_218_238_243_BG));
                }
            } else if (dataType.equals(CellStyleConts.TITLE_228_223_236_BG)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.TITLE_228_223_236_BG));
                }
            } else if (dataType.equals(CellStyleConts.TITLE_228_223_236_BG_FONTRED)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.TITLE_228_223_236_BG_FONTRED));
                }
            } else if (dataType.equals(CellStyleConts.TITLE_242_220_219_BG)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.TITLE_242_220_219_BG));
                }
            } else if (dataType.equals(CellStyleConts.TITLE_GREY_40_PERCENT_BG)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.TITLE_GREY_40_PERCENT_BG));
                }
            } else if (dataType.equals(CellStyleConts.TITLE_230_184_183_BG)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.TITLE_230_184_183_BG));
                }
            } else if (dataType.equals(CellStyleConts.TITLE_183_222_232_BG)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.TITLE_183_222_232_BG));
                }
            } else if (dataType.equals(CellStyleConts.TITLE_146_205_220_BG)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.TITLE_146_205_220_BG));
                }
            }  else if (dataType.equals(CellStyleConts.STRING)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.STRING));
                }
            }else if (dataType.equals(CellStyleConts.STRING_CENTER)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.STRING_CENTER));
                }
            } else if (dataType.equals(CellStyleConts.STRING_DEEP_RED)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.STRING_DEEP_RED));
                }
            } else if (dataType.equals(CellStyleConts.STRING_LEFT)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.STRING_LEFT));
                }
            } else if (dataType.equals(CellStyleConts.STRING_RIGHT)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.STRING_RIGHT));
                }
            } else if (dataType.equals(CellStyleConts.STRING_RED)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.STRING_RED));
                }
            } else if (dataType.equals(CellStyleConts.STRING_GREEN)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.STRING_GREEN));
                }
            } else if (dataType.equals(CellStyleConts.STRING_253_233_217)) { // 2021年5月18日 11:05:42 jr
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.STRING_253_233_217));
                }
            } else if (dataType.equals(CellStyleConts.STRING_RED_FONT)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.STRING_RED_FONT));
                }
            } else if (dataType.equals(CellStyleConts.STRING_YELLOW_FONT)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.STRING_YELLOW_FONT));
                }
            } else if (dataType.equals(CellStyleConts.STRING_GREY_40_PERCENT_BG)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.STRING_GREY_40_PERCENT_BG));
                }
            } else if (dataType.equals(CellStyleConts.STRING_255_199_206_BG)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.STRING_255_199_206_BG));
                }
            } else if (dataType.equals(CellStyleConts.STRING_230_184_183_BG)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.STRING_230_184_183_BG));
                }
            } else if (dataType.equals(CellStyleConts.STRING_218_238_243_BG)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.STRING_218_238_243_BG));
                }
            } else if (dataType.equals(CellStyleConts.STRING_GODL_BG)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.STRING_GODL_BG));
                }
            } else if (dataType.equals(CellStyleConts.STRING_LIGHT_ORANGE_BG)) {
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.STRING_LIGHT_ORANGE_BG));
                }
            } else if (dataType.equals(CellStyleConts.QIAN)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN));
                } else if (value instanceof String) {
                    cell.setCellValue(Double.parseDouble(value.toString()) );
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN));
                }
            } else if (dataType.equals(CellStyleConts.QIAN_ZERO)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN_ZERO));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN_ZERO));
                }
            } else if (dataType.equals(CellStyleConts.QIAN_DEEP_RED)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN_DEEP_RED));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN_DEEP_RED));
                }
            } else if (dataType.equals(CellStyleConts.QIAN_RED)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN_RED));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN_RED));
                }
            } else if (dataType.equals(CellStyleConts.QIAN_255_199_206_BG)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN_255_199_206_BG));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN_255_199_206_BG));
                }
            }  else if (dataType.equals(CellStyleConts.QIAN_255_199_206_BG_Z)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN_255_199_206_BG_Z));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN_255_199_206_BG_Z));
                }
            } else if (dataType.equals(CellStyleConts.QIAN_GREEN)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN_GREEN));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN_GREEN));
                }
            } else if (dataType.equals(CellStyleConts.QIAN_RED_FONT)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN_RED_FONT));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN_RED_FONT));
                }
            } else if (dataType.equals(CellStyleConts.QIAN_YELLOW_FONT)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN_YELLOW_FONT));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN_YELLOW_FONT));
                }
            }  else if (dataType.equals(CellStyleConts.QIAN_ROYAL_BLUE_FONT)) { // 2021年2月5日 14:26:12 jr
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN_ROYAL_BLUE_FONT));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN_ROYAL_BLUE_FONT));
                }
            } else if (dataType.equals(CellStyleConts.QIAN_242_220_219_BG)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN_242_220_219_BG));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN_242_220_219_BG));
                }
            } else if (dataType.equals(CellStyleConts.QIAN_GREY_40_PERCENT_BG)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN_GREY_40_PERCENT_BG));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN_GREY_40_PERCENT_BG));
                }
            } else if (dataType.equals(CellStyleConts.QIAN_230_184_183_BG)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN_230_184_183_BG));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN_230_184_183_BG));
                }
            } else if (dataType.equals(CellStyleConts.QIAN_218_238_243_BG)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN_218_238_243_BG));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN_218_238_243_BG));
                }
            } else if (dataType.equals(CellStyleConts.BAI)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.BAI));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.BAI));
                } else if(value instanceof String){
                    cell.setCellValue(Double.valueOf(value.toString()));
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.BAI));
                }
            } else if (dataType.equals(CellStyleConts.BAI_DEEP_RED)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.BAI_DEEP_RED));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.BAI_DEEP_RED));
                }
            } else if (dataType.equals(CellStyleConts.BAI_255_199_206_BG)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.BAI_255_199_206_BG));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.BAI_255_199_206_BG));
                }
            } else if (dataType.equals(CellStyleConts.BAI_198_239_206_BG)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.BAI_198_239_206_BG));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.BAI_198_239_206_BG));
                }
            } else if (dataType.equals(CellStyleConts.BAI_RED_FONT)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.BAI_RED_FONT));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.BAI_RED_FONT));
                }
            } else if (dataType.equals(CellStyleConts.BAI_YELLOW_FONT)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.BAI_YELLOW_FONT));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.BAI_YELLOW_FONT));
                }
            } else if (dataType.equals(CellStyleConts.BAI_ROYAL_BLUE_FONT)) { // 2021年2月5日 14:27:07 jr
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.BAI_ROYAL_BLUE_FONT));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.BAI_ROYAL_BLUE_FONT));
                }
            } else if (dataType.equals(CellStyleConts.BAI_GREY_40_PERCENT_BG)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.BAI_GREY_40_PERCENT_BG));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.BAI_GREY_40_PERCENT_BG));
                }
            } else if (dataType.equals(CellStyleConts.BAI_230_184_183_BG)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.BAI_230_184_183_BG));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.BAI_230_184_183_BG));
                }
            } else if (dataType.equals(CellStyleConts.BAI_146_205_220_BG)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.BAI_146_205_220_BG));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.BAI_146_205_220_BG));
                }
            } else if (dataType.equals(CellStyleConts.BAI_253_233_217_BG)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.BAI_253_233_217_BG));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.BAI_253_233_217_BG));
                }
            } else if (dataType.equals(CellStyleConts.BAI_216_228_188_BG)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.BAI_216_228_188_BG));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.BAI_216_228_188_BG));
                }
            } else if (dataType.equals(CellStyleConts.BAI_252_213_180_BG)) {
                if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.BAI_252_213_180_BG));
                } else if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.BAI_252_213_180_BG));
                }
            } else if (dataType.equals(CellStyleConts.DATE)) {
                if (value instanceof Date) {
                    cell.setCellValue((Date) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.DATE));
                } // 可自行新增样式判断
            } else {
                if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN));
                } else if (value instanceof Date) {
                    cell.setCellValue((Date) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.DATE));
                } else if (value instanceof String) {
                    cell.setCellValue((String) value);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.STRING));
                } else if (value instanceof BigDecimal) {
                    Double doubleValue = ((BigDecimal) value).doubleValue();
                    cell.setCellValue(doubleValue);
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN));
                } else if (value instanceof Integer) {
                    Integer integerValue = (Integer) value;
                    cell.setCellValue(integerValue.doubleValue());
                    cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN));
                }
            }

        } else {
            if (value instanceof Double) {
                cell.setCellValue((Double) value);
                cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN));
            } else if (value instanceof Date) {
                cell.setCellValue((Date) value);
                cell.setCellStyle(cellStyles2003.get(CellStyleConts.DATE));
            } else if (value instanceof String) {
                cell.setCellValue((String) value);
                cell.setCellStyle(cellStyles2003.get(CellStyleConts.STRING));
            } else if (value instanceof BigDecimal) {
                Double doubleValue = ((BigDecimal) value).doubleValue();
                cell.setCellValue(doubleValue);
                cell.setCellStyle(cellStyles2003.get(CellStyleConts.QIAN));
            }
        }
    }
    // 生成所有汇总行的title
    private void iteratorAllSumTitle(HSSFSheet sheet, List<RealTimeWebExcelTitleVO> table_title_list, HSSFCellStyle titleCellStyle) {
        HSSFRow table_title_row = sheet.createRow(this.row_index); // 标题-1行
        this.row_index = this.row_index + 1;
        HSSFCell row_cell = null; // 行内创建的单元格对象
        int cell_index = 0; // 行内单元格自增条件
        for (RealTimeWebExcelTitleVO tvo : table_title_list) {
            row_cell = table_title_row.createCell(cell_index); // 创建单元格
            row_cell.setCellStyle(titleCellStyle);
            if (tvo.getDataValue() != null) {
                if (tvo.getDataValue() instanceof String) {
                    row_cell.setCellValue((String)tvo.getDataValue()); // 单元格内容
                }
            } else {
                row_cell.setCellValue(""); // 单元格内容
                if (tvo.getDataValue() instanceof String) {
                    row_cell.setCellValue((String)tvo.getDataValue()); // 单元格内容
                }
            }
            if (tvo.getFirstRow() != null) {
                CellRangeAddress address = new CellRangeAddress(tvo.getFirstRow(), tvo.getLastRow(), tvo.getFirstCol(), tvo.getLastCol());
                sheet.addMergedRegion(address);
                // 使用RegionUtil类为合并后的单元格添加边框
                RegionUtil.setBorderBottom(BorderStyle.THIN, address, sheet); // 下边框
                RegionUtil.setBorderLeft(BorderStyle.THIN, address, sheet); // 左边框
                RegionUtil.setBorderRight(BorderStyle.THIN, address, sheet); // 有边框
                RegionUtil.setBorderTop(BorderStyle.THIN, address, sheet); // 上边框
                cell_index = tvo.getLastCol() + 1;
            } else {
                cell_index = cell_index + 1;
            }
        }
    }


    // =========================

    //  数据转换  导出excel数据
    public static List<List<RealTimeWebExcelTitleVO>> selectExcelData(
            List<CardIndexDictVo> resTitleList,
            List<Map<String, Object>> resDataList,
            List<String> baifbDataField

    )  throws Exception  {
        List<List<RealTimeWebExcelTitleVO>> excel_data_list = new ArrayList<List<RealTimeWebExcelTitleVO>>();

        // 标题
        List<RealTimeWebExcelTitleVO> excel_title_list = new ArrayList<RealTimeWebExcelTitleVO>();
        for (CardIndexDictVo cvo : resTitleList) {
            excel_title_list.add(new RealTimeWebExcelTitleVO(cvo.getIndexCode(), cvo.getIndexName(), "title"));
        }
        excel_data_list.add(excel_title_list);

        for (Map<String, Object> data_map : resDataList) { // 全部数据集
            List<RealTimeWebExcelTitleVO> data_list = new ArrayList<RealTimeWebExcelTitleVO>(); // excel数据行
            for (CardIndexDictVo ctVo : resTitleList) {
                if (baifbDataField.contains(ctVo.getIndexCode())) { // 百分比处理
                    data_list.add(new RealTimeWebExcelTitleVO("xx", data_map.get(ctVo.getIndexCode()), "bai"));
                } else {
                    data_list.add(new RealTimeWebExcelTitleVO("xx", data_map.get(ctVo.getIndexCode())));
                }
            }
            excel_data_list.add(data_list);
        }


        return  excel_data_list;
    }

    //  数据转换  导出excel数据
    public static List<List<RealTimeWebExcelTitleVO>> selectExcelDataQianAndBai(
            List<CardIndexDictVo> resTitleList,
            List<Map<String, Object>> resDataList,
            List<String> qianDataField,List<String> baiDataField

    )  throws Exception  {
        List<List<RealTimeWebExcelTitleVO>> excel_data_list = new ArrayList<List<RealTimeWebExcelTitleVO>>();

        // 标题
        List<RealTimeWebExcelTitleVO> excel_title_list = new ArrayList<RealTimeWebExcelTitleVO>();
        for (CardIndexDictVo cvo : resTitleList) {
            excel_title_list.add(new RealTimeWebExcelTitleVO(cvo.getIndexCode(), cvo.getIndexName(), "title"));
        }
        excel_data_list.add(excel_title_list);

        for (Map<String, Object> data_map : resDataList) { // 全部数据集
            List<RealTimeWebExcelTitleVO> data_list = new ArrayList<RealTimeWebExcelTitleVO>(); // excel数据行
            for (CardIndexDictVo ctVo : resTitleList) {
                if (qianDataField.contains(ctVo.getIndexCode())) { // 百分比处理
                    data_list.add(new RealTimeWebExcelTitleVO("xx", data_map.get(ctVo.getIndexCode()), CellStyleConts.QIAN));
                } else if(baiDataField.contains(ctVo.getIndexCode())){
                    data_list.add(new RealTimeWebExcelTitleVO("xx", data_map.get(ctVo.getIndexCode()), CellStyleConts.BAI));
                }
                else {
                    data_list.add(new RealTimeWebExcelTitleVO("xx", data_map.get(ctVo.getIndexCode())));
                }
            }
            excel_data_list.add(data_list);
        }


        return  excel_data_list;
    }


    //  数据转换  导出excel数据
    public static List<List<RealTimeWebExcelTitleVO>> selectExcelDataQianAndBaiTZSS(
            List<CardIndexDictVo> resTitleList,
            List<Map<String, Object>> resDataList,
            List<String> qianDataField,List<String> baiDataField

    )  throws Exception  {
        List<List<RealTimeWebExcelTitleVO>> excel_data_list = new ArrayList<List<RealTimeWebExcelTitleVO>>();

        // 标题
        List<RealTimeWebExcelTitleVO> excel_title_list = new ArrayList<RealTimeWebExcelTitleVO>();
        for (CardIndexDictVo cvo : resTitleList) {
            excel_title_list.add(new RealTimeWebExcelTitleVO(cvo.getIndexCode(), cvo.getIndexName(), "title"));
        }
        excel_data_list.add(excel_title_list);

        for (Map<String, Object> data_map : resDataList) { // 全部数据集
            List<RealTimeWebExcelTitleVO> data_list = new ArrayList<RealTimeWebExcelTitleVO>(); // excel数据行
            for (CardIndexDictVo ctVo : resTitleList) {
                //触发阈值需要设置颜色
                if(data_map.get("color") != null){
                    if("red".equals(data_map.get("color").toString())){
                        if (qianDataField.contains(ctVo.getIndexCode())) { // 百分比处理
                            data_list.add(new RealTimeWebExcelTitleVO("xx", data_map.get(ctVo.getIndexCode()), CellStyleConts.QIAN_RED_FONT));
                        } else if(baiDataField.contains(ctVo.getIndexCode())){
                            data_list.add(new RealTimeWebExcelTitleVO("xx", data_map.get(ctVo.getIndexCode()), CellStyleConts.BAI_RED_FONT));
                        } else {
                            data_list.add(new RealTimeWebExcelTitleVO("xx", data_map.get(ctVo.getIndexCode()),CellStyleConts.STRING_RED_FONT));
                        }
                    }else if("yellow".equals(data_map.get("color").toString())){
                        if (qianDataField.contains(ctVo.getIndexCode())) { // 百分比处理
                            data_list.add(new RealTimeWebExcelTitleVO("xx", data_map.get(ctVo.getIndexCode()), CellStyleConts.QIAN_YELLOW_FONT));
                        } else if(baiDataField.contains(ctVo.getIndexCode())){
                            data_list.add(new RealTimeWebExcelTitleVO("xx", data_map.get(ctVo.getIndexCode()), CellStyleConts.BAI_YELLOW_FONT));
                        } else {
                            data_list.add(new RealTimeWebExcelTitleVO("xx", data_map.get(ctVo.getIndexCode()),CellStyleConts.STRING_YELLOW_FONT));
                        }
                    }
                }else {
                    //未触发阈值，默认颜色即可
                    if (qianDataField.contains(ctVo.getIndexCode())) { // 百分比处理
                        data_list.add(new RealTimeWebExcelTitleVO("xx", data_map.get(ctVo.getIndexCode()), CellStyleConts.QIAN));
                    } else if(baiDataField.contains(ctVo.getIndexCode())){
                        data_list.add(new RealTimeWebExcelTitleVO("xx", data_map.get(ctVo.getIndexCode()), CellStyleConts.BAI));
                    } else {
                        data_list.add(new RealTimeWebExcelTitleVO("xx", data_map.get(ctVo.getIndexCode())));
                    }
                }

            }
            excel_data_list.add(data_list);
        }
        return  excel_data_list;
    }

}


