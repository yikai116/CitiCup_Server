package entity;

import dto.response.FinaPreferInfo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by p on 2017/9/13.
 */
public class FinaPrefer {

    String phone;
    String duration;
    String proType;
    String level;
    String revenue;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public FinaPreferInfo toFinaPreferInfo() {
        FinaPreferInfo info = new FinaPreferInfo();
        info.setDuration(new ArrayList<String>(Arrays.asList(duration.split(","))));
        info.setProType(new ArrayList<String>(Arrays.asList(proType.split(","))));
        info.setLevel(new ArrayList<String>(Arrays.asList(level.split(","))));
        info.setRevenue(new ArrayList<String>(Arrays.asList(revenue.split(","))));
        return info;
    }
}
