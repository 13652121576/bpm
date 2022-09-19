package com.manage.common.util.excelUtils;

//excel列冻结参数
public class ExcelGroupColumn {

    // 起始列
    private Integer firstColl;
    // 结束列
    private Integer lastColl;

    public Integer getFirstColl() {
        return firstColl;
    }
    public void setFirstColl(Integer firstColl) {
        this.firstColl = firstColl;
    }
    public Integer getLastColl() {
        return lastColl;
    }
    public void setLastColl(Integer lastColl) {
        this.lastColl = lastColl;
    }



    public ExcelGroupColumn() {
        super();
    }
    public ExcelGroupColumn(Integer firstColl, Integer lastColl) {
        super();
        this.firstColl = firstColl;
        this.lastColl = lastColl;
    }
    @Override
    public String toString() {
        return "ExcelGroupColumn [firstColl=" + firstColl + ", lastColl=" + lastColl + "]";
    }




}