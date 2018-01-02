package com.dep.sspanel.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 流量日志
 * 
 * @author Maclaine
 *
 */
@Entity(name = "user_traffic_log")
@DynamicUpdate
public class UserTrafficLog implements Serializable {
    private static final long serialVersionUID = 6489380738553107509L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;// *
    @Column(name = "u")
    private Long upload;// 上传*
    @Column(name = "d")
    private Long download;// 下载*
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;// 使用人*
    @ManyToOne
    @JoinColumn(name = "node_id")
    private Node node;// 节点*
    @Column
    private Double rate;// 倍率*
    @Column
    private String traffic;// 流量*
    @Column(name = "log_time")
    private Long logTime;// 记录时间*

    public Integer getId() {
        return id;
    }

    public Long getUpload() {
        return upload;
    }

    public Long getDownload() {
        return download;
    }

    public User getUser() {
        return user;
    }

    public Node getNode() {
        return node;
    }

    public Double getRate() {
        return rate;
    }

    public String getTraffic() {
        return traffic;
    }

    public Long getLogTime() {
        return logTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUpload(Long upload) {
        this.upload = upload;
    }

    public void setDownload(Long download) {
        this.download = download;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public void setLogTime(Long logTime) {
        this.logTime = logTime;
    }

}
