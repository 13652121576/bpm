package com.manage.common.util.excelUtils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.util.HashMap;
import java.util.Map;

// 导出excel样式
@Slf4j
public class ExcelCellStyles {

    // ==================07版本========================
    private XSSFWorkbook workbook2007;

    private XSSFFont titleFont2007;

    private XSSFFont dataFont2007;

    private XSSFFont dataFontRed2007;

    private XSSFFont dataFontYellow2007;

    private XSSFDataFormat df2007;

    private Map<String, XSSFCellStyle> cellStyles2007;

    // ==================03版本========================
    private HSSFWorkbook workbook2003;

    private HSSFFont titleFont2003;

    private HSSFFont dataFont2003;

    private HSSFFont dataFontRed2003;

    private HSSFFont dataFontYellow2003;

    private HSSFFont dataFontRoyalBlue2003; // jr

    private HSSFDataFormat df2003;

    private Map<String, HSSFCellStyle> cellStyles2003;


    // ==================07版本========================
    private void setCellStyles2007(XSSFWorkbook workbook) {

        // title格式
        XSSFCellStyle titleCellStyle = workbook.createCellStyle();
        titleCellStyle.setAlignment(CellStyle.ALIGN_CENTER); // 水平居中
        titleCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直居中
        titleCellStyle.setFont(this.titleFont2007);
        titleCellStyle.setWrapText(true); // 指定当单元格内容显示不下时自动换行
        titleCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        titleCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        titleCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        titleCellStyle.setBorderTop(BorderStyle.THIN); //上边框
        this.cellStyles2007.put(CellStyleConts.TITLE, titleCellStyle);

        // title 背景色 （191_191_191 + 黑色字体） jr
        XSSFCellStyle title_191_191_191_bg = workbook.createCellStyle();
        title_191_191_191_bg.setAlignment(CellStyle.ALIGN_CENTER); // 水平居中
        title_191_191_191_bg.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直居中
        title_191_191_191_bg.setFont(this.titleFont2007);
        title_191_191_191_bg.setWrapText(true); // 指定当单元格内容显示不下时自动换行
        title_191_191_191_bg.setBorderBottom(BorderStyle.THIN);//下边框
        title_191_191_191_bg.setBorderLeft(BorderStyle.THIN);//左边框
        title_191_191_191_bg.setBorderRight(BorderStyle.THIN);//右边框
        title_191_191_191_bg.setBorderTop(BorderStyle.THIN); //上边框
        title_191_191_191_bg.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        title_191_191_191_bg.setFillForegroundColor(new XSSFColor(new java.awt.Color(191, 191, 191)));
        //title_191_191_191_bg.setFillPattern(CellStyle.SOLID_FOREGROUND);
        this.cellStyles2007.put(CellStyleConts.BAI_GREY_40_PERCENT_BG, title_191_191_191_bg);

        // title 背景色 （218_238_243 + 黑色字体） jr
        XSSFCellStyle title_218_238_243_bg = workbook.createCellStyle();
        title_218_238_243_bg.setAlignment(CellStyle.ALIGN_CENTER); // 水平居中
        title_218_238_243_bg.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直居中
        title_218_238_243_bg.setFont(this.titleFont2007);
        title_218_238_243_bg.setWrapText(true); // 指定当单元格内容显示不下时自动换行
        title_218_238_243_bg.setBorderBottom(BorderStyle.THIN);//下边框
        title_218_238_243_bg.setBorderLeft(BorderStyle.THIN);//左边框
        title_218_238_243_bg.setBorderRight(BorderStyle.THIN);//右边框
        title_218_238_243_bg.setBorderTop(BorderStyle.THIN); //上边框
        title_218_238_243_bg.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        title_218_238_243_bg.setFillForegroundColor(new XSSFColor(new java.awt.Color(218, 238, 243)));
        this.cellStyles2007.put(CellStyleConts.TITLE_218_238_243_BG, title_218_238_243_bg);

        // data格式
        XSSFCellStyle dataCellStyle = workbook.createCellStyle();
        dataCellStyle.setFont(this.dataFont2007);
        dataCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        dataCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        dataCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        dataCellStyle.setBorderTop(BorderStyle.THIN); //上边框

        this.cellStyles2007.put(CellStyleConts.STRING, dataCellStyle);

        // data格式(水平靠左)
        XSSFCellStyle dataCellStyleLeft = workbook.createCellStyle();
        dataCellStyleLeft.setAlignment(CellStyle.ALIGN_LEFT); // 水平靠左
        dataCellStyleLeft.setFont(this.dataFont2007);
        dataCellStyleLeft.setBorderBottom(BorderStyle.THIN);//下边框
        dataCellStyleLeft.setBorderLeft(BorderStyle.THIN);//左边框
        dataCellStyleLeft.setBorderRight(BorderStyle.THIN);//右边框
        dataCellStyleLeft.setBorderTop(BorderStyle.THIN); //上边框

        this.cellStyles2007.put(CellStyleConts.STRING_LEFT, dataCellStyleLeft);


        // data格式(水平靠右)
        XSSFCellStyle dataCellStyleRight = workbook.createCellStyle();
        dataCellStyleRight.setAlignment(CellStyle.ALIGN_RIGHT); //水平靠右
        dataCellStyleRight.setFont(this.dataFont2007);
        dataCellStyleRight.setBorderBottom(BorderStyle.THIN);//下边框
        dataCellStyleRight.setBorderLeft(BorderStyle.THIN);//左边框
        dataCellStyleRight.setBorderRight(BorderStyle.THIN);//右边框
        dataCellStyleRight.setBorderTop(BorderStyle.THIN); //上边框

        this.cellStyles2007.put(CellStyleConts.STRING_RIGHT, dataCellStyleRight);

        // data格式（红色字体）
        XSSFCellStyle dataRedFontCellStyle = workbook.createCellStyle();
        dataRedFontCellStyle.setFont(this.dataFontRed2007);
        dataRedFontCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        dataRedFontCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        dataRedFontCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        dataRedFontCellStyle.setBorderTop(BorderStyle.THIN); //上边框

        this.cellStyles2007.put(CellStyleConts.STRING_RED_FONT, dataRedFontCellStyle);

        // data格式（黄色字体）
        XSSFCellStyle dataYellowFontCellStyle = workbook.createCellStyle();
        dataYellowFontCellStyle.setFont(this.dataFontYellow2007);
        dataYellowFontCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        dataYellowFontCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        dataYellowFontCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        dataYellowFontCellStyle.setBorderTop(BorderStyle.THIN); //上边框

        this.cellStyles2007.put(CellStyleConts.STRING_YELLOW_FONT, dataYellowFontCellStyle);

        // data格式（深红背景）
        XSSFCellStyle red_dataCellStyle = workbook.createCellStyle();
        red_dataCellStyle.setFont(this.dataFont2007);
        red_dataCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        red_dataCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        red_dataCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        red_dataCellStyle.setBorderTop(BorderStyle.THIN); //上边框
        red_dataCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        red_dataCellStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());//前景填充色

