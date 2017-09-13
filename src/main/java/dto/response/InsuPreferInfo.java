package dto.response;

import entity.InsuPrefer;

/**
 * Created by p on 2017/9/13.
 */
public class InsuPreferInfo {

    String insuType;
    String theme;
    String payMethod;

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

    public InsuPrefer toInsuPrefer(String phone){
        InsuPrefer prefer = new InsuPrefer();
        prefer.setPhone(phone);
        prefer.setInsuType(insuType);
        prefer.setTheme(theme);
        prefer.setPayMethod(payMethod);
        return prefer;
    }
}
