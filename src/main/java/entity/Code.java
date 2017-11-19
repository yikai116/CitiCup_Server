package entity;

import java.sql.Timestamp;
import java.util.Random;

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
        code = getVerCode();
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

    /**
     * 得到四位验证码
     * @return 验证码
     */
    public static String getVerCode() {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        String verCode;
        for (int i = 0; i < 4; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        verCode = sb.toString();
        return verCode;
    }
}
