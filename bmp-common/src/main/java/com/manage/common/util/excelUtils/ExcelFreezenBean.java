package com.manage.common.util.excelUtils;

import lombok.extern.slf4j.Slf4j;

//excel列冻结参数
@Slf4j
public class ExcelFreezenBean implements java.io.Serializable {

    private Integer colSplit;
    private Integer rowSplit;
    private Integer leftmostColumn;
    private Integer topRow;

    public Integer getColSplit() {
        return colSplit;
    }

    public void setColSplit(Integer colSplit) {
        this.colSplit = colSplit;
    }

    public Integer getRowSplit() {
        return rowSplit;
    }

    public void setRowSplit(Integer rowSplit) {
        this.rowSplit = rowSplit;
    }

    public Integer getLeftmostColumn() {
        return leftmostColumn;
    }

    public void setLeftmostColumn(Integer leftmostColumn) {
        this.leftmostColumn = leftmostColumn;
    }

    public Integer getTopRow() {
        return topRow;
    }

    public void setTopRow(Integer topRow) {
        this.topRow = topRow;
    }

    public ExcelFreezenBean(Integer colSplit, Integer rowSplit, Integer leftmostColumn, Integer topRow) {
        super();
        this.colSplit = colSplit;
        this.rowSplit = rowSplit;
        this.leftmostColumn = leftmostColumn;
        this.topRow = topRow;
    }

}
