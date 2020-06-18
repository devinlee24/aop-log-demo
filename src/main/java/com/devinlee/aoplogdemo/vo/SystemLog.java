package com.devinlee.aoplogdemo.vo;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Data
public class SystemLog {

    /**
     * 唯一标识
     */
    private String id = UUID.randomUUID().toString().replaceAll("-", "");

    /**
     * 描述
     */
    private String desc;

    /**
     * 方法名
     */
    private String method;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 返回参数
     */
    private String returnValues;

    /**
     * 日志等级
     */
    private int level;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 持续时间(秒)
     */
    private long durationTime;

    /**
     * 备注
     */
    private String remark;


    @Override
    public String toString() {
        return "SystemLog{" +
                "id='" + id + '\'' +
                ", desc='" + desc + '\'' +
                ", method='" + method + '\'' +
                ", params='" + params + '\'' +
                ", returnValues='" + returnValues + '\'' +
                ", level=" + level +
                ", operateType='" + operateType + '\'' +
                ", beginTime=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(beginTime) +
                ", endTime=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime) +
                ", durationTime=" + durationTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
