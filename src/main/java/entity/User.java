package entity;

import dto.response.UserInfo;

/**
 * Created by p on 2017/7/30.
 */
public class User {
    private String phone;
    private String name;
    private String psw;
    private String token;
    private String avatar;
    private int age;
    private int income;

    public User() {
    }

    public User(String phone, String name, String psw, String token, String avatar) {
        this.phone = phone;
        this.name = name;
        this.psw = psw;
        this.token = token;
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public UserInfo toUserInfo(){
        UserInfo info = new UserInfo();
        info.setPhone(getPhone());
        info.setName(getName());
        info.setAvatar(getAvatar());
        info.setAge(getAge());
        info.setIncome(getIncome());
        return info;
    }
}
