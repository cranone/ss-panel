package com.dep.sspanel.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.dep.sspanel.util.ServerUtil;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "system_log")
@DynamicUpdate
public class SystemLog implements Serializable{
    private static final long serialVersionUID = 1021912687706388321L;
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    @Column(length = 36, nullable = false)
    private String id;
    @Column(length = 32)
    private String operator;
    @Column(nullable = true)
    private String description;
    @Column(length = 5000)
    private String operateContent;
    @Column
    private String ip;
    @Column
    private String mark;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Column(name = "create_date")
    private Date date;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column
    private String stackTrace;

    public SystemLog() {
        this.date = new Date();
    }

    public SystemLog(HttpServletRequest request) {
        this.ip = ServerUtil.getIpAddr(request);
        this.date = new Date();
    }

    /**
     * 
     * @param request
     * @param description
     *            描述
     */
    public SystemLog(HttpServletRequest request, String description) {
        this.description = description;
        this.ip = ServerUtil.getIpAddr(request);
        this.date = new Date();
    }

    /**
     * 
     * @param request
     * @param description
     *            描述
     * @param operateContent
     *            操作人
     * @param operate
     *            操作内容
     */
    public SystemLog(HttpServletRequest request, String description, String operator, String operateContent) {
        this.operator = operator;
        this.description = description;
        this.operateContent = operateContent;
        this.ip = ServerUtil.getIpAddr(request);
        this.date = new Date();
    }

    /**
     * 
     * @param request
     * @param description
     *            描述
     * @param operateContent
     *            操作人
     * @param operate
     *            操作内容
     */
    public SystemLog(String ip, String description, String operator, String operateContent) {
        this.operator = operator;
        this.description = description;
        this.operateContent = operateContent;
        this.ip = ip;
        this.date = new Date();
    }

    /**
     * 
     * @param request
     * @param description
     *            描述
     * @param operator
     *            操作人
     * @param operateContent
     *            操作内容
     * @param stackTrace
     *            触发层级
     */
    public SystemLog(HttpServletRequest request, String description, String operator, String operateContent,
            String stackTrace) {
        this.operator = operator;
        this.description = description;
        this.operateContent = operateContent;
        this.ip = ServerUtil.getIpAddr(request);
        this.stackTrace = stackTrace;
        this.date = new Date();
    }

    /**
     * ID
     * 
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * 操作人
     * 
     * @return
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 描述
     * 
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * 操作内容
     * 
     * @return
     */
    public String getOperateContent() {
        return operateContent;
    }

    /**
     * IP
     * 
     * @return
     */
    public String getIp() {
        return ip;
    }

    /**
     * 备注
     * 
     * @return
     */
    public String getMark() {
        return mark;
    }

    /**
     * 创建日期
     * 
     * @return
     */
    public Date getDate() {
        return date;
    }

    /**
     * 触发层级
     * 
     * @return
     */
    public String getStackTrace() {
        return stackTrace;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOperateContent(String operateContent) {
        this.operateContent = operateContent;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

}
