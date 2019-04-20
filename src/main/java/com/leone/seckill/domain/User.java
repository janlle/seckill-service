package com.leone.seckill.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * @author leone
 * @since 2018-10-26
 **/
public class User implements Serializable {

    private static final long serialVersionUID = 1952883671037569579L;

    private Long userId;

    private String account;

    private String username;

    private String password;

    private String address;

    private Integer age;

    private String phone;

    private Date createTime;

    private boolean deleted;

    public User() {
    }

    public User(String account, String username, String password, String address, Integer age, String phone, Date createTime, boolean deleted) {
        this.account = account;
        this.username = username;
        this.password = password;
        this.address = address;
        this.age = age;
        this.phone = phone;
        this.createTime = createTime;
        this.deleted = deleted;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
