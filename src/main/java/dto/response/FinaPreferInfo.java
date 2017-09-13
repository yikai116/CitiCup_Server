package dto.response;

import entity.FinaPrefer;

/**
 * Created by p on 2017/9/13.
 */
public class FinaPreferInfo {

    String duration;
    String proType;
    String level;
    String revenue;

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

    public FinaPrefer toFinaPrefer(String phone) {
        FinaPrefer info = new FinaPrefer();
        info.setPhone(phone);
        info.setDuration(duration);
        info.setProType(proType);
        info.setLevel(level);
        info.setRevenue(revenue);
        return info;
    }
}
