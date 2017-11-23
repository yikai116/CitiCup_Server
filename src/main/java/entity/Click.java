package entity;

import java.sql.Time;

/**
 * Created by p on 2017/11/23.
 */
public class Click {
    Time time;
    String phone;
    int id;

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

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
}
