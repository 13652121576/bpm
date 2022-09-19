package com.manage.common.util.excelUtils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CardIndexDictVo implements java.io.Serializable {

    private String indexCode; // 数据库指标列
    private String indexName;
    private String indexLevel;

    private String columnCode;
    private String columnName;
    private String dbColumnCode;

    private String columnAlign = "center"; // 字段居中方式，默认居中
    private String benchCode;
    private String portDim;

    private Boolean isIndeterminate = true; // 是否显示全选
    private Boolean isCheckAll = false; // 判断全选按钮是否打勾
    private List<String> checkedIndexPlan = new ArrayList<String>(); // 已经选中的数组
    private String halfCheckedIndexPlan; // 指标的半选择状态
    private String titleCode;

    public Boolean getCheckAll() {
        return isCheckAll;
    }

    public void setCheckAll(Boolean checkAll) {
        isCheckAll = checkAll;
    }

    public String getTitleCode() {
        return titleCode;
    }

    public void setTitleCode(String titleCode) {
        this.titleCode = titleCode;
    }

    private List<CardIndexDictVo> children;

    public String getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getColumnCode() {
        return columnCode;
    }

    public void setColumnCode(String columnCode) {
        this.columnCode = columnCode;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnAlign() {
        return columnAlign;
    }

    public void setColumnAlign(String columnAlign) {
        this.columnAlign = columnAlign;
    }

    public String getHalfCheckedIndexPlan() {
        return halfCheckedIndexPlan;
    }

    public void setHalfCheckedIndexPlan(String halfCheckedIndexPlan) {
        this.halfCheckedIndexPlan = halfCheckedIndexPlan;
    }

    public String getBenchCode() {
        if (benchCode == null) {
            return "";
        }
        return benchCode;
    }

    public void setBenchCode(String benchCode) {
        this.benchCode = benchCode;
    }

    public Boolean getIndeterminate() {
        return isIndeterminate;
    }

    public void setIndeterminate(Boolean indeterminate) {
        isIndeterminate = indeterminate;
    }

    public Boolean getIsCheckAll() {
        return isCheckAll;
    }

    public void setIsCheckAll(Boolean checkAll) {
        isCheckAll = checkAll;
    }

    public List<String> getCheckedIndexPlan() {
        return checkedIndexPlan;
    }

    public void setCheckedIndexPlan(List<String> checkedIndexPlan) {
        this.checkedIndexPlan = checkedIndexPlan;
    }

    public List<CardIndexDictVo> getChildren() {
        return children;
    }

    public void setChildren(List<CardIndexDictVo> children) {
        this.children = children;
    }

    public String getIndexLevel() {
        return indexLevel;
    }

    public void setIndexLevel(String indexLevel) {
        this.indexLevel = indexLevel;
    }

    public String getPortDim() {
        if (this.portDim == null) {
            return "";
        }
        return portDim;
    }

    public String getDbColumnCode() {
        return dbColumnCode;
    }

    public void setDbColumnCode(String dbColumnCode) {
        this.dbColumnCode = dbColumnCode;
    }

    public void setPortDim(String portDim) {
        this.portDim = portDim;
    }

    public CardIndexDictVo(String indexCode, String indexName) {
        this.indexCode = indexCode;
        this.indexName = indexName;
    }
    public CardIndexDictVo(String indexCode, String indexName, String columnCode, String columnName) {
        this.indexCode = indexCode;
        this.indexName = indexName;
        this.columnCode = columnCode;
        this.columnName = columnName;
    }
    public CardIndexDictVo(String columnAlign, String columnCode, Boolean indeterminate, String indexLevel, String indexCode, String indexName, Boolean isCheckAll) {
        this.columnAlign = columnAlign;
        this.columnCode = columnCode;
        this.indexLevel = indexLevel;
        this.indexCode = indexCode;
        this.indexName = indexName;
    }

    public CardIndexDictVo(String columnAlign,List<CardIndexDictVo> children, String indexLevel, String indexCode, String indexName) {
        this.columnAlign = columnAlign;
        this.children = children;
        this.indexLevel = indexLevel;
        this.indexCode = indexCode;
        this.indexName = indexName;
    }


    public CardIndexDictVo(String indexCode, String indexName, String columnAlign) {
        this.indexCode = indexCode;
        this.indexName = indexName;
        if(!StringUtils.isEmpty(columnAlign))
            this.columnAlign = columnAlign;
    }

    public CardIndexDictVo(String indexCode, String indexName, String columnCode, String columnName, String columnAlign, String benchCode, Boolean isIndeterminate, Boolean isCheckAll, List<String> checkedIndexPlan, List<CardIndexDictVo> children) {
        this.indexCode = indexCode;
        this.indexName = indexName;
        this.columnCode = columnCode;
        this.columnName = columnName;
        this.columnAlign = columnAlign;
        this.benchCode = benchCode;
        this.isIndeterminate = isIndeterminate;
        this.isCheckAll = isCheckAll;
        this.checkedIndexPlan = checkedIndexPlan;
        this.children = children;
    }

    public CardIndexDictVo() {
    }
}
