package entity;

import helper.Helper;

import java.sql.Date;

/**
 * Created by p on 2017/7/31.
 */
public class Code {
    private String phone;
    private String code;
    private Date date;

    public Code(String phone) {
        this.phone = phone;
        code = Helper.getVerCode();
        date = new Date(new java.util.Date().getTime() + 5 * 60 * 1000);
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
