package entity;

import dto.response.InsuPreferInfo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by p on 2017/9/13.
 */
public class InsuPrefer {
    String phone;
    String insuType;
    String theme;
    String payMethod;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInsuType() {
        return insuType;
    }

    public void setInsuType(String insuType) {
        this.insuType = insuType;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public InsuPreferInfo toInsuPreferInfo() {
        InsuPreferInfo info = new InsuPreferInfo();
        info.setInsuType(new ArrayList<String>(Arrays.asList(insuType.split(","))));
        info.setTheme(new ArrayList<String>(Arrays.asList(theme.split(","))));
        info.setPayMethod(new ArrayList<String>(Arrays.asList(payMethod.split(","))));
        return info;
    }

}
