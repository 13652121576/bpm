package com.manage.common.util.excelUtils;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

//导出excel的title处理
public class RealTimeWebExcelTitleVO implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String dataId;

    private Object dataValue;
    private String dataType;

    private Integer firstRow; // 开始行
    private Integer lastRow; // 结束行
    private Integer firstCol; // 开始列
    private Integer lastCol; // 结束列

    private Boolean isComment = false;
    ; // 是否批注
    private String commentContent; // 批注内容

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public Object getDataValue() {
        return dataValue;
    }

    public void setDataValue(Object dataValue) {
        this.dataValue = dataValue;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(Integer firstRow) {
        this.firstRow = firstRow;
    }

    public Integer getLastRow() {
        return lastRow;
    }

    public void setLastRow(Integer lastRow) {
        this.lastRow = lastRow;
    }

    public Integer getFirstCol() {
        return firstCol;
    }

    public void setFirstCol(Integer firstCol) {
        this.firstCol = firstCol;
    }

    public Integer getLastCol() {
        return lastCol;
    }

    public void setLastCol(Integer lastCol) {
        this.lastCol = lastCol;
    }

    public Boolean getComment() {
        return isComment;
    }

    public void setComment(Boolean comment) {
        isComment = comment;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public RealTimeWebExcelTitleVO(String dataId, Object dataValue, String dataType, Integer firstRow, Integer lastRow,
                                   Integer firstCol, Integer lastCol) {
        super();
        this.dataId = dataId;
        this.dataValue = dataValue;
        this.dataType = dataType;
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.firstCol = firstCol;
        this.lastCol = lastCol;
    }

    public RealTimeWebExcelTitleVO(String dataId, Object dataValue, String dataType) {
        super();
        this.dataId = dataId;
        this.dataValue = dataValue;
        this.dataType = dataType;
        this.firstRow = null;
        this.lastRow = null;
        this.firstCol = null;
        this.lastCol = null;
    }


    public RealTimeWebExcelTitleVO(String dataId, Object dataValue, String dataType, Boolean isBg) {
        super();
        this.dataId = dataId;
        this.dataValue = dataValue;

        if (isBg) {
            if (dataValue instanceof BigDecimal) {
                double value1 = ((BigDecimal) dataValue).doubleValue();
                if (value1 < -0.01) {
                    dataType = CellStyleConts.BAI_216_228_188_BG;
                } else if (value1 > 0.01) {
                    dataType = CellStyleConts.BAI_252_213_180_BG;

                }
            }

        }

        this.dataType = dataType;
        this.firstRow = null;
        this.lastRow = null;
        this.firstCol = null;
        this.lastCol = null;
    }

    // 加批注单元格
    public RealTimeWebExcelTitleVO(String dataId, Object dataValue, String dataType, Boolean isComment, String commentContent) {
        super();
        this.dataId = dataId;
        this.dataValue = dataValue;
        this.dataType = dataType;
        this.firstRow = null;
        this.lastRow = null;
        this.firstCol = null;
        this.lastCol = null;
        this.isComment = isComment;
        this.commentContent = commentContent;
    }

    public RealTimeWebExcelTitleVO() {
        super();
    }

    public RealTimeWebExcelTitleVO(String dataId, Object dataValue) {
        super();
        this.dataId = dataId;
        this.dataValue = dataValue;
    }

    @Override
    public String toString() {
        return "RealTimeWebExcelTitleVO [dataId=" + dataId + ", dataValue=" + dataValue + ", dataType=" + dataType
                + ", firstRow=" + firstRow + ", lastRow=" + lastRow + ", firstCol=" + firstCol + ", lastCol=" + lastCol
                + "]";
    }
}
