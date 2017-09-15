package dto.response;

import entity.InsuPrefer;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;

/**
 * Created by p on 2017/9/13.
 */
public class InsuPreferInfo {

    protected ArrayList<String> insuType;
    protected ArrayList<String> theme;
    protected ArrayList<String> payMethod;

    public ArrayList<String> getInsuType() {
        return insuType;
    }

    public void setInsuType(ArrayList<String> insuType) {
        this.insuType = insuType;
    }

    public ArrayList<String> getTheme() {
        return theme;
    }

    public void setTheme(ArrayList<String> theme) {
        this.theme = theme;
    }

    public ArrayList<String> getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(ArrayList<String> payMethod) {
        this.payMethod = payMethod;
    }

    public InsuPrefer toInsuPrefer(String phone){
        InsuPrefer prefer = new InsuPrefer();
        prefer.setPhone(phone);
        prefer.setInsuType(StringUtils.join(insuType,","));
        prefer.setTheme(StringUtils.join(theme,","));
        prefer.setPayMethod(StringUtils.join(payMethod,","));
        return prefer;
    }
}
