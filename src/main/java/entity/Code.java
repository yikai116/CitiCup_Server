package entity;

import helper.Helper;

import java.sql.Timestamp;

/**
 * Created by p on 2017/7/31.
 */
public class Code {
    private String phone;
    private String code;
    private Timestamp date;

    public Code() {
    }

    public Code(String phone) {
        this.phone = phone;
        code = Helper.getVerCode();
        date = new Timestamp(new java.util.Date().getTime() + 5 * 60 * 1000);
    }

    public Code(String phone, String code, Timestamp date) {
        this.phone = phone;
        this.code = code;
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
