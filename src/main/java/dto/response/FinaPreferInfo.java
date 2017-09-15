package dto.response;

import entity.FinaPrefer;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;

/**
 * Created by p on 2017/9/13.
 */
public class FinaPreferInfo {

    private ArrayList<String> duration;
    private ArrayList<String> proType;
    private ArrayList<String> level;
    private ArrayList<String> revenue;

    public ArrayList<String> getDuration() {
        return duration;
    }

    public void setDuration(ArrayList<String> duration) {
        this.duration = duration;
    }

    public ArrayList<String> getProType() {
        return proType;
    }

    public void setProType(ArrayList<String> proType) {
        this.proType = proType;
    }

    public ArrayList<String> getLevel() {
        return level;
    }

    public void setLevel(ArrayList<String> level) {
        this.level = level;
    }

    public ArrayList<String> getRevenue() {
        return revenue;
    }

    public void setRevenue(ArrayList<String> revenue) {
        this.revenue = revenue;
    }

    public FinaPrefer toFinaPrefer(String phone) {
        FinaPrefer info = new FinaPrefer();
        info.setPhone(phone);
        info.setDuration(StringUtils.join(duration,","));
        info.setProType(StringUtils.join(proType,","));
        info.setLevel(StringUtils.join(level,","));
        info.setRevenue(StringUtils.join(revenue,","));
        return info;
    }
}