        this.cellStyles2007.put(CellStyleConts.STRING_DEEP_RED, red_dataCellStyle);

        // data格式（红色背景）
        XSSFCellStyle yellow_dataCellStyle = workbook.createCellStyle();
        yellow_dataCellStyle.setFont(this.dataFont2007);
        yellow_dataCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        yellow_dataCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        yellow_dataCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        yellow_dataCellStyle.setBorderTop(BorderStyle.THIN); //上边框
        yellow_dataCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        yellow_dataCellStyle.setFillForegroundColor(IndexedColors.CORAL.getIndex());//前景填充色

        this.cellStyles2007.put(CellStyleConts.STRING_RED, yellow_dataCellStyle);

        // data格式（绿色背景）
        XSSFCellStyle light_orange_dataCellStyle = workbook.createCellStyle();
        light_orange_dataCellStyle.setFont(this.dataFont2007);
        light_orange_dataCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        light_orange_dataCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        light_orange_dataCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        light_orange_dataCellStyle.setBorderTop(BorderStyle.THIN); //上边框
        light_orange_dataCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        light_orange_dataCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());//前景填充色
        this.cellStyles2007.put(CellStyleConts.STRING_GREEN, light_orange_dataCellStyle);


        // data string 背景色 （242_242_242 + 黑色字体） jr
        XSSFCellStyle string_242_242_242_bg = workbook.createCellStyle();
        string_242_242_242_bg.setFont(this.dataFont2007);
        string_242_242_242_bg.setBorderBottom(BorderStyle.THIN);//下边框
        string_242_242_242_bg.setBorderLeft(BorderStyle.THIN);//左边框
        string_242_242_242_bg.setBorderRight(BorderStyle.THIN);//右边框
        string_242_242_242_bg.setBorderTop(BorderStyle.THIN); //上边框
        string_242_242_242_bg.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        string_242_242_242_bg.setFillForegroundColor(new XSSFColor(new java.awt.Color(242, 242, 242)));
        this.cellStyles2007.put(CellStyleConts.STRING_242_242_242_BG, string_242_242_242_bg);

        // data string 黑色字体 + 浅红色背景色 242_220_219_BG jr
        XSSFCellStyle string_242_220_219_bg = workbook.createCellStyle();
        string_242_220_219_bg.setFont(this.dataFont2007);
        string_242_220_219_bg.setBorderBottom(BorderStyle.THIN);//下边框
        string_242_220_219_bg.setBorderLeft(BorderStyle.THIN);//左边框
        string_242_220_219_bg.setBorderRight(BorderStyle.THIN);//右边框
        string_242_220_219_bg.setBorderTop(BorderStyle.THIN); //上边框
        string_242_220_219_bg.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        string_242_220_219_bg.setFillForegroundColor(new XSSFColor(new java.awt.Color(242, 220, 219)));
        this.cellStyles2007.put(CellStyleConts.STRING_242_220_219_BG, string_242_220_219_bg);

        // data string 黑色字体 + 浅绿色背景色 235_241_222_BG jr
        XSSFCellStyle string_235_241_222_bg = workbook.createCellStyle();
        string_235_241_222_bg.setFont(this.dataFont2007);
        string_235_241_222_bg.setBorderBottom(BorderStyle.THIN);//下边框
        string_235_241_222_bg.setBorderLeft(BorderStyle.THIN);//左边框
        string_235_241_222_bg.setBorderRight(BorderStyle.THIN);//右边框
        string_235_241_222_bg.setBorderTop(BorderStyle.THIN); //上边框
        string_235_241_222_bg.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        string_235_241_222_bg.setFillForegroundColor(new XSSFColor(new java.awt.Color(235, 241, 222)));
        this.cellStyles2007.put(CellStyleConts.STRING_235_241_222_BG, string_235_241_222_bg);

        // data string 黑色字体 + 浅绿色背景色 235_241_222_BG jr
        XSSFCellStyle string_183_219_255_bg = workbook.createCellStyle();
        string_183_219_255_bg.setFont(this.dataFont2007);
        string_183_219_255_bg.setBorderBottom(BorderStyle.THIN);//下边框
        string_183_219_255_bg.setBorderLeft(BorderStyle.THIN);//左边框
        string_183_219_255_bg.setBorderRight(BorderStyle.THIN);//右边框
        string_183_219_255_bg.setBorderTop(BorderStyle.THIN); //上边框
        string_183_219_255_bg.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        string_183_219_255_bg.setFillForegroundColor(new XSSFColor(new java.awt.Color(183, 219, 255)));
        this.cellStyles2007.put(CellStyleConts.STRING_183_219_255_BG, string_183_219_255_bg);


        // 日期格式
        XSSFCellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(this.df2007.getFormat("yyyy-MM-dd")); // 日期
        dateCellStyle.setFont(this.dataFont2007);
        dateCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        dateCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        dateCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        dateCellStyle.setBorderTop(BorderStyle.THIN); //上边框

        this.cellStyles2007.put(CellStyleConts.DATE, dateCellStyle);

        // 金额格式
        XSSFCellStyle qianCellStyle = workbook.createCellStyle();
        qianCellStyle.setDataFormat(this.df2007.getFormat("#,#0.00")); // 千分位
        qianCellStyle.setFont(this.dataFont2007);
        qianCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        qianCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        qianCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        qianCellStyle.setBorderTop(BorderStyle.THIN); //上边框
        this.cellStyles2007.put(CellStyleConts.QIAN, qianCellStyle);

        // 金额格式
        XSSFCellStyle qian_zero = workbook.createCellStyle();
        qian_zero.setDataFormat(this.df2007.getFormat("#,#0")); // 千分位无小数点
        qian_zero.setFont(this.dataFont2007);
        qian_zero.setBorderBottom(BorderStyle.THIN);//下边框
        qian_zero.setBorderLeft(BorderStyle.THIN);//左边框
        qian_zero.setBorderRight(BorderStyle.THIN);//右边框
        qian_zero.setBorderTop(BorderStyle.THIN); //上边框
        this.cellStyles2007.put(CellStyleConts.QIAN_ZERO, qian_zero);

        // 金额格式
        XSSFCellStyle qian_four = workbook.createCellStyle();
        qian_four.setDataFormat(this.df2007.getFormat("#,#0.0000")); // 千分位
        qian_four.setFont(this.dataFont2007);
        qian_four.setBorderBottom(BorderStyle.THIN);//下边框
        qian_four.setBorderLeft(BorderStyle.THIN);//左边框
        qian_four.setBorderRight(BorderStyle.THIN);//右边框
        qian_four.setBorderTop(BorderStyle.THIN); //上边框
        this.cellStyles2007.put(CellStyleConts.QIAN_FOUR, qian_four);


        // 金额格式（红色字体）
        XSSFCellStyle qianRedFontCellStyle = workbook.createCellStyle();
        qianRedFontCellStyle.setDataFormat(this.df2007.getFormat("#,#0.00")); // 千分位
        qianRedFontCellStyle.setFont(this.dataFontRed2007);
        qianRedFontCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        qianRedFontCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        qianRedFontCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        qianRedFontCellStyle.setBorderTop(BorderStyle.THIN); //上边框
        this.cellStyles2007.put(CellStyleConts.QIAN_RED_FONT, qianRedFontCellStyle);

        // 金额格式（绿色字体）
        XSSFCellStyle qianGreenFontCellStyle = workbook.createCellStyle();
        qianGreenFontCellStyle.setDataFormat(this.df2007.getFormat("#,#0.00")); // 千分位
        qianGreenFontCellStyle.setFont(this.dataFontRed2007);
        qianGreenFontCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        qianGreenFontCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        qianGreenFontCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        qianGreenFontCellStyle.setBorderTop(BorderStyle.THIN); //上边框
        this.cellStyles2007.put(CellStyleConts.QIAN_GREEN_FONT, qianGreenFontCellStyle);


        // 金额格式（黄色字体）
        XSSFCellStyle qianYellowFontCellStyle = workbook.createCellStyle();
        qianYellowFontCellStyle.setDataFormat(this.df2007.getFormat("#,#0.00")); // 千分位
        qianYellowFontCellStyle.setFont(this.dataFontYellow2007);
        qianYellowFontCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        qianYellowFontCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        qianYellowFontCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        qianYellowFontCellStyle.setBorderTop(BorderStyle.THIN); //上边框

        this.cellStyles2007.put(CellStyleConts.QIAN_YELLOW_FONT, qianYellowFontCellStyle);

        // 金额格式（深红背景）
        XSSFCellStyle red_qianCellStyle = workbook.createCellStyle();
        red_qianCellStyle.setDataFormat(this.df2007.getFormat("#,#0.00")); // 千分位
        red_qianCellStyle.setFont(this.dataFont2007);
        red_qianCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        red_qianCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        red_qianCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        red_qianCellStyle.setBorderTop(BorderStyle.THIN); //上边框
        red_qianCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        red_qianCellStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());//前景填充色

        this.cellStyles2007.put(CellStyleConts.QIAN_DEEP_RED, red_qianCellStyle);

        // 金额格式（红色背景）
        XSSFCellStyle yellow_qianCellStyle = workbook.createCellStyle();
        yellow_qianCellStyle.setDataFormat(this.df2007.getFormat("#,#0.00")); // 千分位
        yellow_qianCellStyle.setFont(this.dataFont2007);
        yellow_qianCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        yellow_qianCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        yellow_qianCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        yellow_qianCellStyle.setBorderTop(BorderStyle.THIN); //上边框
        yellow_qianCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        yellow_qianCellStyle.setFillForegroundColor(IndexedColors.CORAL.getIndex());//前景填充色

        this.cellStyles2007.put(CellStyleConts.QIAN_RED, yellow_qianCellStyle);

        // 金额格式（绿色背景）
        XSSFCellStyle light_orange_qianCellStyle = workbook.createCellStyle();
        light_orange_qianCellStyle.setDataFormat(this.df2007.getFormat("#,#0.00")); // 千分位
        light_orange_qianCellStyle.setFont(this.dataFont2007);
        light_orange_qianCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        light_orange_qianCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        light_orange_qianCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        light_orange_qianCellStyle.setBorderTop(BorderStyle.THIN); //上边框
        light_orange_qianCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        light_orange_qianCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());//前景填充色
        this.cellStyles2007.put(CellStyleConts.QIAN_GREEN, light_orange_qianCellStyle);


        // data qian 背景色 （242_242_242 + 黑色字体） jr
        XSSFCellStyle qian_242_242_242_bg = workbook.createCellStyle();
        qian_242_242_242_bg.setDataFormat(this.df2007.getFormat("#,#0.00")); // 千分位
        qian_242_242_242_bg.setFont(this.dataFont2007);
        qian_242_242_242_bg.setWrapText(true); // 指定当单元格内容显示不下时自动换行
        qian_242_242_242_bg.setBorderBottom(BorderStyle.THIN);//下边框
        qian_242_242_242_bg.setBorderLeft(BorderStyle.THIN);//左边框
        qian_242_242_242_bg.setBorderRight(BorderStyle.THIN);//右边框
        qian_242_242_242_bg.setBorderTop(BorderStyle.THIN); //上边框
        qian_242_242_242_bg.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        qian_242_242_242_bg.setFillForegroundColor(new XSSFColor(new java.awt.Color(242, 242, 242)));
        this.cellStyles2007.put(CellStyleConts.QIAN_242_242_242_BG, qian_242_242_242_bg);

        // data  qian  浅红色背景 + 黑色字体 242_220_219
        XSSFCellStyle qian_242_220_219_bg = workbook.createCellStyle();
        qian_242_220_219_bg.setDataFormat(this.df2007.getFormat("#,#0.00")); // 千分位
        qian_242_220_219_bg.setFont(this.dataFont2007);
        qian_242_220_219_bg.setBorderBottom(BorderStyle.THIN);//下边框
        qian_242_220_219_bg.setBorderLeft(BorderStyle.THIN);//左边框
        qian_242_220_219_bg.setBorderRight(BorderStyle.THIN);//右边框
        qian_242_220_219_bg.setBorderTop(BorderStyle.THIN); //上边框
        qian_242_220_219_bg.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        qian_242_220_219_bg.setFillForegroundColor(new XSSFColor(new java.awt.Color(242, 220, 219)));
        this.cellStyles2007.put(CellStyleConts.QIAN_242_220_219_BG, qian_242_220_219_bg);

        // data  qian  浅绿色背景 + 黑色字体 235_241_222
        XSSFCellStyle qian_235_241_222_bg = workbook.createCellStyle();
        qian_235_241_222_bg.setDataFormat(this.df2007.getFormat("#,#0.00")); // 千分位
        qian_235_241_222_bg.setFont(this.dataFont2007);
        qian_235_241_222_bg.setBorderBottom(BorderStyle.THIN);//下边框
        qian_235_241_222_bg.setBorderLeft(BorderStyle.THIN);//左边框
        qian_235_241_222_bg.setBorderRight(BorderStyle.THIN);//右边框
        qian_235_241_222_bg.setBorderTop(BorderStyle.THIN); //上边框
        qian_235_241_222_bg.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        qian_235_241_222_bg.setFillForegroundColor(new XSSFColor(new java.awt.Color(235, 241, 222)));
        this.cellStyles2007.put(CellStyleConts.QIAN_235_241_222_BG, qian_235_241_222_bg);


        // data  qian  浅蓝色背景 + 黑色字体 183_219_255
        XSSFCellStyle qian_183_219_255_bg = workbook.createCellStyle();
        qian_183_219_255_bg.setDataFormat(this.df2007.getFormat("#,#0.00")); // 千分位
        qian_183_219_255_bg.setFont(this.dataFont2007);
        qian_183_219_255_bg.setBorderBottom(BorderStyle.THIN);//下边框
        qian_183_219_255_bg.setBorderLeft(BorderStyle.THIN);//左边框
        qian_183_219_255_bg.setBorderRight(BorderStyle.THIN);//右边框
        qian_183_219_255_bg.setBorderTop(BorderStyle.THIN); //上边框
        qian_183_219_255_bg.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        qian_183_219_255_bg.setFillForegroundColor(new XSSFColor(new java.awt.Color(183, 219, 255)));
        this.cellStyles2007.put(CellStyleConts.QIAN_183_219_255_BG, qian_183_219_255_bg);


        // 百分比格式
        XSSFCellStyle percentageCellStyle = workbook.createCellStyle();
        percentageCellStyle.setDataFormat(this.df2007.getFormat("0.00%")); // 百分比
        percentageCellStyle.setFont(this.dataFont2007);
        percentageCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        percentageCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        percentageCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        percentageCellStyle.setBorderTop(BorderStyle.THIN); //上边框

        this.cellStyles2007.put(CellStyleConts.BAI, percentageCellStyle);

        // 百分比格式（红色字体）
        XSSFCellStyle percentageRedFontCellStyle = workbook.createCellStyle();
        percentageRedFontCellStyle.setDataFormat(this.df2007.getFormat("0.00%")); // 百分比
        percentageRedFontCellStyle.setFont(this.dataFontRed2007);
        percentageRedFontCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        percentageRedFontCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        percentageRedFontCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        percentageRedFontCellStyle.setBorderTop(BorderStyle.THIN); //上边框

        this.cellStyles2007.put(CellStyleConts.BAI_RED_FONT, percentageRedFontCellStyle);

        // 百分比格式（黄色字体）
        XSSFCellStyle percentageYellowFontCellStyle = workbook.createCellStyle();
        percentageYellowFontCellStyle.setDataFormat(this.df2007.getFormat("0.00%")); // 百分比
        percentageYellowFontCellStyle.setFont(this.dataFontYellow2007);
        percentageYellowFontCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        percentageYellowFontCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        percentageYellowFontCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        percentageYellowFontCellStyle.setBorderTop(BorderStyle.THIN); //上边框

        this.cellStyles2007.put(CellStyleConts.BAI_RED_FONT, percentageYellowFontCellStyle);

        // 百分比格式（深红）
        XSSFCellStyle red_percentageCellStyle = workbook.createCellStyle();
        red_percentageCellStyle.setDataFormat(this.df2007.getFormat("0.00%")); // 百分比
        red_percentageCellStyle.setFont(this.dataFont2007);
        red_percentageCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        red_percentageCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        red_percentageCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        red_percentageCellStyle.setBorderTop(BorderStyle.THIN); //上边框
        red_percentageCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        red_percentageCellStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());//前景填充色

        this.cellStyles2007.put(CellStyleConts.BAI_DEEP_RED, red_percentageCellStyle);

        // 百分比格式（红色）
        XSSFCellStyle yellow_percentageCellStyle = workbook.createCellStyle();
        yellow_percentageCellStyle.setDataFormat(this.df2007.getFormat("0.00%")); // 百分比
        yellow_percentageCellStyle.setFont(this.dataFont2007);
        yellow_percentageCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        yellow_percentageCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        yellow_percentageCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        yellow_percentageCellStyle.setBorderTop(BorderStyle.THIN); //上边框
        yellow_percentageCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        yellow_percentageCellStyle.setFillForegroundColor(IndexedColors.CORAL.getIndex());//前景填充色

        this.cellStyles2007.put(CellStyleConts.BAI_RED, yellow_percentageCellStyle);

        // 百分比格式（绿色）
        XSSFCellStyle light_orange_percentageCellStyle = workbook.createCellStyle();
        light_orange_percentageCellStyle.setDataFormat(this.df2007.getFormat("0.00%")); // 百分比
        light_orange_percentageCellStyle.setFont(this.dataFont2007);
        light_orange_percentageCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        light_orange_percentageCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        light_orange_percentageCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        light_orange_percentageCellStyle.setBorderTop(BorderStyle.THIN); //上边框
        light_orange_percentageCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        light_orange_percentageCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());//前景填充色
        this.cellStyles2007.put(CellStyleConts.BAI_GREEN, light_orange_percentageCellStyle);

        // data qian 背景色 （242_242_242 + 黑色字体） jr
        XSSFCellStyle bai_242_242_242_bg = workbook.createCellStyle();
        bai_242_242_242_bg.setDataFormat(this.df2007.getFormat("0.00%")); // 百分比
        bai_242_242_242_bg.setFont(this.dataFont2007);
        bai_242_242_242_bg.setWrapText(true); // 指定当单元格内容显示不下时自动换行
        bai_242_242_242_bg.setBorderBottom(BorderStyle.THIN);//下边框
        bai_242_242_242_bg.setBorderLeft(BorderStyle.THIN);//左边框
        bai_242_242_242_bg.setBorderRight(BorderStyle.THIN);//右边框
        bai_242_242_242_bg.setBorderTop(BorderStyle.THIN); //上边框
        bai_242_242_242_bg.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        bai_242_242_242_bg.setFillForegroundColor(new XSSFColor(new java.awt.Color(242, 242, 242)));
        this.cellStyles2007.put(CellStyleConts.BAI_242_242_242_BG, bai_242_242_242_bg);

    }

    // ==================03版本========================
    private void setCellStyles2003(HSSFWorkbook workbook) {

        // title样式
        HSSFCellStyle titleCellStyle = workbook.createCellStyle();
        titleCellStyle.setAlignment(CellStyle.ALIGN_CENTER); // 水平居中
        titleCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直居中
        titleCellStyle.setFont(this.titleFont2003);
        titleCellStyle.setWrapText(true); // 指定当单元格内容显示不下时自动换行
        titleCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        titleCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        titleCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        titleCellStyle.setBorderTop(BorderStyle.THIN); //上边框

        this.cellStyles2003.put(CellStyleConts.TITLE, titleCellStyle);

        // data样式
        HSSFCellStyle dataCellStyle = workbook.createCellStyle();
        dataCellStyle.setFont(this.dataFont2003);
        dataCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        dataCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        dataCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        dataCellStyle.setBorderTop(BorderStyle.THIN); //上边框

        this.cellStyles2003.put(CellStyleConts.STRING, dataCellStyle);

        // data格式(水平靠左)
        HSSFCellStyle dataCellStyleLeft = workbook.createCellStyle();
        dataCellStyleLeft.setAlignment(CellStyle.ALIGN_LEFT); // 水平居中
        dataCellStyleLeft.setFont(this.dataFont2003);
        dataCellStyleLeft.setBorderBottom(BorderStyle.THIN);//下边框
        dataCellStyleLeft.setBorderLeft(BorderStyle.THIN);//左边框
        dataCellStyleLeft.setBorderRight(BorderStyle.THIN);//右边框
        dataCellStyleLeft.setBorderTop(BorderStyle.THIN); //上边框




        this.cellStyles2003.put(CellStyleConts.STRING_LEFT, dataCellStyleLeft);

        HSSFCellStyle dataCellCenterStyle = workbook.createCellStyle();
        dataCellCenterStyle.setAlignment(CellStyle.ALIGN_CENTER); // 水平居中
        dataCellCenterStyle.setFont(this.dataFont2003);
        dataCellCenterStyle.setBorderBottom(BorderStyle.THIN);//下边框
        dataCellCenterStyle.setBorderLeft(BorderStyle.THIN);//左边框
        dataCellCenterStyle.setBorderRight(BorderStyle.THIN);//右边框
        dataCellCenterStyle.setBorderTop(BorderStyle.THIN); //上边框

        this.cellStyles2003.put(CellStyleConts.STRING_CENTER, dataCellCenterStyle);


        // data格式(水平靠右)
        HSSFCellStyle dataCellStyleRight = workbook.createCellStyle();

        dataCellStyleRight.setAlignment(CellStyle.ALIGN_RIGHT); //水平靠右
        dataCellStyleRight.setFont(this.dataFont2003);
        dataCellStyleRight.setBorderBottom(BorderStyle.THIN);//下边框
        dataCellStyleRight.setBorderLeft(BorderStyle.THIN);//左边框
        dataCellStyleRight.setBorderRight(BorderStyle.THIN);//右边框
        dataCellStyleRight.setBorderTop(BorderStyle.THIN); //上边框
        this.cellStyles2003.put(CellStyleConts.STRING_RIGHT, dataCellStyleRight);

        // data样式（红色字体）
        HSSFCellStyle dataRedFontCellStyle = workbook.createCellStyle();
        dataRedFontCellStyle.setFont(this.dataFontRed2003);
        dataRedFontCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        dataRedFontCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        dataRedFontCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        dataRedFontCellStyle.setBorderTop(BorderStyle.THIN); //上边框

        this.cellStyles2003.put(CellStyleConts.STRING_RED_FONT, dataRedFontCellStyle);

        // data样式（黄色字体）
        HSSFCellStyle dataYellowFontCellStyle = workbook.createCellStyle();
        dataYellowFontCellStyle.setFont(this.dataFontYellow2003);
        dataYellowFontCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        dataYellowFontCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        dataYellowFontCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        dataYellowFontCellStyle.setBorderTop(BorderStyle.THIN); //上边框

        this.cellStyles2003.put(CellStyleConts.STRING_YELLOW_FONT, dataYellowFontCellStyle);

        // data样式（蓝色字体）
        HSSFCellStyle dataRoyalBlueFontCellStyle = workbook.createCellStyle();  // jr
        dataRoyalBlueFontCellStyle.setFont(this.dataFontRoyalBlue2003);
        dataRoyalBlueFontCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        dataRoyalBlueFontCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        dataRoyalBlueFontCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        dataRoyalBlueFontCellStyle.setBorderTop(BorderStyle.THIN); //上边框
        this.cellStyles2003.put(CellStyleConts.STRING_ROYAL_BLUE_FONT, dataRoyalBlueFontCellStyle);


        // data样式（深红）
        HSSFCellStyle red_dataCellStyle = workbook.createCellStyle();
        red_dataCellStyle.setFont(this.dataFont2003);
        red_dataCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        red_dataCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        red_dataCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        red_dataCellStyle.setBorderTop(BorderStyle.THIN); //上边框
        red_dataCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        red_dataCellStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());//前景填充色

        this.cellStyles2003.put(CellStyleConts.STRING_DEEP_RED, red_dataCellStyle);

        // data样式（红色）
        HSSFCellStyle yellow_dataCellStyle = workbook.createCellStyle();
        yellow_dataCellStyle.setFont(this.dataFont2003);
        yellow_dataCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        yellow_dataCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        yellow_dataCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        yellow_dataCellStyle.setBorderTop(BorderStyle.THIN); //上边框
        yellow_dataCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        yellow_dataCellStyle.setFillForegroundColor(IndexedColors.CORAL.getIndex());//前景填充色

        this.cellStyles2003.put(CellStyleConts.STRING_RED, yellow_dataCellStyle);

        // data样式（绿色）
        HSSFCellStyle light_orange_dataCellStyle = workbook.createCellStyle();
        light_orange_dataCellStyle.setFont(this.dataFont2003);
        light_orange_dataCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        light_orange_dataCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        light_orange_dataCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        light_orange_dataCellStyle.setBorderTop(BorderStyle.THIN); //上边框
        light_orange_dataCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        light_orange_dataCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());//前景填充色

        this.cellStyles2003.put(CellStyleConts.STRING_GREEN, light_orange_dataCellStyle);

        // data样式（蓝色）
        HSSFCellStyle royal_blue_datacellstyle = workbook.createCellStyle();
        royal_blue_datacellstyle.setFont(this.dataFont2003);
        royal_blue_datacellstyle.setBorderBottom(BorderStyle.THIN);//下边框
        royal_blue_datacellstyle.setBorderLeft(BorderStyle.THIN);//左边框
        royal_blue_datacellstyle.setBorderRight(BorderStyle.THIN);//右边框
        royal_blue_datacellstyle.setBorderTop(BorderStyle.THIN); //上边框
        royal_blue_datacellstyle.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        royal_blue_datacellstyle.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());//前景填充色

        this.cellStyles2003.put(CellStyleConts.STRING_ROYAL_BLUE, royal_blue_datacellstyle);

        // ====================================================================================


        // 日期样式
        HSSFCellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(this.df2003.getFormat("yyyy-MM-dd")); // 日期
        dateCellStyle.setFont(this.dataFont2003);
        dateCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        dateCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        dateCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        dateCellStyle.setBorderTop(BorderStyle.THIN); //上边框

        this.cellStyles2003.put(CellStyleConts.DATE, dateCellStyle);

        // ====================================================================================


        // 金额格式
        HSSFCellStyle qianCellStyle = workbook.createCellStyle();
        qianCellStyle.setDataFormat(this.df2003.getFormat("#,#0.00")); // 千分位
        qianCellStyle.setFont(this.dataFont2003);
        qianCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        qianCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        qianCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        qianCellStyle.setBorderTop(BorderStyle.THIN); //上边框

        this.cellStyles2003.put(CellStyleConts.QIAN, qianCellStyle);

        // 金额格式（红色字体）
        HSSFCellStyle qianRedFontCellStyle = workbook.createCellStyle();
        qianRedFontCellStyle.setDataFormat(this.df2003.getFormat("#,#0.00")); // 千分位
        qianRedFontCellStyle.setFont(this.dataFontRed2003);
        qianRedFontCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        qianRedFontCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        qianRedFontCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        qianRedFontCellStyle.setBorderTop(BorderStyle.THIN); //上边框

        this.cellStyles2003.put(CellStyleConts.QIAN_RED_FONT, qianRedFontCellStyle);

        // 金额格式（黄色字体）
        HSSFCellStyle qianYellowFontCellStyle = workbook.createCellStyle();
        qianYellowFontCellStyle.setDataFormat(this.df2003.getFormat("#,#0.00")); // 千分位
        qianYellowFontCellStyle.setFont(this.dataFontYellow2003);
        qianYellowFontCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        qianYellowFontCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        qianYellowFontCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        qianYellowFontCellStyle.setBorderTop(BorderStyle.THIN); //上边框

        this.cellStyles2003.put(CellStyleConts.QIAN_YELLOW_FONT, qianYellowFontCellStyle);

        // 金额格式（蓝色字体）
        HSSFCellStyle qianRoyalBlueFontCellStyle = workbook.createCellStyle(); // jr
        qianRoyalBlueFontCellStyle.setDataFormat(this.df2003.getFormat("#,#0.00")); // 千分位
        qianRoyalBlueFontCellStyle.setFont(this.dataFontRoyalBlue2003);
        qianRoyalBlueFontCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        qianRoyalBlueFontCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        qianRoyalBlueFontCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        qianRoyalBlueFontCellStyle.setBorderTop(BorderStyle.THIN); //上边框
        this.cellStyles2003.put(CellStyleConts.QIAN_ROYAL_BLUE_FONT, qianRoyalBlueFontCellStyle);


        // 金额格式（深红）
        HSSFCellStyle red_qianCellStyle = workbook.createCellStyle();
        red_qianCellStyle.setDataFormat(this.df2003.getFormat("#,#0.00")); // 千分位
        red_qianCellStyle.setFont(this.dataFont2003);
        red_qianCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        red_qianCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        red_qianCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        red_qianCellStyle.setBorderTop(BorderStyle.THIN); //上边框
        red_qianCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        red_qianCellStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());//前景填充色

        this.cellStyles2003.put(CellStyleConts.QIAN_DEEP_RED, red_qianCellStyle);

        // 金额格式（红色）
        HSSFCellStyle yellow_qianCellStyle = workbook.createCellStyle();
        yellow_qianCellStyle.setDataFormat(this.df2003.getFormat("#,#0.00")); // 千分位
        yellow_qianCellStyle.setFont(this.dataFont2003);
        yellow_qianCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        yellow_qianCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        yellow_qianCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        yellow_qianCellStyle.setBorderTop(BorderStyle.THIN); //上边框
        yellow_qianCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        yellow_qianCellStyle.setFillForegroundColor(IndexedColors.CORAL.getIndex());//前景填充色

        this.cellStyles2003.put(CellStyleConts.QIAN_RED, yellow_qianCellStyle);

        // 金额格式（绿色）
        HSSFCellStyle light_orange_qianCellStyle = workbook.createCellStyle();
        light_orange_qianCellStyle.setDataFormat(this.df2003.getFormat("#,#0.00")); // 千分位
        light_orange_qianCellStyle.setFont(this.dataFont2003);
        light_orange_qianCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        light_orange_qianCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        light_orange_qianCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        light_orange_qianCellStyle.setBorderTop(BorderStyle.THIN); //上边框
        light_orange_qianCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        light_orange_qianCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());//前景填充色

        this.cellStyles2003.put(CellStyleConts.QIAN_GREEN, light_orange_qianCellStyle);

        // ====================================================================================

        // 百分比样式
        HSSFCellStyle percentageCellStyle = workbook.createCellStyle();
        percentageCellStyle.setDataFormat(this.df2003.getFormat("0.00%")); // 百分比
        percentageCellStyle.setFont(this.dataFont2003);
        percentageCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        percentageCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        percentageCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        percentageCellStyle.setBorderTop(BorderStyle.THIN); //上边框

        this.cellStyles2003.put(CellStyleConts.BAI, percentageCellStyle);

        // 百分比样式(红色字体)
        HSSFCellStyle percentageRedFontCellStyle = workbook.createCellStyle();
        percentageRedFontCellStyle.setDataFormat(this.df2003.getFormat("0.00%")); // 百分比
        percentageRedFontCellStyle.setFont(this.dataFontRed2003);
        percentageRedFontCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        percentageRedFontCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        percentageRedFontCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        percentageRedFontCellStyle.setBorderTop(BorderStyle.THIN); //上边框

        this.cellStyles2003.put(CellStyleConts.BAI_RED_FONT, percentageRedFontCellStyle);

        // 百分比样式(黄色字体)
        HSSFCellStyle percentageYellowFontCellStyle = workbook.createCellStyle();
        percentageYellowFontCellStyle.setDataFormat(this.df2003.getFormat("0.00%")); // 百分比
        percentageYellowFontCellStyle.setFont(this.dataFontYellow2003);
        percentageYellowFontCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        percentageYellowFontCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        percentageYellowFontCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        percentageYellowFontCellStyle.setBorderTop(BorderStyle.THIN); //上边框

        this.cellStyles2003.put(CellStyleConts.BAI_YELLOW_FONT, percentageYellowFontCellStyle);

        // 百分比样式(蓝色字体)
        HSSFCellStyle percentageRoyalBlueFontCellStyle = workbook.createCellStyle(); // jr
        percentageRoyalBlueFontCellStyle.setDataFormat(this.df2003.getFormat("0.00%")); // 百分比
        percentageRoyalBlueFontCellStyle.setFont(this.dataFontRoyalBlue2003);
        percentageRoyalBlueFontCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        percentageRoyalBlueFontCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        percentageRoyalBlueFontCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        percentageRoyalBlueFontCellStyle.setBorderTop(BorderStyle.THIN); //上边框
        this.cellStyles2003.put(CellStyleConts.BAI_ROYAL_BLUE_FONT, percentageRoyalBlueFontCellStyle);


        // 百分比样式（深红）
        HSSFCellStyle red_percentageCellStyle = workbook.createCellStyle();
        red_percentageCellStyle.setDataFormat(this.df2003.getFormat("0.00%")); // 百分比
        red_percentageCellStyle.setFont(this.dataFont2003);
        red_percentageCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        red_percentageCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        red_percentageCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        red_percentageCellStyle.setBorderTop(BorderStyle.THIN); //上边框
        red_percentageCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        red_percentageCellStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());//前景填充色

        this.cellStyles2003.put(CellStyleConts.BAI_DEEP_RED, red_percentageCellStyle);

        // 百分比样式（红色）
        HSSFCellStyle yellow_percentageCellStyle = workbook.createCellStyle();
        yellow_percentageCellStyle.setDataFormat(this.df2003.getFormat("0.00%")); // 百分比
        yellow_percentageCellStyle.setFont(this.dataFont2003);
        yellow_percentageCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        yellow_percentageCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        yellow_percentageCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        yellow_percentageCellStyle.setBorderTop(BorderStyle.THIN); //上边框
        yellow_percentageCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        yellow_percentageCellStyle.setFillForegroundColor(IndexedColors.CORAL.getIndex());//前景填充色

        this.cellStyles2003.put(CellStyleConts.BAI_RED, yellow_percentageCellStyle);

        // 百分比样式（绿色）
        HSSFCellStyle light_orange_percentageCellStyle = workbook.createCellStyle();
        light_orange_percentageCellStyle.setDataFormat(this.df2003.getFormat("0.00%")); // 百分比
        light_orange_percentageCellStyle.setFont(this.dataFont2003);
        light_orange_percentageCellStyle.setBorderBottom(BorderStyle.THIN);//下边框
        light_orange_percentageCellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        light_orange_percentageCellStyle.setBorderRight(BorderStyle.THIN);//右边框
        light_orange_percentageCellStyle.setBorderTop(BorderStyle.THIN); //上边框
        light_orange_percentageCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        light_orange_percentageCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());//前景填充色

        this.cellStyles2003.put(CellStyleConts.BAI_GREEN, light_orange_percentageCellStyle);


        HSSFCellStyle bai_216_228_188Style = workbook.createCellStyle();
        HSSFPalette pbai_216_228_188 = workbook.getCustomPalette();  //wb HSSFWorkbook对象
        bai_216_228_188Style.setDataFormat(this.df2003.getFormat("0.00%")); // 百分比
        bai_216_228_188Style.setFont(this.dataFont2003);
        bai_216_228_188Style.setBorderBottom(BorderStyle.THIN);//下边框
        bai_216_228_188Style.setBorderLeft(BorderStyle.THIN);//左边框
        bai_216_228_188Style.setBorderRight(BorderStyle.THIN);//右边框
        bai_216_228_188Style.setBorderTop(BorderStyle.THIN); //上边框
        bai_216_228_188Style.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        bai_216_228_188Style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());//前景填充色
        pbai_216_228_188.setColorAtIndex((short)37, (byte) (216), (byte) (228), (byte) (188));
        bai_216_228_188Style.setFillForegroundColor((short)37);//前景填充色
        this.cellStyles2003.put(CellStyleConts.BAI_216_228_188_BG, bai_216_228_188Style);

        HSSFCellStyle bai_252_213_180Style = workbook.createCellStyle(); // 红色背景
        HSSFPalette pbai_252_213_180 = workbook.getCustomPalette();  //wb HSSFWorkbook对象
        bai_252_213_180Style.setDataFormat(this.df2003.getFormat("0.00%")); // 百分比
        bai_252_213_180Style.setFont(this.dataFont2003);
        bai_252_213_180Style.setBorderBottom(BorderStyle.THIN);//下边框
        bai_252_213_180Style.setBorderLeft(BorderStyle.THIN);//左边框
        bai_252_213_180Style.setBorderRight(BorderStyle.THIN);//右边框
        bai_252_213_180Style.setBorderTop(BorderStyle.THIN); //上边框
        bai_252_213_180Style.setFillPattern(CellStyle.SOLID_FOREGROUND);//设置前景填充样式
        pbai_252_213_180.setColorAtIndex((short)38, (byte) (252), (byte) (213), (byte) (180));
        bai_252_213_180Style.setFillForegroundColor((short)38);//前景填充色
        this.cellStyles2003.put(CellStyleConts.BAI_252_213_180_BG, bai_252_213_180Style);

    }

    // 初始化字体
    public ExcelCellStyles(Workbook workbook) {
        if (workbook instanceof XSSFWorkbook) {
            workbook2007 = (XSSFWorkbook) workbook;

            // ==================07版本========================
            this.titleFont2007 = workbook2007.createFont(); // 生成一个字体
            this.titleFont2007.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
            this.titleFont2007.setFontName("宋体");
            this.titleFont2007.setFontHeightInPoints((short)10);

            this.dataFont2007 = workbook2007.createFont(); // 黑色字体
            this.dataFont2007.setFontName("宋体");
            this.dataFont2007.setFontHeightInPoints((short)10);

            this.dataFontRed2007 = workbook2007.createFont(); // 红色字体
            this.dataFontRed2007.setFontName("宋体");
            this.dataFontRed2007.setColor(IndexedColors.RED.getIndex());
            this.dataFontRed2007.setFontHeightInPoints((short)10);

            this.dataFontYellow2007 = workbook2007.createFont(); // 黄色字体
            this.dataFontYellow2007.setFontName("宋体");
            this.dataFontYellow2007.setColor(IndexedColors.YELLOW.getIndex());
            this.dataFontYellow2007.setFontHeightInPoints((short)10);

            this.cellStyles2007 = new HashMap<String, XSSFCellStyle>();
            this.df2007 = workbook2007.createDataFormat();

            setCellStyles2007(workbook2007);

        } else if (workbook instanceof HSSFWorkbook) {
            workbook2003 = (HSSFWorkbook) workbook;

            // ==================03版本========================
            this.titleFont2003 = workbook2003.createFont(); // 生成一个字体
            this.titleFont2003.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
            this.titleFont2003.setFontName("宋体");

            this.dataFont2003 = workbook2003.createFont(); // 黑色字体
            this.dataFont2003.setFontName("宋体");

            this.dataFontRed2003 = workbook2003.createFont(); // 红色字体
            this.dataFontRed2003.setFontName("宋体");
            this.dataFontRed2003.setColor(IndexedColors.RED.getIndex());

            this.dataFontYellow2003 = workbook2003.createFont(); // 黄色字体
            this.dataFontYellow2003.setFontName("宋体");
            this.dataFontYellow2003.setColor(IndexedColors.YELLOW.getIndex());

            this.dataFontRoyalBlue2003 = workbook2003.createFont(); // 蓝色字体 jr
            this.dataFontRoyalBlue2003.setFontName("宋体");
            this.dataFontRoyalBlue2003.setColor(IndexedColors.ROYAL_BLUE.getIndex());

            this.cellStyles2003 = new HashMap<String, HSSFCellStyle>();
            this.df2003 = workbook2003.createDataFormat();

            setCellStyles2003(workbook2003);
        }

    }

    // ==================07版本========================
    public Map<String, XSSFCellStyle> getCellStyles2007() {
        return cellStyles2007;
    }

    // ==================03版本========================
    public Map<String, HSSFCellStyle> getCellStyles2003() {
        return cellStyles2003;
    }


}
