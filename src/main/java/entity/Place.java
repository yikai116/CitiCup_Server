package entity;

import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by p on 2017/11/23.
 */
public class Place {
    String phone;
    int id;
    String site;
    Time time;
    String type;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String  getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
