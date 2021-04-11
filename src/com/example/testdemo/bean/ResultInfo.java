package com.example.testdemo.bean;

import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

import java.io.Serializable;

@Table(name = "user_table")
public class ResultInfo implements Serializable {

    @Id(column = "time")
    private long time;//
    private String result;//
    private String flag1;
    private String flag2;
    private String flag3;
    private String fliter1;
    private String fliter2;
    private String fliter3;
    private String result1;
    private String result2;
    private String result3;
    private String result_input1;

    public String getResult_input2() {
        return result_input2;
    }

    public void setResult_input2(String result_input2) {
        this.result_input2 = result_input2;
    }

    public String getResult_input3() {
        return result_input3;
    }

    public void setResult_input3(String result_input3) {
        this.result_input3 = result_input3;
    }

    private String result_input2;
    private String result_input3;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getFlag1() {
        return flag1;
    }

    public void setFlag1(String flag1) {
        this.flag1 = flag1;
    }

    public String getFlag2() {
        return flag2;
    }

    public void setFlag2(String flag2) {
        this.flag2 = flag2;
    }

    public String getFlag3() {
        return flag3;
    }

    public void setFlag3(String flag3) {
        this.flag3 = flag3;
    }

    public String getFliter1() {
        return fliter1;
    }

    public void setFliter1(String fliter1) {
        this.fliter1 = fliter1;
    }

    public String getFliter2() {
        return fliter2;
    }

    public void setFliter2(String fliter2) {
        this.fliter2 = fliter2;
    }

    public String getFliter3() {
        return fliter3;
    }

    public void setFliter3(String fliter3) {
        this.fliter3 = fliter3;
    }

    public String getResult1() {
        return result1;
    }

    public void setResult1(String result1) {
        this.result1 = result1;
    }

    public String getResult2() {
        return result2;
    }

    public void setResult2(String result2) {
        this.result2 = result2;
    }

    public String getResult3() {
        return result3;
    }

    public void setResult3(String result3) {
        this.result3 = result3;
    }

    public String getResult_input1() {
        return result_input1;
    }

    public void setResult_input1(String result_input1) {
        this.result_input1 = result_input1;
    }
}
