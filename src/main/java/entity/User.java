package entity;

/**
 * Created by p on 2017/7/30.
 */
public class User {
    private String phone;
    private String name;
    private String psw;
    private String token;
    private String code;

    public User() {
    }

    public User(String phone, String name, String psw, String token, String code) {
        this.phone = phone;
        this.name = name;
        this.psw = psw;
        this.token = token;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
